package com.chenrique.rest.webservices.restful_web_services.helloword;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloWordController {
    private MessageSource messageSource;

    public HelloWordController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping("/hello-word-inter")
    public String helloWordInter() {

        Locale locate = LocaleContextHolder.getLocale();
        return messageSource.getMessage("bom.dia.message", null, "Messagem padrão", locate);
    }
}
