package com.example.springbootdemo.model;

/**
 * 用户表
 * @author BG362793
 */
public class UserInfo implements java.io.Serializable{

    //用户ID
    private String userId;

    //用户登录ID
    private String loginId;

    //用户名
    private String userName;

    //用户密码
    private String userPwd;

    //用户所属部门
    private String department;

    //联系方式
    private String phone;

    //用户的状态  0-正常；1-禁用；
    private String status;

    //上次登录时间
    private String lastLoginTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", loginId='" + loginId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                '}';
    }
}
