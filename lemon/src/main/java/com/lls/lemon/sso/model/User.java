package com.lls.lemon.sso.model;

/************************************
 * User
 * @author liliangshan
 * @date 2019-04-09
 ************************************/
public class User {

    private Long id;
    private String name;
    private String password;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
