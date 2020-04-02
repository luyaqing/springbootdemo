package com.example.springbootdemo;

import com.example.springbootdemo.model.UserRoleRelation;
import com.example.springbootdemo.service.UserRoleRelationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author BG362793
 * 用户角色关联关系
 */
public class UserRoleRelationTest extends SpringbootdemoApplicationTests{

    @Autowired
    private UserRoleRelationService relationService;

    @Test
    public void testGetRelationById(){

        UserRoleRelation relation = getRelation();

        relationService.insertRelation(relation);
        UserRoleRelation selectRelation = relationService.getRelationById(relation.getRelationId());

        assert selectRelation != null;
        assert relation.getRelationId().equals(selectRelation.getRelationId());
        assert relation.getRoleId().equals(selectRelation.getRoleId());
        assert relation.getUserId().equals(selectRelation.getUserId());
        assert relation.getComment().equals(selectRelation.getComment());

    }

    @Test
    public void testUpdateById(){

        UserRoleRelation relation = getRelation();
        relationService.insertRelation(relation);

        relation.setUserId(relation.getUserId()+"test");
        relation.setRoleId(relation.getRoleId()+"test");
        relation.setComment(relation.getComment()+"test");
        relationService.updateRelation(relation);

        UserRoleRelation selectRelation = relationService.getRelationById(relation.getRelationId());

        assert selectRelation != null;
        assert selectRelation.getUserId().equals(relation.getUserId());
        assert selectRelation.getRoleId().equals(relation.getRoleId());
        assert selectRelation.getComment().equals(relation.getComment());

    }

    @Test
    public void testDeleteById(){

        UserRoleRelation relation = getRelation();
        relationService.insertRelation(relation);
        assert relation != null;

        relationService.deleteRelationById(relation.getRelationId());
        UserRoleRelation selectRelation = relationService.getRelationById(relation.getRelationId());
        assert selectRelation == null;
    }

    private UserRoleRelation getRelation(){

        UserRoleRelation relation = new UserRoleRelation();
        relation.setRelationId(UUID.randomUUID().toString().substring(12));
        relation.setRoleId("role_id");
        relation.setUserId("user_id");
        relation.setComment("commment");

        return relation;
    }

}
