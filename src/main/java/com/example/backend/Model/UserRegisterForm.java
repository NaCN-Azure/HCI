package com.example.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterForm {
    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String avatar;

    private List<Integer> vidList;
}
