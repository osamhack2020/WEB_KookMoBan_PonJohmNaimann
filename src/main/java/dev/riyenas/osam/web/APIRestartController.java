package dev.riyenas.osam.web;

import dev.riyenas.osam.OsamApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class APIRestartController {

    @GetMapping("restart")
    public void restart() {
        OsamApplication.restart();
    }
}
