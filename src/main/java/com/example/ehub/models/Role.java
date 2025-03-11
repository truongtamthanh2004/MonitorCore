package com.example.ehub.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_role")
public class Role extends AbstractEntity<Integer> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<RoleHasPermission> roles = new HashSet<>();

    public Role() {
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class Builder {
        private String name;
        private String description;
        private Set<RoleHasPermission> roles = new HashSet<>();

        public Builder builder() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder roles(Set<RoleHasPermission> roles) {
            this.roles = roles;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

//    @OneToMany(mappedBy = "user")
//    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RoleHasPermission> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleHasPermission> roles) {
        this.roles = roles;
    }
}
