package com.example.springbootdemo.model;

/**
 * @author  BG362793
 *  用户角色的关系
 */
public class UserRoleRelation implements java.io.Serializable{

    private String relationId;

    //用户id
    private String userId;

    //角色id
    private String roleId;

    //comment
    private String comment;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "UserRoleRelation{" +
                "relationId='" + relationId + '\'' +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
