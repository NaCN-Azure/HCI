package com.example.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Graph")
public class Graph {

    @Transient
    public static final String SEQUENCE_NAME = "graphs_sequence";

    @Id
    private long id;

    private List<Object> nodes;

    private List<Object> links;

    public Graph() {

    }

    public Graph(long id, List<Object> nodes, List<Object> links) {
        this.id = id;
        this.nodes = nodes;
        this.links = links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Object> getNodes() {
        return nodes;
    }

    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }

    public List<Object> getLinks() {
        return links;
    }

    public void setLinks(List<Object> links) {
        this.links = links;
    }
}
