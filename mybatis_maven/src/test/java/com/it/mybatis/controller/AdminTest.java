package com.it.mybatis.controller;


import com.it.mybatis.pojo.Admin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AdminTest {

    @Test
    public void testMybatis() {
        try {
            //加载核心配置文件
            InputStream resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
            //创建sqlSessionFactory工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            //获得sqlSession对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<Admin> objectList = sqlSession.selectList("com.it.mybatis.mapper.AdminMapper.findAll");
            System.out.println(objectList);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
