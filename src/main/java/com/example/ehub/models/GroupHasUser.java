package com.example.ehub.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_group_has_user")
public class GroupHasUser extends AbstractEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public GroupHasUser() {
    }

    public GroupHasUser(Group group, UserEntity user) {
        this.group = group;
        this.user = user;
    }

    public GroupHasUser(Builder builder) {
        this.group = builder.group;
        this.user = builder.user;
    }

    public static class Builder {
        private Group group;
        private UserEntity user;

        public Builder builder() {
            return new Builder();
        }

        public Builder group(Group group) {
            this.group = group;
            return this;
        }

        public Builder user(UserEntity user) {
            this.user = user;
            return this;
        }

        public GroupHasUser build() {
            return new GroupHasUser(this);
        }
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}