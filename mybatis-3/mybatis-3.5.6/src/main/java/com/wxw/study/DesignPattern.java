package com.wxw.study;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.builder.xml.XMLStatementBuilder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.CacheBuilder;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.cache.Cache;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * @author ：wxw.
 * @date ： 9:49 2021/3/9
 * @description：Mybatis的设计模式
 * @link:
 * @version: v_0.0.1
 */
public class DesignPattern {
  public static void main(String[] args) {
    // LogFactory
  }

  private void iterator(){
    // NOTE: 2021/3/9 迭代器模式
    // Iterator
  }
  private void decorator(){
    // NOTE: 2021/3/9 装饰者模式
    // Cache
    /**
     * 用于装饰PerpetualCache的标准装饰器共有8个
     * package org.apache.ibatis.cache.decorators 装饰者们
     * 1、FifoCache：先进先出算法，缓存回收策略
     * 2、LoggingCache：输出缓存命中的日志信息
     * 3、LruCache：最近最少使用算法，缓存回收策略
     * 4、ScheduledCache：调度缓存，负责定时清空缓存
     * 5、SerializedCache：缓存序列化和反序列化存储
     * 6、SoftCache：基于软引用实现的缓存管理策略
     * 7、SynchronizedCache：同步的缓存装饰器，用于防止多线程并发访问
     * 8、WeakCache：基于弱引用实现的缓存管理策略
     */
  }
  private void adapter(){
    // NOTE: 2021/3/9 适配器（包装器模式）
    //Log
  }
  private void template(){
    // NOTE: 2021/3/9 模板方法模式
    // BaseExecutor : SIMPLE, REUSE, BATCH
  }
  private void composite(){
    // 组合模式
    // SqlSource#SqlNode
    // 对于实现该SqlSource接口的所有节点SqlNode，就是整个组合模式树的各个节点：
  }
  private void proxy(){
    /**
     * 代理模式
     * 该MapperProxy类实现了InvocationHandler接口，并且实现了该接口的invoke方法。
     * 通过这种方式，我们只需要编写Mapper.java接口类，当真正执行一个Mapper接口的时候，就会转发给MapperProxy.invoke方法
     * 而该方法则会调用后续的sqlSession.cud>executor.execute>prepareStatement等一系列方法，完成SQL的执行和返回。
     */
    // MapperProxyFactory

  }
  private void singleton(){
    // 单例模式
    //ErrorContext
    //LogFactory
  }
  private void factory(){
    // 工厂模式
    // SqlSessionFactory
    // LoggerFactory
  }
  private void Builder(){
    // 构建者模式 SqlSessionFactoryBuilder、XMLConfigBuilder、XMLMapperBuilder、XMLStatementBuilder、CacheBuilder
    // SqlSessionFactoryBuilder
    // XMLConfigBuilder
    // XMLMapperBuilder
    // XMLStatementBuilder
    // CacheBuilder
  }
}
