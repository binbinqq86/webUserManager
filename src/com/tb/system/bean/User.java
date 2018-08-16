package com.tb.system.bean;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户id
     */
    public int userId;
    /**
     * 用户角色，默认0(未激活)
     * 一级管理员-1，二级管理员-2，等等类推
     * 普通用户按等级递增(1,2,3...)
     */
    public int role;
    /**
     * 用户名
     */
    public String userName;
    /**
     * 用户昵称，可空(空的话为用户名)
     */
    public String nickName;
    /**
     * 用户密码
     */
    public String password;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
