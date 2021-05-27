package com.GitLabReviewer.GR.Message;

import com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook.MergeRequestWebhook;
import com.GitLabReviewer.GR.WebhookTypes.ObjectKindWebhook;
import com.GitLabReviewer.GR.WebhookTypes.PushWebhook.PushWebhook;
import com.GitLabReviewer.GR.DataBase.WebHookDB.WebHookRepository;
import com.GitLabReviewer.GR.TelegramBot.TelegramCommands.GR_Bot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebHookController {

    private final WebHookRepository webHookRepository;
    private final GR_Bot bot;
    WebHookController(WebHookRepository webHookRepository, GR_Bot bot) {
        this.webHookRepository = webHookRepository;
        this.bot = bot;
    }

    @PostMapping("/webhook")
    public void newWebHook(@RequestBody String json){

        System.out.println("WebHook collected JSON: " + json);
        ObjectKindWebhook web = new Gson().fromJson(json, ObjectKindWebhook.class);
        if(web.getObjectKind().equals("merge_request"))
        {
            MergeRequestWebhook userFromJSON = new Gson().fromJson(json, MergeRequestWebhook.class);
            System.out.println(userFromJSON);
            bot.sendMsg(userFromJSON.webhookBotMessage());
        }
        if(web.getObjectKind().equals("push"))
        {
            PushWebhook userFromJSON = new Gson().fromJson(json, PushWebhook.class);
            System.out.println(userFromJSON);
            bot.sendMsg(userFromJSON.webhookBotMessage());
        }
    }

    @GetMapping("/webhook")
    List<PushWebhook> returnWebHook() {
        return webHookRepository.findAll();
    }
}
