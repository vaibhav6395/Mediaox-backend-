package com.mediabox.mediabox;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Home")
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "Welcome to Mediabox!";
    }

}
