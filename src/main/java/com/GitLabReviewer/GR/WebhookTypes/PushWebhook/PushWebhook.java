package com.GitLabReviewer.GR.WebhookTypes.PushWebhook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class PushWebhook {
    public @Id
    @GeneratedValue
    Long id;
    public String object_kind;
    public String user_name;
    public String user_email;
    public String user_avatar;

    protected PushWebhook() {
    }

    public PushWebhook(String object_kind, String user_name, String user_email, String user_avatar) {
        this.object_kind = object_kind;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_avatar = user_avatar;
    }
    public Long getId() {
        return this.id;
    }

    public String getObjectKind() {
        return this.object_kind;
    }

    public String getName() {
        return this.user_name;
    }

    public String getEmail() {
        return this.user_email;
    }

    public String getAvatar() {
        return this.user_avatar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObjectKind(String object_kind) {
        this.object_kind = object_kind;
    }

    public void setName(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String user_email) {
        this.user_email = user_email;
    }

    public void setAvatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof PushWebhook))
            return false;
        PushWebhook user = (PushWebhook) o;
        return  Objects.equals(this.id, user.id) && Objects.equals(this.object_kind, user.object_kind) && Objects.equals(this.user_name, user.user_name)
                && Objects.equals(this.user_email, user.user_email) && Objects.equals(this.user_avatar, user.user_avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.object_kind, this.user_name, this.user_email, this.user_avatar);
    }

    @Override
    public String toString() {
        return "WebhookUser{" + "id=" + this.id + ", object_kind=" + this.object_kind + ", name='" + this.user_name + '\'' + ", email=" + this.user_email + '\'' + ", avatar=" + this.user_avatar + '\'' + '}';
    }

    public String webhookBotMessage(){
        return " Новое действие на GitLab! " +
                "\n Новое событие: " + this.object_kind +
                "\n Пользователь под ником - " + this.user_name +
                "\n " +  this.user_avatar;
    }
}

