package com.example.demo.propertiesreader.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.*;
import pers.zhzh.consts.spring.boot.autoconfigure.service.DFAsensitiveWordBean;
import pers.zhzh.consts.spring.boot.autoconfigure.service.SensitiveWordBean;

import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping("/sensitiveWord")
public class SensitiveWordController extends BaseController {

    @Resource
    private SensitiveWordBean sensitiveWordBean;
    @Resource
    private DFAsensitiveWordBean dfaSensitiveWordBean;

    @RequestMapping(value = "/getSensorWord",method = {RequestMethod.GET})
    public BaseResponse<Set<String>> getSensorWord(String text) {
        BaseResponse<Set<String>> baseResponse = new BaseResponse<>();
        Set<String> set = sensitiveWordBean.getCensorWord(text);
        if(set.isEmpty()){
            baseResponse.setCode(0);
            baseResponse.setMsg("没有查询到敏感词");
            return baseResponse;
        }
        baseResponse.setCode(1);
        baseResponse.setMsg("已查询到敏感词");
        baseResponse.setData(set);
        return baseResponse;
    }

    @RequestMapping(value = "/getDFASensorWord",method = {RequestMethod.GET})
    public BaseResponse<Set<String>> getDFASensorWord(String text) {
        BaseResponse<Set<String>> baseResponse = new BaseResponse<>();
        Set<String> set = dfaSensitiveWordBean.getCensorWord(text);
        if(set.isEmpty()){
            baseResponse.setCode(0);
            baseResponse.setMsg("没有查询到敏感词");
        }else{
            baseResponse.setCode(1);
            baseResponse.setMsg("已查询到敏感词");
            baseResponse.setData(set);
        }
        return baseResponse;
    }
}
