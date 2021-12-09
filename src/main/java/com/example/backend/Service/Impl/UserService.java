package com.example.backend.Service.Impl;

import com.example.backend.Model.ResponseVO;
import com.example.backend.Model.User;
import com.example.backend.Model.UserLoginForm;
import com.example.backend.Repository.UserRepository;
import com.example.backend.Service.IUserService;
import com.example.backend.UserData.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    private static final ReentrantLock buildUserLock = new ReentrantLock();

    @Override
    public ResponseEntity<ResponseVO> login(UserLoginForm userLoginForm) {
        if(userLoginForm.getEmail() == null) {
            return ResponseEntity.ok(ResponseVO.buildFailure("邮箱不可为空"));
        }
        if(userLoginForm.getPassword() == null) {
            return ResponseEntity.ok(ResponseVO.buildFailure("密码不可为空"));
        }

        if(UserSession.getUser() != null) {
            return ResponseEntity.ok(ResponseVO.buildFailure("请勿重复登录"));
        }

        List<User> list = userRepository.findByEmail(userLoginForm.getEmail());

        if(list.size() == 0) {
            return ResponseEntity.ok(ResponseVO.buildFailure("用户名不存在"));
        }
        if(list.size() > 1) {
            return ResponseEntity.ok(ResponseVO.buildFailure("服务器异常"));
        }

        User user = list.get(0);

        if(!user.getPassword().equals(userLoginForm.getPassword())) {
            return ResponseEntity.ok(ResponseVO.buildFailure("密码错误"));
        }

        UserSession.loginUser(user);

        return ResponseEntity.ok(ResponseVO.buildOK("登录成功"));
    }

    @Override
    public ResponseEntity<ResponseVO> logout() {
        UserSession.logoutUser();

        return ResponseEntity.ok(ResponseVO.buildOK("注销成功"));
    }

    @Override
    public ResponseEntity<ResponseVO> register(User userVO) {
        if(userVO.getEmail() == null) {
            return ResponseEntity.ok(ResponseVO.buildFailure("邮箱不可为空"));
        }
        if(userVO.getUsername() == null) {
            return ResponseEntity.ok(ResponseVO.buildFailure("用户名不可为空"));
        }
        if(userVO.getPassword() == null) {
            return ResponseEntity.ok(ResponseVO.buildFailure("密码不可为空"));
        }
        if(userVO.getPhoneNumber() == null){
            return ResponseEntity.ok(ResponseVO.buildFailure("手机号不可为空"));
        }

        buildUserLock.lock();
        try {
            List<User> list = userRepository.findByEmail(userVO.getEmail());

            if(list.size() > 0) {
                ResponseVO.buildFailure("邮箱已注册");
            }

            User user = new User();
            user.setEmail(userVO.getEmail());
            user.setUsername(userVO.getUsername());
            user.setPassword(userVO.getPassword());
            user.setUsername(userVO.getUsername());
            user.setPhoneNumber(userVO.getPhoneNumber());
            user.setVidList(userVO.getVidList());

            userRepository.save(user);
        } finally {
            buildUserLock.unlock();
        }

        return ResponseEntity.ok(ResponseVO.buildOK("注册成功"));
    }

}
