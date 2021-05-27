package com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook;

public class MergeRequestWebhook {
    public String object_kind;
    public UserWebhook user;

    protected MergeRequestWebhook() {
    }

    public MergeRequestWebhook(String object_kind, UserWebhook user){
        this.object_kind = object_kind;
        this.user = user;
    }

    public UserWebhook getUser() {
        return this.user;
    }

    public String getObjectKind() {
        return this.object_kind;
    }

    public void setUser(UserWebhook user) {
        this.user = user;
    }

    public void setObjectKind(String object_kind) {
        this.object_kind = object_kind;
    }

    public String webhookBotMessage(){
        return " Новое действие на GitLab! " +
                "\n Новое событие: " + this.object_kind +
                user.webhookBotMessage();
    }
}
