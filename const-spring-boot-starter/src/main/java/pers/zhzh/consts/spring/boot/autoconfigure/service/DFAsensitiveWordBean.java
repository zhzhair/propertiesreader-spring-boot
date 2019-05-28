package pers.zhzh.consts.spring.boot.autoconfigure.service;

import pers.zhzh.consts.spring.boot.autoconfigure.util.DFAfilterUtil;

import java.util.Map;
import java.util.Set;

public class DFAsensitiveWordBean {
    private Map<String,Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public boolean hasCensorWord(String text){
        return DFAfilterUtil.hasSensitiveWordAll(text,map);
    }

    public Set<String> getCensorWord(String text){
        return DFAfilterUtil.getSensitiveWordAll(text,map);
    }

    public String getCensorWordText(String text){
        return DFAfilterUtil.getSensitiveWordTextAll(text,map);
    }

}
