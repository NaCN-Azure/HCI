package com.example.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class IndexController {
    @RequestMapping()
    public String index(){
        return "这是一个测试页面，如果能看到，说明springboot配置成功";
    }
}
