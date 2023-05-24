package com.itheima.model.domain;

import java.util.Date;
/**
 * @Classname UserMapper
 * @Description 用户实体类
 * @Date 2023-5-17
 * @Created by LMF
 */
public class User {
    private Integer id;  //用户id
    private String username;  //用户名
    private String email; //用户邮箱
    private Date created; //用户创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                '}';
    }
}
