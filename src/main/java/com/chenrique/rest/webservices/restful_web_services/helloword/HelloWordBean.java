package com.chenrique.rest.webservices.restful_web_services.helloword;

public class HelloWordBean {

    private String message;

    public HelloWordBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
