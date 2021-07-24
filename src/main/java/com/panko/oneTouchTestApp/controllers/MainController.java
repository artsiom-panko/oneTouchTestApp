package com.panko.oneTouchTestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/employees")
    public void firstEndPoint() {

    }

    @GetMapping("/employees")
    public void secondEndPoint() {

    }
}
