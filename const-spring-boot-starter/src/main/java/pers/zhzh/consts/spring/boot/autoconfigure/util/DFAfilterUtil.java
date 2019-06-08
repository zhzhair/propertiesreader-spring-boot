package pers.zhzh.consts.spring.boot.autoconfigure.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFAfilterUtil {
    /**
     * 检查文字中的敏感字符
     * @param text 文本内容
     * @param map 敏感词dfs数树
     * @return 敏感词的集合
     */
    public static String getSensitiveWordTextAll(String text, Map<String, Object> map){
        if(!hasSensitiveWordAll(text,map)) return text;
        String returnStr = text;
        Set<String> set = getSensitiveWordAll(text,map);
        for (String string : set) {
            returnStr = returnStr.replace(string,getStars(string.length()));
        }
        return returnStr;
    }

    /**
     * 将每隔字符替换成*
     * @param length 长度
     * @return *...
     */
    private static String getStars(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }

    /**
     * 检查文字中的敏感字符
     * @param text 文本内容
     * @param map 敏感词dfs数树
     * @return 敏感词的集合
     */
    public static Set<String> getSensitiveWordAll(String text, Map<String, Object> map){
        Set<String> set = new HashSet<>();
        if(StringUtils.hasText(text) && map != null && !map.isEmpty()){
            String[] textArr = text.replaceAll(" ","").split("，|。|！|？");
            for (String textPart : textArr) {
                for (int i = 0; i < textPart.length(); i++) {
                    Set<String> set1 = getSensitiveWordBegin(textPart,map,i);
                    if(!set1.isEmpty()){
                        set.addAll(set1);
                    }
                }
            }
        }
        return set;
    }

    /**
     * 检查文字中的敏感字符
     * @param text 文本内容
     * @param map 敏感词dfs数树
     * @return 是否有敏感词
     */
    public static boolean hasSensitiveWordAll(String text, Map<String, Object> map){
        if(StringUtils.hasText(text) && map != null && !map.isEmpty()){
            System.err.println(text.replaceAll("\\s*",""));
            String[] textArr = text.replaceAll("\\s*","")
                    .split("，|。|！|？|\\,|\\.|\\!|\\?");
            for (String textPart : textArr) {
                for (int i = 0; i < textPart.length(); i++) {
                    boolean bool = hasSensitiveWordBegin(textPart,map,i);
                    if(bool){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查文字中的敏感字符
     * @param text 用户输入的文本内容
     * @param map 敏感词dfs数树
     * @param beginIndex 被检索文本内容的起始下标
     * @return 如果存在，则返回敏感词，不存在返回null
     */
    @SuppressWarnings("unchecked")
    private static Set<String> getSensitiveWordBegin(String text, Map<String, Object> map, int beginIndex){
        Set<String> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();     //匹配标识数默认为0
        char word;
        Map<String, Object> nowMap = map;
        for(int i = beginIndex; i < text.length(); i++){
            word = text.charAt(i);
            if(nowMap != null && nowMap.get(String.valueOf(word)) != null){     //存在，则判断是否为最后一个
                nowMap = (Map)nowMap.get(String.valueOf(word));     //获取指定key
                stringBuilder.append(word);
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    set.add(stringBuilder.toString());
                }
            }else{
                break;
            }
        }
        return set;
    }

    /**
     * 检查文字中是否有敏感字符
     * @param text 用户输入的文本内容
     * @param map 敏感词dfs数树
     * @param beginIndex 被检索文本内容的起始下标
     * @return 是否存在
     */
    @SuppressWarnings("unchecked")
    private static boolean hasSensitiveWordBegin(String text, Map<String, Object> map, int beginIndex){
        char word;
        Map<String, Object> nowMap = map;
        for(int i = beginIndex; i < text.length(); i++){
            word = text.charAt(i);
            if(nowMap != null && nowMap.get(String.valueOf(word)) != null){     //存在，则判断是否为最后一个
                nowMap = (Map)nowMap.get(String.valueOf(word));     //获取指定key
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    return true;
                }
            }else{
                break;
            }
        }
        return false;
    }

    /**
     * 将敏感词库放到HashMap树中
     * @param keyWordSet 敏感词文件路径
     * @return dfs树
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getSensitiveWordToHashMap(Set<String> keyWordSet) {
        Map<String,Object> sensitiveWordMap = new HashMap<>(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        Map<String, Object> nowMap; //负责sensitiveWordMap拼接树丫^_^
        Map<String, Object> newWordMap; //负责放isEnd
        //迭代keyWordSet
        for (String key : keyWordSet) {
            nowMap = sensitiveWordMap; //对象引用，不只是赋值
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(String.valueOf(keyChar));
                /*如果树枝上找不到字符，直接拼接字符的结束标记，
                否则直接取下字符对应的树枝，直到树枝上找不到字符，又循环前述步骤*/
                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (Map)wordMap;
                }else{     //不存在则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWordMap = new HashMap<>();
                    newWordMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(String.valueOf(keyChar), newWordMap);
                    nowMap = newWordMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
        return sensitiveWordMap;
    }

}
