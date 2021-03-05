package com.wxw.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: MybatisConfig
 * @author: wxw
 * @date: 2020/7/15 10:58
 */

@Configuration
@ComponentScan("com.wxw.service")
@MapperScan(basePackages = "com.gqzdev.mybatis.spring.mapper")
public class MyBatisConfig {

  @Bean
  public DataSource dataSource(){
    Properties properties = new Properties();
    InputStream is = MyBatisConfig.class.getClassLoader().getResourceAsStream("jdbc.properties");
    try {
      // 加载配置文件
      properties.load(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
    dataSource.setUrl(properties.getProperty("jdbc.url"));
    dataSource.setUsername(properties.getProperty("jdbc.username"));
    dataSource.setPassword(properties.getProperty("jdbc.password"));

    return dataSource;
  }


  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource());
//    factoryBean.setMapperLocations("classpath:mapper/*.xml");
    return factoryBean.getObject();
  }


}
