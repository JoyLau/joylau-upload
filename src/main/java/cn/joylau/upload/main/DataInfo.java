package cn.joylau.upload.main;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by JoyLau on 2017/6/7.
 * cn.joylau.upload.main
 * 2587038142@qq.com
 */
public class DataInfo{
    @Setter @Getter public String fileName;
    @Setter @Getter public String hash;
    @Setter @Getter public String fileSize;
    @Setter @Getter public String putTime;
    @Setter @Getter public String putDate;
    @Setter @Getter public String mimeType;
    @Setter @Getter public String endUser;

}
