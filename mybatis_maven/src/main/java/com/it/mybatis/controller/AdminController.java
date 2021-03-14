package com.it.mybatis.controller;

import com.it.mybatis.mapper.AdminMapper;
import com.it.mybatis.pojo.Admin;
import com.it.mybatis.vo.AdimCustomer;
import com.it.mybatis.vo.AdminVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminController {
    public static void main(String[] args) {
        try {
            System.out.println("我当前的所有操作都是在新创建的分支上进行的");
            //加载核心配置文件
            InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
            //创建sqlSessionFactory工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            //获得sqlSession对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            /**
             * mybatis中的中一级缓存和二级缓存测试
             */
            /* *//* List<Admin> objectList = sqlSession.selectList("com.it.mybatis.mapper.AdminMapper.findAll");*//*
            AdminMapper mapper1 = sqlSession.getMapper(AdminMapper.class);
            List<Admin> all = mapper1.findAll();
            System.out.println(all);
          *//*  System.out.println(objectList);*//*
             *//*Admin admin = sqlSession.selectOne("com.it.mybatis.mapper.AdminMapper.findById", 1);*/
            AdminVo adminVo = new AdminVo();
            AdimCustomer adimCustomer = new AdimCustomer();
            adimCustomer.setId(null);
            /*动态sql条件拼接查询*/
            adimCustomer.setUsername(null);
            List<Integer> ids = new ArrayList<Integer>();
            adminVo.setAdimCustomer(adimCustomer);
            ids.add(5);
            ids.add(6);
            ids.add(7);
            adminVo.setIds(ids);
            List<AdimCustomer> adimCustomer1 = sqlSession.getMapper(AdminMapper.class).findByAuto(adminVo);
            System.out.println("=======根据id条件查询==========");
            System.out.println(adimCustomer1);
            System.out.println(adimCustomer1.size());
         /*   List<Admin> admins = sqlSession.selectList("com.it.mybatis.mapper.AdminMapper.findByUserName", "%四%");
            System.out.println("======模糊查询=========");
            System.out.println(admins);
            System.out.println(admins.size());
            System.out.println("======添加数据=========");
        *//*    Admin admin2 = new Admin();
            admin2.setId(13);
            admin2.setUsername("王⑦");
            admin2.setPassword("123");
            admin2.setSubmitTime(new Date());
            int insert = sqlSession.insert("com.it.mybatis.mapper.AdminMapper.insertAdmin", admin2);
            System.out.println(insert);
            System.out.println(admin2.getId());
            //手动提交事务
            sqlSession.commit();*//*
            System.out.println("=========修改数据==============");
            Admin admin1 = new Admin();
            admin1.setId(9);
            admin1.setUsername("王三");
            admin1.setPassword("1242252");
            admin1.setSubmitTime(new Date());
            int update = sqlSession.update("com.it.mybatis.mapper.AdminMapper.updateAdmin", admin1);
            System.out.println(update);
            sqlSession.commit();
            System.out.println("================删除数据===================");
            Admin admin2 = new Admin();
            admin2.setId(10);
            AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
            mapper.deleteAdminById(3);
            *//*   int delete = sqlSession.delete("com.it.mybatis.mapper.AdminMapper.deleteAdminById", admin2);*//*
            sqlSession.commit();*/
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
