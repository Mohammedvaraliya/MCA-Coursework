package org.kjsim.session11.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    private final AppConfig appConfig;

    public ConfigController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/config")
    public String test() {
        return "App Name: " + appConfig.getName() + "<br>Version: " + appConfig.getVersion() + "<br>Class: " + appConfig.getClass();
    }
}