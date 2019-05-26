package pers.zhzh.consts.spring.boot.autoconfigure.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicToListUtil {
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

}
