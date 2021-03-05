1. mybatis 如何获取数据源？
"main"@1 in group "main": RUNNING
parseConfiguration:117, XMLConfigBuilder {org.apache.ibatis.builder.xml}
parse:97, XMLConfigBuilder {org.apache.ibatis.builder.xml}
build:78, SqlSessionFactoryBuilder {org.apache.ibatis.session}
build:64, SqlSessionFactoryBuilder {org.apache.ibatis.session}
main:35, XMLTest {com.gqzdev.mybatis.test}


Mybatis处理流程

    SqlSessionFactoryBuilder
        parse解析Configuration
        build构建SqlSessionFactory

            open打开session，得到SQLSession ，这个是mybatis在java中的核心api
                【将xml配置文件解析读取到configuration中】

                query查询 Executor（有三种执行器）

                    newStatementHandler
                        statementHandler

                            HandlerResultSets
                                ResultSetHandler

