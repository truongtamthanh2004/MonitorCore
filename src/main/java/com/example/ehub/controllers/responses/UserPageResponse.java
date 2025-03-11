package com.example.ehub.controllers.responses;

import java.io.Serializable;
import java.util.List;

public class UserPageResponse extends PageResponseAbstract implements Serializable {
    private List<UserResponse> users;

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }
}
