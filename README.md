# accessControl
疫情期间，小区进出管理系统，实例系统中没有区分小区，欢迎拉分支自行改造！！！

# 后端管理系统
## 1. 基于SpringBoot+MyBatis搭建
 SpringBoot：可以理解为一个整合了所有框架的框架，使用这个框架可以很方便的调用其他的框架，不需要考虑很多兼容性问题。简单来说，就是让开发人员的重点放在开发本身上。  
 
 MyBatis：是一个对象映射框架（ORM），简单来说就是把数据库中一条一条的数据，映射成代码中的对象，因为Java是面向对象的开发语言。
 
## 2. 开发流程

### 2.1 根据业务流程设计数据库
以本系统为例，小区进出管理系统设计了两个数据模型，第一个是住户模型，其DDL如下：
``` 
CREATE TABLE `pds_user_management` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_code` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '用户号（门牌号）',
  `registered_time` datetime DEFAULT NULL COMMENT '注册时间',
  `times_in` int(64) DEFAULT NULL COMMENT '进小区次数',
  `times_out` int(64) DEFAULT NULL COMMENT '出小区次数',
  `user_type` int(1) DEFAULT NULL COMMENT '用户类型  (0 表示正常 1表示禁止)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_code` (`user_code`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='住户模型';
```  
住户模型主要用来保存住户的基本信息、统计住户进出总次数。  
第二个是进出详情模型，其DDL如下：
``` 
CREATE TABLE `pds_access_management` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_code` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '用户号（门牌号）',
  `access_type` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '进出类型',
  `access_time` datetime DEFAULT NULL COMMENT '进出时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='进出详情模型';
``` 
进出详情模型主要用来保存住户的进出详情。住户模型使用user_code字段与进出详情模型关联，这两个模型之间是一对多的关系。

### 2.2 根据数据库使用MyBatis框架映射
使用 https://github.com/MacRylynn/mybatisGenerator 工程可以将数据直接从数据库映射成对象，并且生成一些CURD方法，供服务调用。其参数和使用方法在工程中有说明。

### 2.3 工程中的包和类的说明

#### 2.3.1 pom.xml
这个文件是系统中用到的所有Maven依赖，需要用到什么工具，就将这个工具的依赖导入进来。  
举例：以C++为例，假如代码中要用到OpenCV，首先需要去下载OpenCV，然后安装、添加环境变量、在VS中配置、在工程中#include......非常麻烦，  在Spring项目中只需要在pom.xml中添加如下类似依赖（示例依赖并不是OpenCV）：
``` 
  <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
   </dependency>
``` 
系统会自动从网络Maven库中加载该依赖包，就可以使用mysql于Java之间的连接方法了。

#### 2.3.2 application.properties 
是系统的总配置，包括数据库地址、网络端口号等等，很多参数可以自行百度。

#### 2.3.3 logback.xml
系统日志的配置，使用日志可以观察系统的运行状态，很多参数可以自行百度。

#### 2.3.4 mybatis-config.xml
MyBatis从数据库映射到对象的配置，很多参数可以自行百度，在pom.xml文件中引用。

#### 2.3.5 entity包
包中的所有类，都是使用2.2中的工程生成的，是数据库对应生成的Java类

#### 2.3.5 mapper包
两个mapper包都是使用2.2中的工程生成的（带有注释的是手动写的，主要适应不同的业务需求）。main中的mapper包中的类，是操作数据库的方法申明，resource中的mapper包是main中mapper包中方法的实现。  
resource中的mapper包下的extend包是手动写的sql（带有注释的那些方法的实现）

#### 2.3.6 service包
包中是主要的业务方法的申明和实现。接口中是业务流程会用到的所有方法的申明。service包中的impl包是接口的实现，主要体现Java面向接口编程，不对外暴露任何业务细节的思想。

#### 2.3.7 domain包
包中的类是与Web直接交互的类，包括请求类、响应类等，是对数据进行了包装，称之为数据传输层。因为在实际项目中，考虑到安全性，往往不会将数据库映射出来的entity直接与Web进行交互。

#### 2.3.8 controller包
可以理解为连接前端和后端的方式。类的最上方的注解@RequestMapping("/web/wx")表示根路径，使用一下方法得到所有住户的信息。
``` 
   @PostMapping("/getallrecords")
    public CommonResponse<List<UserRes>> getAllRecords() {
        logger.info("AccessManagementCtrl|getAllRecords，查询所有用户进出信息");
        CommonResponse<List<UserRes>> res = new CommonResponse<>();
        List<UserRes> result = accessManagementService.getAllRecords();
        res.setResultData(result);
        return res;
    }
 ``` 
@PostMapping("/getallrecords")表示使用post方法，并且在根路径中加分路径，即前端使用电脑ip和application.properties的端口号+根路径+分路径就能访问这个方法，比如：http//:127.0.0.1:8088/web/wx/getallrecords。具体使用的时候均使用post方法，保证一定的安全性。  
整个实现方法中入参为空，返回有所的住户信息。

#### 2.3.9 AccessAplication类
是整个项目的启动类，如果需要打包并且在tomcat运行，则需要继承SpringBootServletInitializer类，详情可以自行百度。

## 3. 使用流程
编码：设计数据库-> 数据库映射为对象-> 业务中操作对象-> 与前端交互
访问：前端请求-> controller层-> 调用service中的方法-> 调用mapper中的方法-> 实现数据库的增删改查
