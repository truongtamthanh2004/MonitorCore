package com.example.ehub.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_role_has_permission")
public class RoleHasPermission extends AbstractEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public RoleHasPermission() {
    }

    public RoleHasPermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

    public RoleHasPermission(Builder builder) {
        this.role = builder.role;
        this.permission = builder.permission;
    }

    public static class Builder {
        private Role role;
        private Permission permission;

        public Builder builder() {
            return new Builder();
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder permission(Permission permission) {
            this.permission = permission;
            return this;
        }

        public RoleHasPermission build() {
            return new RoleHasPermission(this);
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
