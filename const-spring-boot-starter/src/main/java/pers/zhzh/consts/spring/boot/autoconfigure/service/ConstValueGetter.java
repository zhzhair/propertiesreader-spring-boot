package pers.zhzh.consts.spring.boot.autoconfigure.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pers.zhzh.consts.spring.boot.autoconfigure.properties.ConstReaderProperties;
import pers.zhzh.consts.spring.boot.autoconfigure.util.WebUtil;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class ConstValueGetter {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(DFAsensitiveWordFilter.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public ConstValueBean getConstValueBean(){
        ConstValueBean constValueBean = new ConstValueBean();
        if(constReaderProperties.getConstValueEnable()){
            constValueBean.setMac(WebUtil.getMACAddress());
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                constValueBean.setInetAddress(inetAddress);
                constValueBean.setHostName(inetAddress.getHostName());
                constValueBean.setHostAddress(inetAddress.getHostAddress());
                constValueBean.setHostAllAddress(WebUtil.getAllIpAddress());
                logger.log(Level.INFO,"加载本机信息成功");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return constValueBean;
    }
}
