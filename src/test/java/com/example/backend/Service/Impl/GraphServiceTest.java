package com.example.backend.Service.Impl;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Graph;
import com.example.backend.Repository.GraphRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphServiceTest {

    @Autowired
    GraphService graphService;

    @Autowired
    GraphRepository graphRepository;


    @Test
    public void addGraph() {
        Graph graph = new Graph(0, null, null);
        long currId = graphService.addGraph(graph);

        assert graphService.getLatestGraph().getId() == currId;
        graphService.deleteGraphById(currId);
    }


    @Test
    public void getLatestGraph() {
        graphService.addGraph(new Graph(0, null, null));
        Graph graph = new Graph(0, null, null);
        long currId = graphService.addGraph(graph);

        assert graphService.getLatestGraph().getId() == currId;
        graphService.deleteGraphById(graphService.getLatestGraph().getId());
        graphService.deleteGraphById(graphService.getLatestGraph().getId());
    }

    @Test
    public void deleteGraphById() {
        Graph graph = new Graph(0, null, null);
        long currId = graphService.addGraph(graph);

        graphService.deleteGraphById(currId);
        assert graphService.getLatestGraph().getId() != currId;
    }
}
