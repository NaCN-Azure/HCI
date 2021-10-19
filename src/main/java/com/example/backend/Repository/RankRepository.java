package com.example.backend.Repository;

import com.example.backend.Model.Rank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends MongoRepository<Rank, Long> {

    Rank findByNameContains(String name);

    Rank findById(long id);
}
