package pers.zhzh.consts.spring.boot.autoconfigure.service;

import com.alibaba.fastjson.JSONObject;
import pers.zhzh.consts.spring.boot.autoconfigure.util.DicUtil;

import java.util.List;
import java.util.Map;

public class MultiDictionaryBean {
    private JSONObject multiJsonObject;

    public JSONObject getMultiJsonObject() {
        return multiJsonObject;
    }

    public void setMultiJsonObject(JSONObject multiJsonObject) {
        this.multiJsonObject = multiJsonObject;
    }

    public JSONObject getTypeObject(String type) {
        if(this.multiJsonObject != null){
            String o = String.valueOf(multiJsonObject.get(type));
            return JSONObject.parseObject(o);
        }else{
            return null;
        }
    }

    public JSONObject getTypeKeyObject(String type,String key) {
        if(this.multiJsonObject != null){
            String o = String.valueOf(multiJsonObject.get(type));
            String o1 = String.valueOf(JSONObject.parseObject(o).get(key));
            return JSONObject.parseObject(o1);
        }else{
            return null;
        }
    }

    public List<Map<String,String>> getTypeListMap(String type, String key) {
        return DicUtil.getCodeAndNames(this.getTypeKeyObject(type,key));
    }
}
