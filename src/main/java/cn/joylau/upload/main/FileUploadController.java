package cn.joylau.upload.main;

import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by JoyLau on 2017/6/5.
 * cn.joylau.upload.main
 * 2587038142@qq.com
 */
@Controller
@RequestMapping("/upload")
@ConfigurationProperties(prefix = "qiniu")
public class FileUploadController {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private Auth auth;
    private Configuration cfg;

    public FileUploadController() {
        this.auth = Auth.create(accessKey, secretKey);
        //构造一个带指定Zone对象的配置类,zone0指华东地区
        this.cfg = new Configuration(Zone.zone0());
    }

    @RequestMapping()
    public String uploadPage() {
        BucketManager bucketManager = new BucketManager(auth, cfg);
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "");
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                System.out.println(item.key);
                System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);
                System.out.println("=============");
            }

        }
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
            Thread.sleep(5000);
//            Response response = uploadManager.put(fileList.getBytes(), key, upToken);
            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return Results.success("http://file.joylau.cn/"+key);
        } catch (Exception ex) {
            return Results.failure();
        }
    }















    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
