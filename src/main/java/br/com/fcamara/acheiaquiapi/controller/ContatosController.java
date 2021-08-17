package br.com.fcamara.acheiaquiapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ContatosController {

    @GetMapping
    @ResponseBody
    public String pai() {
        return "Hello";
    }
}
