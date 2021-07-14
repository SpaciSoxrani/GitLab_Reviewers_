package com.GitLabReviewer.GR.MessageRewiever;

import com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook.Project;
import com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook.UserWebhook;

public class MessageRewiever {
    public String id;
    public String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String rewieverMessage(){
        return " Вас назначили ревьюером! " +
                "\n ID проекта: " + this.id +
                "\n Имя проекта: " + this.name;
    }
}
