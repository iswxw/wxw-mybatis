package com.wxw.test;

import com.gqzdev.mybatis.spring.config.MyBatisConfig;
import com.gqzdev.mybatis.spring.mapper.UserMapper;
import com.gqzdev.mybatis.spring.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @ClassName: AnnotationConfigTest
 *
 *  通过MyBatis的全注解配置方法
 * @MapperScan注解  扫描mapper文件
 *
 *
 * @author: ganquanzhong
 * @date: 2020/7/15 11:42
 */
public class AnnotationConfigTest {
  public static void main(String[] args) {

    AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyBatisConfig.class);

    String[] beanDefinitionNames = app.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      System.out.println(beanDefinitionName);
    }

    UserMapper userMapper = app.getBean(UserMapper.class);
    List<User> users = userMapper.queryAllByLimit(0, 10);
    for (User user : users) {
      System.out.println(user.toString());
    }
  }

}
