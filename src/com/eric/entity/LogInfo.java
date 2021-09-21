package com.eric.entity;

/**
 * @Date: 2020/6/20 21:46
 * @author: Eric
 */
public class LogInfo {
    private String account;
    private String name;
    private String loginTime;

    public LogInfo() {
    }

    public LogInfo(String account, String name, String loginTime) {
        this.account = account;
        this.name = name;
        this.loginTime = loginTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
