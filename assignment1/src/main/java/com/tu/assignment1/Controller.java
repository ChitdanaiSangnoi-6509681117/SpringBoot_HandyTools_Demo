package com.tu.assignment1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controller {

    @GetMapping("/main")
    public String getMethodName(@RequestParam String param) {
        return "Received param: " + param;
    }
}