package com.wxw.manager.proxy;

import java.lang.reflect.Method;

/**
 * @ClassName: ProxyDemo
 * @author: wxw
 * @date: 2020/7/16 15:12
 */
public class ProxyDemo {
  public static void main(String[] args) {

  }

  public Test createProxyInstance(final InvokeHandler handler, final Class<?> clazz) {
    return new Test() {
      @Override
      public void say() {
        try {
          Method sayMethod = clazz.getMethod("say");
          handler.invoke(this, sayMethod);
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }
      }};
  }

  interface Test {
      public void say();
  }

  interface InvokeHandler {
    Object invoke(Object obj, Method method, Object... args);
  }

}
