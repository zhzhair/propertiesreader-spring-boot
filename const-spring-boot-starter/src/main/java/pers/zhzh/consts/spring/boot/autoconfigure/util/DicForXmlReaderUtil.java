package pers.zhzh.consts.spring.boot.autoconfigure.util;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.List;

/**
 * 读取json配置文件工具类
 */
public class DicForXmlReaderUtil {

    public static JSONObject getJSONObject(String path){
        JSONObject jsonObject = new JSONObject();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(DicForXmlReaderUtil.class.getResource(path));
            Element rootElement = document.getRootElement();
            List elements = rootElement.elements();
            for (Object o : elements) {
                Element element = (Element)o;
                String key = element.attributeValue("id");
                JSONObject jsonObject1 = new JSONObject();
                for (Object o1 : element.elements()) {
                    Element element1 = (Element) o1;
                    String code = element1.attributeValue("value");
                    String name = element1.getText();
                    jsonObject1.put(code,name);
                }
                jsonObject.put(key,jsonObject1);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
