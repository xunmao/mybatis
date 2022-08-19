# mybatis

此项目用于学习和尝试MyBatis( https://mybatis.org/mybatis-3/zh/index.html )框架的各种功能。

## 搭建环境

1. 在本地环境安装MySQL数据库。
2. 导入Sakila示例数据库。（ https://dev.mysql.com/doc/sakila/en/ ）

## 准备工作

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

### 配置MyBatis

此项目采用MyBatis官方推荐的目录结构。

## 第一个Mapper

### 创建MyBatis工具类

TODO
1. 什么是单例模式？
2. 什么是静态单例模式？

https://mybatis.org/mybatis-3/zh/getting-started.html#作用域（scope）和生命周期

### 创建Actor类（POJO）

1. 根据actor表，创建Actor类（POJO）。
2. 更改MyBatis核心配置文件。
```xml
<!-- 是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。 -->
<setting name="mapUnderscoreToCamelCase" value="true" />
```

### 创建ActorMapper类

**切记：将Mapper文件添加到MyBatis核心配置文件中。**

在Mapper添加基本的CRUD方法：
```java
public List<Actor> listActors();
public Actor findActorById(int actorId);
public void addActor(Actor actor);
public void updateActor(Actor actor);
public void deleteActor(int actorId);
```

只要创建好第一个方法，保证MyBatis核心配置文件正确且MyBatis工具类可以工作，  
后续再添加其他方法时，仅仅修改以下3个文件即可：
1. ActorMapper.java
2. ActorMapper.xml
3. ActorMapperTest.java

### 创建测试类

## 扩展ActorMapper

### 分页查询

以下方法默认返回所有的Actor记录，共计200条。
```java
public List<Actor> listActors();
```

现在需要添加分页功能，避免返回过多数据，造成资源浪费。
```java
public List<Actor> listActorsWithLimit(Map<String, Integer> parameterMap);
```

### 模糊查询

现在需要添加模糊查询功能，根据Actor的last_name进行模糊查询。
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

## 高级映射

TODO
1. 理论上可以进行自动映射，实际上却失效了

直接将结果集映射到Map上，输出的结果如下（手动格式化）：
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
Map的Key跟结果集中的列名一致，符合预期。

省略ResultMap中跟City类相关的字段，输出结果如下（手动格式化）：
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
City类中被省略的字段均为初始值，说明自动映射失效。

### 一对一查询（ association ）

一个城市（City类）有一个地址（Address类）。

```xml
<!-- 
  https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#结果映射
  （xunmao） City 类和 Address 类的属性名与所对应的列名一致，理论上可以进行自动映射，实际上却失效了。
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

一个国家（Country类）包含多个城市（City类）。

```xml
<!-- 
  https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#结果映射
  （xunmao） Country 类和 City 类的属性名与所对应的列名一致，可以进行自动映射（故在此省略）。
  （xunmao）更新了 City 类之后，上面的自动映射居然失效了，不知道为什么。
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

## 动态SQL

### 部分更新

现在需要添加部分更新功能，只更新actor表的指定的一部分字段。
```java
public void updateActorWithMap(Map<String, Object> parameterMap);
```
