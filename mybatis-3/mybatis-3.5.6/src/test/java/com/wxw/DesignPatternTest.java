package com.wxw;

import org.apache.ibatis.builder.CachedAuthorMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

/**
 * @author ：wxw.
 * @date ： 10:34 2021/3/9
 * @description： 设计模式测试
 * @link:
 * @version: v_0.0.1
 */
public class DesignPatternTest {

  @Test
  public void test_1(){
    try {
      // 基本mybatis环境
      // 1.定义mybatis_config文件地址
      String resources = "mybatis_config.xml";
      // 2.获取InputStreamReaderIo流
      Reader reader = Resources.getResourceAsReader(resources);
      // 3.获取SqlSessionFactory
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      // 4.获取Session
      SqlSession sqlSession = sqlSessionFactory.openSession();
      // 5.操作Mapper接口
      CachedAuthorMapper cachedAuthorMapper = sqlSession.getMapper(CachedAuthorMapper.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
