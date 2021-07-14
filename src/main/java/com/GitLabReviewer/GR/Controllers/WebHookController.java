package com.GitLabReviewer.GR.Controllers;

import com.GitLabReviewer.GR.MessageRewiever.MessageRewiever;
import com.GitLabReviewer.GR.WebhookTypes.MergeRequestWebhook.MergeRequestWebhook;
import com.GitLabReviewer.GR.WebhookTypes.ObjectKindWebhook;
import com.GitLabReviewer.GR.WebhookTypes.PushWebhook.PushWebhook;
import com.GitLabReviewer.GR.DataBase.WebHookDB.WebHookRepository;
import com.GitLabReviewer.GR.TelegramBot.TelegramCommands.GR_Bot;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebHookController {

    private final WebHookRepository webHookRepository;
    private final GR_Bot bot;
    WebHookController(WebHookRepository webHookRepository, GR_Bot bot) {
        this.webHookRepository = webHookRepository;
        this.bot = bot;
    }

    //TODO сделать по-человечески и красиво
    @PostMapping("/webhook")
    public void newWebHook(@RequestBody String json) throws InterruptedException {

        System.out.println("WebHook collected JSON: " + json);
        ObjectKindWebhook web = new Gson().fromJson(json, ObjectKindWebhook.class);
        if(web.getObjectKind().equals("merge_request"))
        {
            MergeRequestWebhook userFromJSON = new Gson().fromJson(json, MergeRequestWebhook.class);

            MessageRewiever mesRewiever = new MessageRewiever();
            mesRewiever.setId(userFromJSON.getProject().getId().toString());
            mesRewiever.setName(userFromJSON.getProject().getName().toString());

            System.out.println(userFromJSON);
            bot.sendMsg(userFromJSON.webhookBotMessage());
            Thread.sleep(500);
            bot.sendMsg(mesRewiever.rewieverMessage());
        }
        if(web.getObjectKind().equals("push"))
        {
            PushWebhook userFromJSON2 = new Gson().fromJson(json, PushWebhook.class);
            webHookRepository.save(userFromJSON2);
            System.out.println(userFromJSON2);
            bot.sendMsg(userFromJSON2.webhookBotMessage());
        }
    }

    @GetMapping("/webhook")
    List<PushWebhook> returnWebHook() {
        return webHookRepository.findAll();
    }
}
