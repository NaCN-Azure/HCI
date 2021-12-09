package com.example.backend.Service;

import com.example.backend.Model.ResponseVO;
import com.example.backend.Model.User;
import com.example.backend.Model.UserLoginForm;
import org.springframework.http.ResponseEntity;


public interface IUserService {
    ResponseEntity<ResponseVO> login(UserLoginForm userLoginForm);

    ResponseEntity<ResponseVO> register(User user);

    ResponseEntity<ResponseVO> logout();

}
