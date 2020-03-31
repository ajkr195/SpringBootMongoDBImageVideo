package com.springboot.mongodb.imagevideo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
   
    @ModelAttribute("metaTitle")
   	public String globalTitle() {
   		return "MongoDB";
   	}
    
    @RequestMapping(value = "/")
	public String index(Model model) {
		return "index";
	}
    
}
