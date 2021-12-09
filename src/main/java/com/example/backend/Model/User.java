package com.example.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String avatar;

    private List<Integer> vidList;

}
