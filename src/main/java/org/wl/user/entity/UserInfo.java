package org.wl.user.entity;

import java.util.Date;

public class UserInfo {
    private int id;

    private String username;

    private String password;

    private Date registTime;

    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public String getStatus() {
        return status;
    }
}
