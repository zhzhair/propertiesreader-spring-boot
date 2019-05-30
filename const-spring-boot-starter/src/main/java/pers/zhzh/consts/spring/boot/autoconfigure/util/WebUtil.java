package pers.zhzh.consts.spring.boot.autoconfigure.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class WebUtil {

    public static String getMACAddress(){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            byte[] bytes = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            for (int i = 0; i < bytes.length; i++) {
                if(i != 0){
                    stringBuilder.append("-");
                }
                String s = Integer.toHexString(bytes[i] & 0xFF);
                stringBuilder.append(s.length() == 1? 0 + s : s);
            }
        } catch (UnknownHostException |SocketException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString().toUpperCase().replace("-","");
    }

    public static String[] getAllIpAddress(){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostName = inetAddress.getHostName();
            InetAddress[] inetAddresses = InetAddress.getAllByName(hostName);
            String[] ipAddresses = new String[inetAddresses.length];
            for (int i = 0; i < inetAddresses.length; i++) {
                ipAddresses[i] = inetAddresses[i].getHostAddress();
            }
            return ipAddresses;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
