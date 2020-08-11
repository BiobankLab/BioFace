package com.bioface.model.ext;

public class UserManageRequest {

    private String username;

    private boolean toEnable;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isToEnable() {
        return toEnable;
    }

    public void setToEnable(boolean toEnable) {
        this.toEnable = toEnable;
    }
}
