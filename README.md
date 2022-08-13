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

### 类型别名

### 开启日志

## 高级映射

### 多对一查询

### 一对多查询

## 动态SQL

### 部分更新

现在需要添加部分更新功能，只更新actor表的指定的一部分字段。
```java
public void updateActorWithMap(Map<String, Object> parameterMap);
```
