package com.example.springbootdemo.model;

/**
 * @author BG362793
 *  角色表
 */
public class RoleInfo implements java.io.Serializable{

    //角色id
    private String roleId;

    //角色名称
    private String roleName;

    //角色权限
    private String permission;

    //摘要
    private String comment;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", permission='" + permission + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
