package com.wxw.test;

import com.wxw.dao.UserMapper;
import com.wxw.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: XMLConfigTest
 *
 *  接口+映射文件 代理对象的产生交给spring容器
 *
 * @author: ganquanzhong
 * @date: 2020/7/15 11:42
 */
public class XMLConfigTest {

  public static void main(String[] args) {
    //1. 加载Spring容器、初始化容器
    ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");

    //2. 从容器中获取对象，进行其他操作
    UserMapper userMapper = app.getBean(UserMapper.class);
    User user = userMapper.queryById(1);
    System.out.println(user.toString());
  }
}
