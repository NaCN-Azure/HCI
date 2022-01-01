package com.example.backend.Service;

import com.example.backend.Model.ResponseVO;
import com.example.backend.Model.User;
import com.example.backend.Model.UserLoginForm;
import com.example.backend.Model.UserRegisterForm;
import org.springframework.http.ResponseEntity;


public interface IUserService {
    ResponseEntity<ResponseVO> login(UserLoginForm userLoginForm);

    ResponseEntity<ResponseVO> register(UserRegisterForm user);

    ResponseEntity<ResponseVO> logout();

}
