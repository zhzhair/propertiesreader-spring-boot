package com.example.demo.propertiesreader.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.common.dto.BaseResponse;
import com.example.demo.common.enums.Gender;
import com.example.demo.common.enums.GenderEnum;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("enums")
public class EnumsController extends BaseController {

    @RequestMapping(value = "/findNameByCode", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<String> findNameByCode(@RequestParam(value = "code") Integer code) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMsg("success");
        int sexIndex;
        switch (code){
            case 1 : sexIndex = Gender.MALE; break;
            case 2 : sexIndex = Gender.FEMALE; break;
            default : sexIndex = Gender.UNKNOWN;
        }
        //常量和枚举案例
        GenderEnum genderEnum = GenderEnum.fromStatus(String.valueOf(sexIndex));
        if(genderEnum == null){
            baseResponse.setData("未知");
        }else{
            baseResponse.setData(genderEnum.getDescription());
        }
        return baseResponse;
    }

    @RequestMapping(value = "/findAll", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<List<Map<String,String>>> findAll() {
        BaseResponse<List<Map<String,String>>> baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMsg("success");
        List<Map<String,String>> list = new ArrayList<>();
        for (GenderEnum genderEnum : GenderEnum.values()) {
            Map<String,String> map = new HashMap<>();
            map.put("code",genderEnum.getCode());
            map.put("name",genderEnum.getDescription());
            list.add(map);
        }
        baseResponse.setData(list);
        return baseResponse;
    }
}
