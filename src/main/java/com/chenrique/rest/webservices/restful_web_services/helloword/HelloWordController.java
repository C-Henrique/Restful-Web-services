package com.chenrique.rest.webservices.restful_web_services.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloWordController {

    @GetMapping("/hello-word")
    public String helloWord() {
        return "Hello Word!";
    }

    @GetMapping("/hello-word-bean")
    public HelloWordBean helloWordBeaString() {
        return new HelloWordBean("Hello Word!");
    }

    @GetMapping("/hello-word/path-variable/{name}")
    public HelloWordBean helloWordPathVariable(@PathVariable String name) {
        return new HelloWordBean(String.format("Hello Word, %s", name));
    }
}
