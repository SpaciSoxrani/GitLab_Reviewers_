package com.GitLabReviewer.GR.WebhookTypes;

public class ObjectKindWebhook {
    public String object_kind;

    protected ObjectKindWebhook() {
    }

    public ObjectKindWebhook( String object_kind){
        this.object_kind = object_kind;
    }

    public String getObjectKind() {
        return this.object_kind;
    }
    public void setObjectKind(String object_kind) {
        this.object_kind = object_kind;
    }

}
