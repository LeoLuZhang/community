# community
##  常用Git command
### Push(Local ->Remote) 
* git status (Red)
* git add . 
* Git status（Green）
* git commit -m "Merage local workspace and remote"
* git push -u origin master（第二次直接git push）

### Pull(Remote->Local)
* git pull 

### MyBatis:
1. @MapperScan("life.majiang.community.mapper") in application.java
2. 属性文件
*  (打开驼峰命名方式，默认是关闭)mybatis.configuration.map-underscore-to-camel-case=true
*  (设置aliases package)mybatis.type-aliases-package=life.majiang.community.mapper
*  (设置classpath的配置文件)mybatis.mapper-locations=classpath:mapper/*.xml
3. generatorConfig.xml 配置

### 异常处理
1. BasicErrorController 是默认的实现方式，可以通过实现ErrorController接口，重新自己去定义我们自己的异常页面。 
2. 同时也以通过@ControllerAdvice配合ExceptionHandler去处理所以上下文中业务异常。

### flyway
* mvn flyway:migrade ：数据库版本管理，执行sql脚本；
* mvn -Dmybatis.generator.overwrite=true mybatis-generator:generategir 

***
* sample community: https://github.com/PuZhiweizuishuai/community