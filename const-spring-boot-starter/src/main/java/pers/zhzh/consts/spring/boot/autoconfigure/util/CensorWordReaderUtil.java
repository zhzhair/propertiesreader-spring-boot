package pers.zhzh.consts.spring.boot.autoconfigure.util;

import org.apache.commons.codec.Charsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CensorWordReaderUtil {
    /**
     * 将敏感词放到Set集合中
     * @param path 敏感词文件路径
     * @param wordCount 敏感词个数
     * @return 敏感词集合
     */
    public static Set<String> toSet(String path,Integer wordCount){
        InputStream inputStream = CensorWordReaderUtil.class.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
        String line;
        Set<String> set = new HashSet<>(wordCount);//假设有3000个敏感词，初始化容量，避免扩容
        try {
            while((line = reader.readLine()) != null) {
                set.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 从文本中查询是否有铭感词
     * @param text 查询文本
     * @param set 敏感词的集合
     * @return 是否有敏感词
     */
    public static boolean hasCensorWord(String text, Set<String> set){
        for (String string : set) {
            if(text.contains(string)) return true;
        }
        return false;
    }

    /**
     * 从文本中查询铭感词
     * @param text 查询文本
     * @param keyWordSet 敏感词的集合
     * @return 敏感词的集合
     */
    public static Set<String> getCensorWord(String text, Set<String> keyWordSet){
        Set<String> set = new HashSet<>();
        for (String string : keyWordSet) {
            if(text.contains(string)){
                set.add(string);
            }
        }
        return set;
    }

    /**
     * 从文本中查询铭感词
     * @param text 查询文本
     * @param keyWordSet 敏感词的集合
     * @return 敏感词的集合
     */
    public static String getCensorWordText(String text, Set<String> keyWordSet){
        if(!hasCensorWord(text,keyWordSet)) return text;
        Set<String> set = getCensorWord(text,keyWordSet);
        String returnStr = text;
        for (String string : set) {
            returnStr = returnStr.replace(string,"**");
        }
        return returnStr;
    }
}
