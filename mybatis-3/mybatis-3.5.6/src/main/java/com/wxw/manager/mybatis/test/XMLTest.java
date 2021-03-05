package com.wxw.manager.mybatis.test;


import com.wxw.dao.UserMapper;
import com.wxw.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName TestUser
 * @Description
 * @Author wxw
 * @Date2020/7/14 21:11
 * @Version
 **/
public class XMLTest {

  /**
   * 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。
   * SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。
   * 而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先配置的 Configuration 实例
   *  来构建出 SqlSessionFactory 实例。
   */
  public static void main(String[] args) throws IOException {
      //1、读取配置文件
      String resource = "config/mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);

      //2、初始化mybatis，创建SqlSessionFactory类实例
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      //3、创建Session实例  会指定执行器，默认开启一级缓存
      /*
        key的结构
        -1885415485:2692716209:com.gqzdev.mybatis.mapper.UserMapper.queryById:0:2147483647:select
          id, user_name, password, deptment, phone, email, status, create_date, remark
        from test_mybatis.user
        where id = ?:1:development
       */
      /**
       *  SqlSessionFactory 有六个方法创建 SqlSession 实例。
       *  通常来说，当你选择其中一个方法时，你需要考虑以下几点：
       *
       * 事务处理：你希望在 session 作用域中使用事务作用域，还是使用自动提交（auto-commit）？
       *          （对很多数据库和/或 JDBC 驱动来说，等同于关闭事务支持）
       * 数据库连接：你希望 MyBatis 帮你从已配置的数据源获取连接，还是使用自己提供的连接？
       * 语句执行：你希望 MyBatis 复用 PreparedStatement 和/或批量更新语句（包括插入语句和删除语句）吗？
       */
      SqlSession session = sqlSessionFactory.openSession();

      //方式一  可创建二级缓存
      User user = session.selectOne("com.gqzdev.mybatis.mapper.UserMapper.queryById",1);
      System.out.println(user.toString());


      //方式二
      /**
       *  使用映射器  获取Mapper
       * 传入接口，动态生成代理对象
       */
      UserMapper userMapper = session.getMapper(UserMapper.class);
      List<User> userList = userMapper.queryAllByLimit(1, 100);
      userList.stream().forEach(user1 -> {
        System.out.println(user1.toString());
      });

      User user1 = userMapper.selectByUid(1);
      System.out.println(user1);

      /*
        了解技术的本质
        MyBatis
          数据库源
            Driver url username password

          执行语句
            select insert update delete

          操作
            Connection  preparedStatement  execute


        怎么看源码

       */



  }
}
