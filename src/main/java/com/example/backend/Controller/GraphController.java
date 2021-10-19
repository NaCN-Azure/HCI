package com.example.backend.Controller;

import com.example.backend.Model.Graph;
import com.example.backend.Service.Impl.GraphService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/getGraph")
    public ResponseEntity<Graph> getLatestGraph() {
        Graph graph = graphService.getLatestGraph();
        return ResponseEntity.ok().body(graph);
    }

    @PostMapping("/addGraph")
    public ResponseEntity<Map<String, Boolean>> addGraph(@Valid @RequestBody Graph graph) {
        graphService.addGraph(graph);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
}
