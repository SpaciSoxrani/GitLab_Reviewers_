package com.GitLabReviewer.GR.Message;

import com.GitLabReviewer.GR.DataBase.User;
import com.GitLabReviewer.GR.DataBase.UserNotFoundException;
import com.GitLabReviewer.GR.DataBase.UserRepository;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class MessageController {

    private final UserRepository repository;

    MessageController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody String body, User newUser) {
        return repository.save(newUser);
    }

    @PostMapping("/webhook")
    JSONObject newWebHook(JSONObject message) {
        return message;
    }

//    @GetMapping("/webhookw")
//    JSONObject getWebHook() {
//        return webhook;
//    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setRole(newUser.getRole());
                    user.setCanCheck(newUser.getCanCheck());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ServerMessage greeting(MessageForm message){
        //Thread.sleep(1000); // simulated delay
        return new ServerMessage("Hello, как это заебало " + message.returnMassage() + "!");
    }
}
