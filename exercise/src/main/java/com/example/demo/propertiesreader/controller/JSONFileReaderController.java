package com.example.demo.propertiesreader.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.dto.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pers.zhzh.consts.spring.boot.autoconfigure.service.DictionaryBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("jsonReader")
public class JSONFileReaderController extends BaseController {

    @Resource
    private DictionaryBean dictionaryBean;

    @RequestMapping(value = "/findNameByCode", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<String> findNameByCode(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMsg("success");
        JSONObject jsonObject = dictionaryBean.getKeyObject(key);
        baseResponse.setData(String.valueOf(jsonObject.get(code)));
        return baseResponse;
    }

    @RequestMapping(value = "/findAll", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<List<Map<String,String>>> findAll(@RequestParam(value = "key") String key) {
        BaseResponse<List<Map<String,String>>> baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMsg("success");
        baseResponse.setData(dictionaryBean.getListMap(key));
        return baseResponse;
    }

}
