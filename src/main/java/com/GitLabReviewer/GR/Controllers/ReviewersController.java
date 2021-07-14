package com.GitLabReviewer.GR.Controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewersController {

    @PostMapping(headers = "PRIVATE-TOKEN: uKV2zNyFhApiZ-sYSWbx",
            value = "https://gitlab.example.com/api/v4/projects?name=foo")
    public void putId(){
        System.out.println("new foo");
    }
}
