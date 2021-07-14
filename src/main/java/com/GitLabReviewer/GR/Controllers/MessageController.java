package com.GitLabReviewer.GR.Controllers;

import com.GitLabReviewer.GR.DataBase.UserDB.User;
import com.GitLabReviewer.GR.DataBase.UserDB.UserNotFoundException;
import com.GitLabReviewer.GR.DataBase.UserDB.UserRepository;
import org.springframework.http.MediaType;
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

    @PostMapping("/hello")
    public void UserName(String name){
        User user = new User(name, "", false);
        repository.save(user);
    }

    @GetMapping("/hello")
    List<User> getUser(){
        return repository.findAll();
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping(value = "/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
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
}
