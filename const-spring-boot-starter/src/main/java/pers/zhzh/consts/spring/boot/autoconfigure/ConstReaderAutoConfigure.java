package pers.zhzh.consts.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pers.zhzh.consts.spring.boot.autoconfigure.properties.ConstReaderProperties;
import pers.zhzh.consts.spring.boot.autoconfigure.service.*;

@Configuration
@ConditionalOnClass(value = {DictionaryBean.class, SensitiveWordBean.class})
@EnableConfigurationProperties({ConstReaderProperties.class})
@Import({DictionaryReader.class, MultiDictionaryReader.class, SensitiveWordFilter.class, DFAsensitiveWordFilter.class})
public class ConstReaderAutoConfigure {

}
