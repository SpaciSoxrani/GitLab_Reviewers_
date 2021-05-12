package com.GitLabReviewer.GR.Message;

import com.GitLabReviewer.GR.DataBase.User;
import com.GitLabReviewer.GR.DataBase.UserNotFoundException;
import com.GitLabReviewer.GR.DataBase.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;
import org.springframework.http.MediaType;
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

    JSONObject webhook;
    public void setWebHook(JSONObject newWebHook){
        this.webhook = newWebHook;
    }

    public JSONObject getWebHook(){
        return webhook;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping(value = "/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PostMapping(value = "/webhook", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    String newWebHook(JSONObject message) {
        setWebHook(message);
        
        if(message != null) {
            return "successful!";
        }
        else return "message = null";
    }

    @GetMapping("/webhook2")
    JSONObject getNewWebHook() {
        return getWebHook();
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ServerMessage greeting(@RequestBody MessageForm message){
        //Thread.sleep(1000); // simulated delay
        return new ServerMessage("Hello, " + message.returnMassage() + "!");
    }
}
