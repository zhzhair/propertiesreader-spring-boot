package pers.zhzh.consts.spring.boot.autoconfigure.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicUtil {
    /**
     * 转换下拉列表的字典数据
     * @param jsonObject 字典数据
     * @return list for dic
     */
    public static List<Map<String,String>> getCodeAndNames(JSONObject jsonObject){
        List<Map<String,String>> list = new ArrayList<>();
        for (String code : jsonObject.keySet()) {
            Map<String,String> map = new HashMap<>();
            map.put("code",code);
            map.put("name",String.valueOf(jsonObject.get(code)));
            list.add(map);
        }
        return list;
    }

    public static JSONObject getJSONObject(String path){
        JSONObject dicSONObject = null;
        String suffix = path.substring(path.indexOf("."));
        if(".json".equals(suffix)){
            dicSONObject = DictionaryReaderUtil.getJSONObject(path);
        }else if(".xml".equals(suffix)){
            dicSONObject = DicForXmlReaderUtil.getJSONObject(path);
        }else{
            try {
                throw new Exception("这里加载的只能是.json或.xml文件");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dicSONObject;
    }

}
