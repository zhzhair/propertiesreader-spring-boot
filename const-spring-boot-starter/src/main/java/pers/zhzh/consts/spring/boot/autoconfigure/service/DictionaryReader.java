package pers.zhzh.consts.spring.boot.autoconfigure.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pers.zhzh.consts.spring.boot.autoconfigure.properties.ConstReaderProperties;
import pers.zhzh.consts.spring.boot.autoconfigure.util.DicForXmlReaderUtil;
import pers.zhzh.consts.spring.boot.autoconfigure.util.DicUtil;
import pers.zhzh.consts.spring.boot.autoconfigure.util.DictionaryReaderUtil;

import javax.annotation.Resource;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class DictionaryReader {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(DictionaryReader.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public DictionaryBean getDictionaryReader(){
        DictionaryBean dictionaryBean = new DictionaryBean();
        Boolean bool = constReaderProperties.getDicEnable();
        if(bool){
            String path = constReaderProperties.getDic().getDicLocation();
            String[] paths = path.split(",");
            dictionaryBean.setJsonObject(DicUtil.getJSONObject(paths[0]));
            logger.log(Level.INFO, "初始化字典数据 ===== 文件路径: " + path);
        }
        return dictionaryBean;
    }

}
