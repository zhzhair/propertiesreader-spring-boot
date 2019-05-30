package pers.zhzh.consts.spring.boot.autoconfigure.service;

import com.alibaba.fastjson.JSONObject;
import pers.zhzh.consts.spring.boot.autoconfigure.util.DicUtil;

import java.util.List;
import java.util.Map;

public class DictionaryBean {
    private JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getKeyObject(String key) {
        if(this.jsonObject != null){
            String o = String.valueOf(jsonObject.get(key));
            return JSONObject.parseObject(o);
        }else{
            return null;
        }
    }

    public List<Map<String,String>> getListMap(String key) {
        return DicUtil.getCodeAndNames(this.getKeyObject(key));
    }

}
