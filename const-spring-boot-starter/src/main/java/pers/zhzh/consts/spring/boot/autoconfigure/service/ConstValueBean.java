package pers.zhzh.consts.spring.boot.autoconfigure.service;

import java.net.InetAddress;

public class ConstValueBean {
    private String mac;
    private String hostName;
    private String hostAddress;
    private String[] hostAllAddress;
    private InetAddress inetAddress;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String[] getHostAllAddress() {
        return hostAllAddress;
    }

    public void setHostAllAddress(String[] hostAllAddress) {
        this.hostAllAddress = hostAllAddress;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }
}
