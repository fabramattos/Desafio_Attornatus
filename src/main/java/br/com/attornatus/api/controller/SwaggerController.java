package br.com.attornatus.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class SwaggerController {
    @RequestMapping("/")
    public RedirectView redirecionar() {
        return new RedirectView("http://localhost:8080/swagger-ui/index.html");
    }
}