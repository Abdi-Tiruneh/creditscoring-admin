package com.dxvalley.creditscoring.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class IndexController {
    
    @GetMapping
    public String getInfo(){
        return "Credit scoring Admin api's documentation";
    }
}
