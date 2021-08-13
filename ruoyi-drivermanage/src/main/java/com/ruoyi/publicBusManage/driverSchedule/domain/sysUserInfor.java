package com.ruoyi.publicBusManage.driverSchedule.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/13 14:59
 * @desc:before SET
 */
public class sysUserInfor {
    private String userName;
    private String password;
    private String value;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "sysUserInfor{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
