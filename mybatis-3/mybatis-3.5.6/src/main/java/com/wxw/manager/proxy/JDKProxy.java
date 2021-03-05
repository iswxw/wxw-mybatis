package com.wxw.manager.proxy;


import com.google.common.collect.Maps;
import com.wxw.domain.User;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JDKProxy
 * @author: wxw
 * @date: 2020/7/16 14:18
 */
public class JDKProxy {

  public static void main(String[] args) {
    // 通过JDK动态代理，创建一个mapper的代理对象
    UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), new Class<?>[]{UserMapper.class}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用方法是"+method.getName());
        System.out.println("方法参数是"+Arrays.toString(args));

        Map<String, Object> argNameMap = buildMethodArgNameMap(method, args);
        Select annotation = method.getAnnotation(Select.class);
        // 获取注解sql内容
        if (annotation != null){
          String[] value = annotation.value();
          String sql = value[0];
          sql = parseSQL(sql,argNameMap);
          System.out.println("执行的sql是"+sql);
        }
        return null;
      }
    });

    userMapper.selectUserList();

    System.out.println("=============================================");

    userMapper.selectUserById(1,"gqzdev");
  }

  // 处理参数
  public static Map<String,Object> buildMethodArgNameMap(Method method, Object[] args){
    Map<String, Object> nameArgMap = Maps.newHashMap();
    //获取方法参数
    Parameter[] parameters = method.getParameters();
    //匿名 final
    int index[] = {0};
    //java8 特性
    // java1.8是支持从反射获取参数名的
    Arrays.asList(parameters).forEach(parameter -> {
      String name = parameter.getName();
      if (name!=null) {
        System.out.println("参数的名称是"+name);
        nameArgMap.put(name, args[index[0]]);
        index[0]++;
      }
    });
    return nameArgMap;
  }

  //解析SQL
  /**
   * 将方法中传入的参数，解析到注解中的sql语句里面
   */
  public static String parseSQL(String sql,Map<String,Object> nameArgMap){
    StringBuilder stringBuilder = new StringBuilder();
    int length = sql.length();

    for (int i = 0; i < length ; i++) {
      char c = sql.charAt(i);
      if (c == '#'){
        //判断下一字符是不是{
        int nextIndex = i+1;
        char nextChar = sql.charAt(nextIndex);
        if (nextChar != '{'){
          throw new RuntimeException(String.format("这里应该为#{\n\tsql:%s\n\tindex:%d",stringBuilder.toString(),nextIndex));
        }
        StringBuilder argSB = new StringBuilder();
        //解析替换参数
        i = parseSQLArg(argSB,sql,nextIndex);
        //
        String argName = argSB.toString();
        Object argValue = nameArgMap.get(argName);
        if (argValue == null){
          // 在sql中找不到对应的参数名
          throw  new RuntimeException(String.format("找不到参数：%s",argName));
        }
        stringBuilder.append(argValue.toString());
        continue;
      }
      stringBuilder.append(c);
    }
    return stringBuilder.toString();
  }

  /**
   * argSB  记录参数名
   * return 返回 } 结束的定位 位置
   */
  private static int parseSQLArg(StringBuilder argSB, String sql, int nextIndex) {
    nextIndex++;
    for (; nextIndex < sql.length(); nextIndex++) {
      char c = sql.charAt(nextIndex);
      if (c != '}'){
        argSB.append(c);
        continue;
      }else {
        return nextIndex;
      }
    }
    throw new RuntimeException();
  }


  interface UserMapper{

    @Select("select * from user")
    List<User> selectUserList();

    @Select("select * from user where id =#{id} and user_name=#{username}")
    User selectUserById(int id, String username);
  }

}
