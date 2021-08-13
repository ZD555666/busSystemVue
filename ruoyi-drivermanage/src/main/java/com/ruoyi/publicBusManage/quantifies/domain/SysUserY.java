package com.ruoyi.publicBusManage.quantifies.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/12 15:21
 * @desc:before SET
 */
public class SysUserY {
    private int userId;
    private int driverId;
    private String userName;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

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

    @Override
    public String toString() {
        return "SysUserY{" +
                "userId=" + userId +
                ", driverId=" + driverId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
