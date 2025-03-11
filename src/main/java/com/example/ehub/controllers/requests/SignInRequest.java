package com.example.ehub.controllers.requests;

import java.io.Serializable;

public class SignInRequest implements Serializable {
    private String username;
    private String password;
    private String platform; // web, mobile, tablet
    private String deviceToken; // for push notify
    private String versionApp;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getVersionApp() {
        return versionApp;
    }
}
