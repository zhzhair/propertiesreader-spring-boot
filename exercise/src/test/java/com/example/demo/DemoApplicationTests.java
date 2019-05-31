package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.zhzh.consts.spring.boot.autoconfigure.service.*;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    private ConstValueBean constValueBean;
    @Resource
    private DictionaryBean dictionaryBean;
    @Resource
    private MultiDictionaryBean multiDictionaryBean;
    @Resource
    private SensitiveWordBean sensitiveWordBean;
    @Resource
    private DFAsensitiveWordBean dfAsensitiveWordBean;
    private long begin;

    @Test
    public void contextLoads() {
        begin = System.currentTimeMillis();
        System.err.println(constValueBean.getMac());
        dicSerr();
        sensitiveWordSerr();
        System.err.println("use " + (System.currentTimeMillis() - begin) + " milliseconds");
    }

    private void dicSerr(){
        System.err.println(dictionaryBean.getKeyObject("login_channel"));
        System.err.println(dictionaryBean.getListMap("login_channel"));
        System.err.println(multiDictionaryBean.getTypeKeyObject("dic","id_type"));
        System.err.println(multiDictionaryBean.getTypeListMap("dic","id_type"));
    }

    private void sensitiveWordSerr(){
        System.err.println(sensitiveWordBean.getCensorWord("诱惑小泽玛利亚，妈卖批，草泥马狗日的"));
        System.err.println(sensitiveWordBean.getCensorWordText("诱惑小泽玛利亚，妈卖批，草泥马狗日的"));
        System.err.println(dfAsensitiveWordBean.getCensorWord("诱惑小泽玛利亚，妈卖批，草泥马狗日的"));
        System.err.println(dfAsensitiveWordBean.getCensorWordText("诱惑小泽玛利亚，妈卖批，草泥马狗日的"));
    }

    @Before
    public void before(){
        begin = System.currentTimeMillis();
    }
    @After
    public void after(){
//        System.err.println("use " + (System.currentTimeMillis() - begin) + " milliseconds");
    }
}
