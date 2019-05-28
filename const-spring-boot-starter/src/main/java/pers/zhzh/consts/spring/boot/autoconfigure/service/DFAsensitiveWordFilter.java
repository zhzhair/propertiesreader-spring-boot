package pers.zhzh.consts.spring.boot.autoconfigure.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pers.zhzh.consts.spring.boot.autoconfigure.properties.ConstReaderProperties;
import pers.zhzh.consts.spring.boot.autoconfigure.util.CensorWordReaderUtil;
import pers.zhzh.consts.spring.boot.autoconfigure.util.DFAfilterUtil;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableConfigurationProperties({ConstReaderProperties.class})
public class DFAsensitiveWordFilter {
    @Resource
    private ConstReaderProperties constReaderProperties;
    private final Logger logger = Logger.getLogger(DFAsensitiveWordFilter.class.getName());

    @Bean
    @ConditionalOnMissingBean
    public DFAsensitiveWordBean getDFAsensitiveWord(){
        boolean bool = constReaderProperties.getCensorWordEnable();
        DFAsensitiveWordBean dfaSensitiveWordBean = new DFAsensitiveWordBean();
        if(bool){
            ConstReaderProperties.CensorWord censorWord = constReaderProperties.getCensorWord();
            if(censorWord.getDfaEnabled()){
                Set<String> set = CensorWordReaderUtil.toSet(censorWord.getCensorWordsLocation(),censorWord.getWordCount());
                Map<String,Object> map = DFAfilterUtil.getSensitiveWordToHashMap(set);
                dfaSensitiveWordBean.setMap(map);
                logger.log(Level.INFO,"加载敏感词到map，文件加载路径：" + censorWord.getCensorWordsLocation());
            }
        }
        return dfaSensitiveWordBean;
    }
}
