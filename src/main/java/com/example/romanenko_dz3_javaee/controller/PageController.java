package com.example.romanenko_dz3_javaee.controller;

import com.example.romanenko_dz3_javaee.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}
