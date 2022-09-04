# mybatis

此项目用于学习和尝试 MyBatis ( https://mybatis.org/mybatis-3/zh/index.html )框架的各种功能。

## 搭建环境

1. 在本地环境安装 MySQL 数据库。
2. 导入 Sakila 示例数据库。( https://dev.mysql.com/doc/sakila/en/ )

## 准备工作

### 创建 Maven 项目

使用以下命令创建 Maven 项目。参考：  
https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

```sh
mvn archetype:generate -DgroupId=com.xunmao.demo -DartifactId=mybatis -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

### 导入依赖

#### Connector/J

```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.29</version>
</dependency>
```

#### MyBatis

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.5.10</version>
</dependency>
```

### 目录结构

此项目采用 MyBatis 官方推荐的目录结构。

```
/my_application
  /bin
  /devlib
  /lib                <-- MyBatis *.jar 文件在这里。
  /src
    /org/myapp/
      /action
      /data           <-- MyBatis 配置文件在这里，包括映射器类、XML 配置、XML 映射文件。
        /mybatis-config.xml
        /BlogMapper.java
        /BlogMapper.xml
      /model
      /service
      /view
    /properties       <-- 在 XML 配置中出现的属性值在这里。
  /test
    /org/myapp/
      /action
      /data
      /model
      /service
      /view
    /properties
  /web
    /WEB-INF
      /web.xml
```

### 配置 MyBatis

TODO

## 第一个映射器（Mapper）

### 创建 MyBatis 工具类

TODO
1. 什么是单例模式？
2. 什么是静态单例模式？

https://mybatis.org/mybatis-3/zh/getting-started.html#作用域（scope）和生命周期

### 创建 Actor 类（POJO）

1. 根据 actor 表，创建 Actor 类（POJO）。
2. 更改 MyBatis 核心配置文件。

```xml
<!-- 是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。 -->
<setting name="mapUnderscoreToCamelCase" value="true" />
```

### 创建 ActorMapper 类（映射器）

**切记：将Mapper文件添加到MyBatis核心配置文件中。**

在 Mapper 添加基本的 CRUD 方法：

```java
public List<Actor> listActors();
public Actor findActorById(int actorId);
public void addActor(Actor actor);
public void updateActor(Actor actor);
public void deleteActor(int actorId);
```

只要创建好第一个方法，保证 MyBatis 核心配置文件正确且 MyBatis 工具类可以工作，  
后续再添加其他方法时，仅仅修改以下3个文件即可：

1. ActorMapper.java
2. ActorMapper.xml
3. ActorMapperTest.java

### 创建测试类

TODO

## 扩展 ActorMapper 类（映射器）

### 分页查询

以下方法默认返回所有的 Actor 记录，共计200条。

```java
public List<Actor> listActors();
```

现在需要添加分页功能，避免返回过多数据，造成资源浪费。

```java
public List<Actor> listActorsWithLimit(Map<String, Integer> parameterMap);
```

### 模糊查询

现在需要添加模糊查询功能，根据 Actor 的 last_name 进行模糊查询。

```java
public List<Actor> listActorsByLastNameLike(String value);
```

## 优化核心配置

### 开启日志

```xml
<!-- 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。可以从以下实现中选择一个：
  SLF4J | LOG4J（3.5.9 起废弃） | LOG4J2 | JDK_LOGGING | COMMONS_LOGGING | STDOUT_LOGGING | NO_LOGGING
  （xunmao） STDOUT_LOGGING 不需要导入依赖，可以直接使用。
 -->
<setting name="logImpl" value="STDOUT_LOGGING" />
```

### 类型别名

TODO

## 高级映射

### 一对一查询（ association ）

一个城市（ City 类）有一个地址（ Address 类）。

```xml
<!-- 
  https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#结果映射
 -->
<resultMap id="cityMap" type="com.xunmao.demo.pojo.City">
  <id property="cityId" column="city_id" />
  <result property="city" column="city" />
  <result property="countryId" column="country_id" />
  <result property="lastUpdate" column="last_update" />
  <association property="address" javaType="com.xunmao.demo.pojo.Address">
    <id property="addressId" column="address_id" />
    <result property="addressId" column="address_id" />
    <result property="address" column="address" />
    <result property="address2" column="address2" />
    <result property="district" column="district" />
    <result property="cityId" column="address_city_id" />
    <result property="postalCode" column="postal_code" />
    <result property="lastUpdate" column="address_last_update" />
  </association>
</resultMap>
```

### 一对多查询（ collection ）

一个国家（ Country 类）包含多个城市（ City 类）。

```xml
<!-- 
  https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#结果映射
 -->
<resultMap id="countryMap" type="com.xunmao.demo.pojo.Country">
  <id property="countryId" column="country_id" />
  <result property="lastUpdate" column="country_last_update" />
  <collection property="cities" javaType="list" ofType="com.xunmao.demo.pojo.City">
    <id property="cityId" column="city_id" />
    <result property="lastUpdate" column="city_last_update" />
  </collection>
</resultMap>
```

### 自动映射

在使用上述映射时，有个疑问，就是自动映射不知为何失效了。
1.  City 类和 Address 类的属性名与所对应的列名一致，理论上可以进行自动映射，实际上却失效了。
2.  Country 类和 City 类的属性名与所对应的列名一致，可以进行自动映射（故在此省略）。更新了 City 类之后，自动映射居然失效了，不知道为什么。

直接将结果集映射到 Map 上，输出的结果如下（手动格式化）：
```
{
  city_id=1
  city=A Corua (La Corua), 
  country_id=87, 
  last_update=2006-02-15 04:45:25.0, 
  address_id=56, 
  address=939 Probolinggo Loop, 
  address2=, 
  district=Galicia, 
  address_city_id=1, 
  postal_code=4166, 
  address_last_update=2014-09-25 22:33:08.0, 
}
```
Map 的 Key 跟结果集中的列名一致，符合预期。

省略 ResultMap 中跟 City 类相关的字段，输出结果如下（手动格式化）：
```
City [
  address=Address [
    address=939 Probolinggo Loop, 
    address2=, 
    addressId=56, 
    cityId=1, 
    district=Galicia, 
    lastUpdate=Thu Sep 25 22:33:08 CST 2014, 
    postalCode=4166
  ], 
  city=null, 
  cityId=0, 
  countryId=null, 
  lastUpdate=null
]
```
City 类中被省略的字段均为初始值，说明自动映射失效。

根据官方文档的解释，这种现象是符合预期的。

> 有三种自动映射等级：
> 
> `NONE` - 禁用自动映射。仅对手动映射的属性进行映射。  
> `PARTIAL` - 对除在内部定义了嵌套结果映射（也就是连接的属性）以外的属性进行映射。  
> `FULL` - 自动映射所有属性。  
> 默认值是 `PARTIAL`，这是有原因的。当对连接查询的结果使用 `FULL` 时，连接查询会在同一行中获取多个不同实体的数据，因此可能导致非预期的映射。

```xml
<!-- 指定 MyBatis 应如何自动映射列到字段或属性。
   NONE 表示关闭自动映射。
   PARTIAL 只会自动映射没有定义嵌套结果映射的字段。
   FULL 会自动映射任何复杂的结果集（无论是否嵌套）。
 -->
<setting name="autoMappingBehavior" value="PARTIAL"/>
```

## 动态 SQL

### 部分更新

现在需要添加部分更新功能，只更新 actor 表的指定的一部分字段。

```java
public void updateActorWithMap(Map<String, Object> parameterMap);
```

### 动态查询

新增动态查询功能，可以根据指定的条件进行查询，并且，当指定条件为空时，不使用该条件。

```java
// 根据根据输入的演员姓名，生成动态SQL查询
// 可以输入 first_name 或者 last_name 或者两者
// TODO 传入空 Map 时，返回 listActorsWithLimit 方法的默认结果
public List<Actor> listActorsLike(Map<String, String> parameterMap);
```

## 缓存

### 一级缓存

TODO

### 二级缓存

TODO
