package dev.riyenas.osam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/log/fault")
    public String logFault() {
        return "logFault";
    }

    @GetMapping("/log/all")
    public String logPass() {
        return "logAll";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/soldier")
    public String soldier() {
        return "soldier";
    }
}
