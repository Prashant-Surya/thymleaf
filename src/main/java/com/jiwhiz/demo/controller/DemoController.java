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
import java.util.Date;

@Controller
public class DemoController {

        @Autowired
        DemoService service;

        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String index(Model md) {
                md.addAttribute("persons", service.findAll());
                md.addAttribute("versions", service.findAllVersion());

                return "index";
        }

        @PostMapping(value = "/")

        public String addservice(@RequestParam(required = false, name = "name") String name,
                        @RequestParam(required = false, name = "description") String description,
                        @RequestParam(required = false, name = "version") String version, Model model) {
                service.addService(name, description, version);
                System.out.println("name = " + name + ",description = " + description + ", version = " + version);
                return "redirect:/";
        }

        @PostMapping(value = "/hi")
        public String addversion(@RequestParam("service_id") String service_id,
                        @RequestParam("version_id") String version_id,
                        @RequestParam("version_status") String version_status,
                        @RequestParam("version_started") String version_started, Model model) {
                service.addVersion(service_id, version_id, version_status, version_started);
                System.out.println("service_id = " + service_id + ",version_id = " + version_id + ", version_status = "
                                + version_status + ", version_started = " + version_started);
                return "redirect:/";
        }

        @PostMapping(value = "/compat")
        public String addcompat(@RequestParam("service1_name") String service1_name,
                        @RequestParam("service1_version") String service1_version,
                        @RequestParam("service2_name") String service2_name,
                        @RequestParam("service2_version") String service2_version,
                        @RequestParam("compatable") String compatable, Model model) {
                service.addCompat(service1_name, service1_version, service2_name, service2_version, compatable);
                System.out.println("service1_name = " + service1_name + ",service1_version = " + service1_version
                                + ", service2_name = " + service2_name + ", service2_version = " + service2_version
                                + "compatable = " + compatable);
                return "redirect:/";
        }

        @GetMapping("/compatable")
        public String compat(Model md) {
                md.addAttribute("compatables", service.findCompat());
                return "compatable";
        }

}
