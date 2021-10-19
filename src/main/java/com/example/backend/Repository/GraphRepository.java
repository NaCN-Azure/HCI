package com.example.backend.Repository;

import com.example.backend.Model.Graph;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends MongoRepository<Graph, Long> {

    Graph findTopByOrderByIdDesc();

    void deleteById(long id);
}
