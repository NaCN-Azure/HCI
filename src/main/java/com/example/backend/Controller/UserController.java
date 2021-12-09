package com.example.backend.Controller;

import com.example.backend.Model.ResponseVO;
import com.example.backend.Model.User;
import com.example.backend.Model.UserLoginForm;
import com.example.backend.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService IUserService;

    @PostMapping("/login")
    public ResponseEntity<ResponseVO> login(@RequestBody UserLoginForm userLoginForm){
        return IUserService.login(userLoginForm);
    }

    @PostMapping("/register")
    ResponseEntity<ResponseVO> register(@RequestBody User user){
        return IUserService.register(user);
    }

    @GetMapping("/logout")
    ResponseEntity<ResponseVO> logout(){
        return IUserService.logout();
    }

}
