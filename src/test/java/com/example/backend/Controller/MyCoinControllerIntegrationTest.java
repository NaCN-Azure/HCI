package com.example.backend.Controller;

import com.example.backend.BackendApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyCoinControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void getCoin() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Map> response = restTemplate.exchange(getRootUrl() + "/api/getCoin",
                HttpMethod.GET, entity, Map.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void getGroupNameList() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<List> response = restTemplate.exchange(getRootUrl() + "/api/getGroupNameList",
                HttpMethod.GET, entity, List.class);

        assertNotNull(response);
        for (String name : (List<String>) response.getBody()) {
            System.out.println(name);
        }
    }

    @Test
    public void getGraphByGroupName() {
        Map<String, String> params = new HashMap<>();
        params.put("groupName", "锦江国际集团");

        ResponseEntity<Map> response = restTemplate.postForEntity(getRootUrl() + "/api/getGraphByGroupName", params, Map.class);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("nodes"));
    }

    @Test
    public void getDetailByBrandName() {
        Map<String, String> params = new HashMap<>();
        params.put("brandName", "7天");

        ResponseEntity<Map> response = restTemplate.postForEntity(getRootUrl() + "/api/getDetailByBrandName", params, Map.class);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("detail"));
        System.out.println(response.getBody().get("detail"));
    }

    @Test
    public void getDetailByGroupName() {
        Map<String, String> params = new HashMap<>();
        params.put("groupName", "锦江国际集团");

        ResponseEntity<Map> response = restTemplate.postForEntity(getRootUrl() + "/api/getDetailByGroupName", params, Map.class);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("detail"));
        System.out.println(response.getBody().get("detail"));
    }
}
