package com.example.ehub.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_user_has_role")
public class UserHasRole extends AbstractEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserHasRole() {
    }

    public UserHasRole(UserEntity user, Role role) {
        this.user = user;
        this.role = role;
    }

    public UserHasRole(Builder builder) {
        this.user = builder.user;
        this.role = builder.role;
    }

    public static class Builder {
        private UserEntity user;
        private Role role;

        public Builder builder() {
            return new Builder();
        }

        public Builder user(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public UserHasRole build() {
            return new UserHasRole(this);
        }
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
