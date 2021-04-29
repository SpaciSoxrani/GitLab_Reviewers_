package com.GitLabReviewer.GR.DataBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String role;
    private Boolean canCheck;

    protected User() {}

    public User(String name, String role, Boolean canCheck) {
        this.name = name;
        this.role = role;
        this.canCheck = canCheck;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public Boolean getCanCheck() {
        return this.canCheck;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCanCheck(Boolean canCheck) {
        this.canCheck = canCheck;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
                && Objects.equals(this.role, user.role)&&Objects.equals(this.canCheck, user.canCheck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role, this.canCheck);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + ", canCheck=" +this.canCheck + '\'' + '}';
    }
}

