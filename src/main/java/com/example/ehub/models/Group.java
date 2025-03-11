package com.example.ehub.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_group")
public class Group extends AbstractEntity<Integer> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Group() {
    }

    public Group(String name, String description, Role role) {
        this.name = name;
        this.description = description;
        this.role = role;
    }

    public Group(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.role = builder.role;
    }

    public static class Builder {
        private String name;
        private String description;
        private Role role;

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

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
