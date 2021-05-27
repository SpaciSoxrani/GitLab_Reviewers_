package com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook;

import java.util.Objects;

public class UserWebhook {
    public Long id;
    public String name;
    public String email;
    public String avatar_url;

    protected UserWebhook() {
    }

    public UserWebhook(String user_name, String user_email, String avatar_url) {
        this.name = user_name;
        this.email = user_email;
        this.avatar_url = avatar_url;
    }
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAvatar() {
        return this.avatar_url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof UserWebhook))
            return false;
        UserWebhook user = (UserWebhook) o;
        return  Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
                && Objects.equals(this.email, user.email) && Objects.equals(this.avatar_url, user.avatar_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email, this.avatar_url);
    }

    @Override
    public String toString() {
        return "WebhookUser{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email=" + this.email + '\'' + ", avatar=" + this.avatar_url + '\'' + '}';
    }

    public String webhookBotMessage(){
        return "\n Пользователь под ником - " + this.name +
                "\n id пользователя - " + this.id +
                "\n " +  this.avatar_url;
    }
}
