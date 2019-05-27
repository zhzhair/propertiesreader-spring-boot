package pers.zhzh.consts.spring.boot.autoconfigure.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pers.zhzh.consts.spring.boot.autoconfigure.properties.ConstReaderProperties;
import pers.zhzh.consts.spring.boot.autoconfigure.util.DictionaryReaderUtil;
import pers.zhzh.consts.spring.boot.autoconfigure.util.StringUtil;

import javax.annotation.Resource;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class MultiDictionaryReader {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(MultiDictionaryReader.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public MultiDictionaryBean getMultiDictionaryReader(){
        MultiDictionaryBean dictionaryBean = new MultiDictionaryBean();
        Boolean bool = constReaderProperties.getDicEnable();
        if(bool){
            String path = constReaderProperties.getDic().getDicLocation();
            String[] paths = path.split(",");
            JSONObject dicSONObject = DictionaryReaderUtil.getJSONObject(paths[0]);
            JSONObject multiJsonObject = new JSONObject();
            multiJsonObject.put(StringUtil.getFileName(paths[0]),dicSONObject);
            if(paths.length > 1){
                for (int i = 1; i < paths.length; i++) {
                    JSONObject dicSONObject1 = DictionaryReaderUtil.getJSONObject(paths[i]);
                    multiJsonObject.put(StringUtil.getFileName(paths[i]),dicSONObject1);
                }
            }else{
                logger.warning("只有单个json配置文件建议注入对象：DictionaryBean");
            }
            dictionaryBean.setMultiJsonObject(multiJsonObject);
            logger.log(Level.INFO, "初始化字典数据 ===== 文件路径: " + path);
        }
        return dictionaryBean;
    }

}
