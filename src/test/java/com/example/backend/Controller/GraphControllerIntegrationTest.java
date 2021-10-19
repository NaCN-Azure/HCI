package com.example.backend.Controller;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Graph;
import com.example.backend.Service.Impl.GraphService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GraphService graphService;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void addGraph() {
        Graph graph = new Graph();
        List<Object> nodes = new ArrayList<>();
        nodes.add("s1");
        nodes.add("s2");
        nodes.add("s3");
        graph.setNodes(nodes);

        ResponseEntity<Map> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/addGraph", graph, Map.class);
        Map<String, Boolean> m = postResponse.getBody();

        assert m != null;
        assert m.get("added");

        Graph resGraph = graphService.getLatestGraph();
        assert resGraph.getNodes().get(0).equals("s1");
        assert resGraph.getNodes().get(1).equals("s2");
        assert resGraph.getNodes().get(2).equals("s3");

        graphService.deleteGraphById(resGraph.getId());
    }

    @Test
    public void getLatestGraph() {
        Graph graph = new Graph();
        List<Object> nodes = new ArrayList<>();
        nodes.add("s1");
        nodes.add("s2");
        nodes.add("s3");
        graph.setNodes(nodes);
        long id = graphService.addGraph(graph);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Map> response = restTemplate.exchange(getRootUrl() + "/api/getGraph",
                HttpMethod.GET, entity, Map.class);

        assert (int) response.getBody().get("id") == id;

        graphService.deleteGraphById(id);
    }
}
