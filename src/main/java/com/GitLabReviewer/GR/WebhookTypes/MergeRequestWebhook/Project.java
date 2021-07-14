package com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook;

public class Project {
    public Long id;
    public String name;

    protected Project() {
    }

    public Project(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
