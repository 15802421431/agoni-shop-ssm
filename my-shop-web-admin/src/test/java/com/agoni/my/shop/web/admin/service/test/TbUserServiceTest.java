package com.agoni.my.shop.web.admin.service.test;

import com.agoni.my.shop.domain.TbUser;
import com.agoni.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> list = tbUserService.selectAll();
        for(TbUser tbUser:list){
            System.out.println(tbUser.getCreated());
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setCreated(new Date());
        tbUser.setEmail("agoni@funtl.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setPhone("1588888888");
        tbUser.setUpdated(new Date());
        tbUser.setUsername("agoni");
        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete(){
        tbUserService.delete(38L);
    }

    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(39L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testUpdate(){
        TbUser tbUser = tbUserService.getById(39L);
        tbUser.setUsername("agoni");
        tbUserService.update(tbUser);
    }

}
