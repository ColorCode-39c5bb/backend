package cn.violetgarden.game.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*") // 允许跨域请求
public class Controller_ {
    @GetMapping("/init")
    public String start(){
        return "init";
    }
}