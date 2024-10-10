package org.example.model;

public class User {
    private String userName;
    private String department;
    private String userId;

    public User(String userName, String department, String userId) {
        this.userName = userName;
        this.department = department;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
