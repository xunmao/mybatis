package com.xunmao.demo.pojo;

import java.util.Date;

// mysql> describe actor;
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | Field       | Type              | Null | Key | Default           | Extra                                         |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | actor_id    | smallint unsigned | NO   | PRI | NULL              | auto_increment                                |
// | first_name  | varchar(45)       | NO   |     | NULL              |                                               |
// | last_name   | varchar(45)       | NO   | MUL | NULL              |                                               |
// | last_update | timestamp         | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+

public class Actor {

    private int actorId;
    private String firstName;
    private String lastName;
    // MyBatis 在设置预处理语句（PreparedStatement）中的参数或从结果集中取出一个值时，
    // 都会用类型处理器将获取到的值以合适的方式转换成 Java 类型。
    // https://mybatis.org/mybatis-3/zh/configuration.html#类型处理器（typehandlers）
    // （xunmao）数据库中的 TIMESTAMP 类型会被转换成 java.util.Date 类型。
    private Date lastUpdate;

    public Actor(int actorId, String firstName, String lastName, Date lastUpdate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
                + lastUpdate + "]";
    }
}
