package com.GitLabReviewer.GR.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ServerMessage greeting(MessageForm message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ServerMessage("Hello, " + message.returnMassage() + "!");
    }
}
