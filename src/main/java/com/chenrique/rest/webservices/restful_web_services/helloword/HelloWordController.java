package com.chenrique.rest.webservices.restful_web_services.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloWordController {

    @GetMapping("/hello-word")
    public String helloWord() {
        return "Hello Word!";
    }
}
