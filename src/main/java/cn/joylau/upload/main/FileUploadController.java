package cn.joylau.upload.main;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JoyLau on 2017/6/5.
 * cn.joylau.upload.main
 * 2587038142@qq.com
 */
@Controller
@RequestMapping("/")
public class FileUploadController {

    @Value("${qiniu.bucket}")
    private String bucket;

    private Auth auth;

    private Configuration cfg;

    public FileUploadController(@Value("${qiniu.accessKey}") String accessKey, @Value("${qiniu.secretKey}") String secretKey) {
        this.auth = Auth.create(accessKey, secretKey);
        //构造一个带指定Zone对象的配置类,zone0指华东地区
        this.cfg = new Configuration(Zone.zone0());
    }

    @RequestMapping()
    public String uploadPage() {
        return "index";
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String upload(@RequestParam("fileList") MultipartFile fileList) throws IOException {
        //其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileList.getOriginalFilename();
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(fileList.getBytes(), key, upToken);
            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return Results.success("<a href= 'http://file.joylau.cn/"+key+"' target='_blank'>点击查看</a>");
        } catch (Exception ex) {
            return Results.failure();
        }
    }


    @RequestMapping("/explorer")
    public String list(){
        return "list";
    }

    @RequestMapping("/getFileDate")
    @ResponseBody
    public String getFileDate(Model model){
        BucketManager bucketManager = new BucketManager(auth, cfg);
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "");
        List<DataInfo> infoList = new ArrayList<>();
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                DataInfo dataInfo = new DataInfo();
                dataInfo.setFileName(item.key);
                dataInfo.setHash(item.hash);
                dataInfo.setFileSize(new DecimalFormat("0.##").format(item.fsize / 1024.00));
                dataInfo.setMimeType(item.mimeType);
                dataInfo.setPutDate(toStringDate("yyyy-MM-dd",item.putTime));
                dataInfo.setPutTime(toStringDate("yyyy-MM-dd HH:mm:ss",item.putTime));
                dataInfo.setEndUser(item.endUser);
                infoList.add(dataInfo);
            }
        }

        /*分组*/
        infoList.sort((b2, b3) -> b3.getPutDate().compareTo(b2.getPutDate()));

        model.addAttribute("rows",infoList);
        return JSON.toJSONString(model);
    }

    /*由于七牛云的文件时间的特殊性，获取的时间需要去掉后5位得到的才是UNIX的时间*/
    private String toStringDate(String pattern,long date){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date date1 = new Date(date / 10000);
        return sdf.format(date1);
    }

    @RequestMapping("/download")
    @ResponseBody
    public String download(String fileName) throws UnsupportedEncodingException {
        String domainOfBucket = "//file.joylau.cn";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        return finalUrl;
    }
}
