package com.example.backend.Service;

import com.example.backend.Model.Graph;

public interface IGraphService {

    /**
     * @param graph
     */
    long addGraph(Graph graph);

    /**
     * @return
     */
    Graph getLatestGraph();

    /**
     * @param id
     */
    void deleteGraphById(long id);
}
