package com.wxw.manager.jdbc;


import com.wxw.domain.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @ClassName: JDBCConnection
 * @author: wxw
 * @date: 2020/7/16 9:40
 */

/**
 * 1、数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，
 *    如果使用数据库链接池可解决此问题。
 * 2、Sql语句在代码中硬编码，造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。
 * 3、使用preparedStatement向占有位符号传参数存在硬编码，因为sql语句的where条件不一定，
 *    可能多也可能少，修改sql还要修改代码，系统不易维护。
 *
 * 4、对结果集解析存在硬编码（查询列名），sql变化导致解析代码变化，系统不易维护，
 *    如果能将数据库记录封装成pojo对象解析比较方便。
 */
public class JDBCConnection {

  /*
    注意驱动包版本，8.0后的写法
   */
  private static String driverName = "com.mysql.cj.jdbc.Driver";
  private static String url = "jdbc:mysql://127.0.0.1:3306/test_mybatis?useCharacter=UTF-8&serverTimezone=Asia/Shanghai";
  private static String username = "root";
  private static String password = "ganquanzhong";

  public static void main(String[] args)  {

    try {
      // 1. 加载驱动
      Class.forName(driverName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    ArrayList<User> userList = new ArrayList<>();
    try {
      // 2.获取连接
      connection = DriverManager.getConnection(url,username,password);
      // 3.创建Statement
      /*
       Statement stmt = con.createStatement() ;
       PreparedStatement pstmt = con.prepareStatement(sql) ;
       CallableStatement cstmt = con.prepareCall("{CALL demoSp(? , ?)}") ;
       */
      statement = connection.createStatement();
      // 4. 执行操作
      /*
        Statement接口提供了三种执行SQL语句的方法：executeQuery 、executeUpdate和execute
       */
      resultSet = statement.executeQuery("select * from user");
      // 5. 遍历结果集
      while (resultSet.next()){
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        userList.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      // 6. 关闭资源
      if (resultSet!=null){
        try {
          resultSet.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (statement != null){
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (connection != null){
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    for (User user : userList) {
      System.out.println(user.toString());
    }

  }

}
