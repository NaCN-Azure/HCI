package com.example.backend.Repository;

import com.example.backend.Model.Vip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VipRepository extends MongoRepository<Vip, Long> {

    Vip findById(long id);

    Vip findByNameContains(String name);
}
