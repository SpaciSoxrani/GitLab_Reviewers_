package com.GitLabReviewer.GR.WebHook;

import org.json.JSONObject;

public class WebHook {
    public JSONObject webhook;

    public void setWebHook(JSONObject newWebHook){
        this.webhook = newWebHook;
    }

    public JSONObject getWebHook(){
        return webhook;
    }
}
