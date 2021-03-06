package com.lbj.bean;


import java.util.Map;


public class UserInfo {
    /**
     * 用户姓名
     */
    private String userName = null;

    /**
     * 用户账户
     */
    private String userAccount = null;

    /**
     * 返回的其他用户属性
     */
    private Map<String,Object> attributes;

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getUserAccount () {
        return userAccount;
    }

    public void setUserAccount (String userAccount) {
        this.userAccount = userAccount;
    }

    public Map<String, Object> getAttributes () {
        return attributes;
    }

    public void setAttributes (Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
