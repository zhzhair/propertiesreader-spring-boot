package pers.zhzh.consts.spring.boot.autoconfigure.util;

public class StringUtil {
    public static String getFileName(String path){
        int end = path.indexOf(".");
        int begin = path.lastIndexOf("/");
        return path.substring(begin + 1,end);
    }
}
