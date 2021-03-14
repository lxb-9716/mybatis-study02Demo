package com.it.mybatis.controller;

import com.it.mybatis.mapper.AdminMapper;
import com.it.mybatis.vo.AdimCustomer;
import com.it.mybatis.vo.AdminVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisCache {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void buildSqlSessionFactory() {
        //加载核心配置文件
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("myBatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建sqlSessionFactory工厂对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    /*
     * mybatis框架一级缓存的测试注意事项：
     * 1、mybatis的一级缓存是sqlSession级别的缓存，mybatis框架默认开启
     * 2、不同sqlSession对象之间的缓存是互相隔离的，同一个sqlSession对象对同一个sql的多次查询会从缓存中进行读取
     * */
    @Test
    public void sqlSessionCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper1 = sqlSession.getMapper(AdminMapper.class);
        AdminVo adminVo = new AdminVo();
        AdimCustomer adimCustomer = new AdimCustomer();
        adimCustomer.setId(5);
        adminVo.setAdimCustomer(adimCustomer);
        AdimCustomer adimCustomer1 = mapper1.findAdminById(adminVo);
        System.out.println(adimCustomer1);
        System.out.println("==============update更新admin数据=========================");
        adimCustomer1.setUsername("修改数据");
        adimCustomer1.setPassword("99999");
        mapper1.updateAdmin(adimCustomer1);
        sqlSession.commit();
        System.out.println("==============update更新数据完毕=========================");
        AdimCustomer adimCustomer2 = mapper1.findAdminById(adminVo);
        System.out.println(adimCustomer2);
        sqlSession.close();
    }

    /*
     * mybatis框架的二级缓存的测试需要注意以下几点：
     * 1、mybatis框架的二级缓存是mapper级别的,mybatis的二级缓存区域是根据mapper.xml映射文件的nameSpace来划分的
     * 2、对于mybatis框架中的二级缓存，不同的sqlSession之间可以公用，作用的区域范围更大
     * 3、要使用二级缓存需要进行相关的配置(mapper.xml映射文件中配置<cache/>)
     * */
    @Test
    public void mapperCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        AdminMapper mapper1 = sqlSession1.getMapper(AdminMapper.class);
        AdminMapper mapper2 = sqlSession2.getMapper(AdminMapper.class);
        AdminVo adminVo1 = new AdminVo();
        AdimCustomer adimCustomer1 = new AdimCustomer();
        adimCustomer1.setId(6);
        adminVo1.setAdimCustomer(adimCustomer1);
        //第一个sqlsession进行查询操作
        AdimCustomer adimCustomer01 = mapper1.findAdminById(adminVo1);
        System.out.println(adimCustomer01);
        sqlSession1.close();
        //第一个sqlsession进行查询操作
        System.out.println("===========第二个sqlSession执行相同的sqlSession验证mybatis的二级缓存======================");
        //第二个sqlsession进行查询操作
        AdimCustomer adimCustomer02 = mapper2.findAdminById(adminVo1);
        System.out.println(adimCustomer02);
        sqlSession2.close();
    }
}
