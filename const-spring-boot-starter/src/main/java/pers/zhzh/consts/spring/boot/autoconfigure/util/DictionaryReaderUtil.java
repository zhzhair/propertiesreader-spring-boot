package pers.zhzh.consts.spring.boot.autoconfigure.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取json配置文件工具类
 */
public class DictionaryReaderUtil {

    public static JSONObject getJSONObject(String path){
        try {
            InputStream inputStream = DictionaryReaderUtil.class.getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
            String var = FileCopyUtils.copyToString(reader);
            String newVar = var.replaceAll("\\s*","");
            inputStream.close();
            return JSONObject.parseObject(newVar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
