package com.course.utils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class MybatisSqlSession {
   public static SqlSession getSqlsession() throws IOException {
       //获取配置的资源文件
       Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
       SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
       //执行配置文件中sql语句的对象
       SqlSession sqlSession = factory.openSession();
       return sqlSession;
   }
}
