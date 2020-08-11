package com.bioface.model.ext;

public class GetUsersManageResponse {
    private String username;
    private boolean enabled;


    public GetUsersManageResponse(String username, boolean enabled) {
        this.username = username;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
