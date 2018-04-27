package com.jiwhiz.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.jiwhiz.demo.model.Demo;
import com.jiwhiz.demo.service.DemoService;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;


@Controller
public class DemoController {

    @Autowired
    DemoService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("persons", service.findAll());

        return "index";
    }
    
          @PostMapping(value = "/")
        public String addservice(@RequestParam("name") String name,
                        @RequestParam("description") String description,
                        @RequestParam("version") String version, Model model) {
                service.addService(name, description, version);
                System.out.println("name = " + name + ",description = " + description + ", version = " + version);
                return "redirect:/";
        }


}
