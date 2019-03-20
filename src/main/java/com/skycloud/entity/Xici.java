package com.skycloud.entity;

/**
 * Created by angel on 2019/1/4.
 */
public class Xici {

    private Integer id;
    private String ip;
    private Integer port;
    private String address;

    public Xici() {
    }

    public Xici(Integer id, String ip, Integer port, String address) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
