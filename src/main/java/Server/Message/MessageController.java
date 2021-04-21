package Server.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ServerMessage greeting(MessageForm message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new ServerMessage("Hello, " + message.ReturnMassage() + "!");
    }
}
