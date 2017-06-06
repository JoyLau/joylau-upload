package cn.joylau.upload.main;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Results {

    public static String success(){
        Map<String, Object> map = new HashMap<>();
        map.put("success",true);
        map.put("msg","操作成功!");
        return JSON.toJSONString(map);
    }

    public static String success(String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("success",true);
        map.put("msg",msg);
        return JSON.toJSONString(map);
    }

    public static String success(String successKey,String msgKey,String msg){
        Map<String, Object> map = new HashMap<>();
        map.put(successKey,true);
        map.put(msgKey,msg);
        return JSON.toJSONString(map);
    }


    public static String failure(){
        Map<String, Object> map = new HashMap<>();
        map.put("success",false);
        map.put("msg","操作失败!");
        return JSON.toJSONString(map);
    }

    public static String failure(String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("success",false);
        map.put("msg",msg);
        return JSON.toJSONString(map);
    }

    public static String failure(String successKey,String msgKey,String msg){
        Map<String, Object> map = new HashMap<>();
        map.put(successKey,false);
        map.put(msgKey,msg);
        return JSON.toJSONString(map);
    }
}