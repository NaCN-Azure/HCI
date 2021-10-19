package com.example.backend.Controller;

import com.example.backend.BackendApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QaControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void getAnswer() {
        Map<String, Object> params = new HashMap<>();
        String name = "锦江国际";
        params.put("questionIndex", 1);
        params.put("groupName", "锦江国际集团");
        params.put("brandName", "");
        params.put("rankName", "");
        params.put("vipName", "");

        ResponseEntity<Map> response = restTemplate.postForEntity(getRootUrl() + "/api/getAnswer", params, Map.class);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("answer"));

        System.out.println(response.getBody().get("answer"));
    }

}
