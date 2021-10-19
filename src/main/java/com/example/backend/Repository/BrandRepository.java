package com.example.backend.Repository;

import com.example.backend.Model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends MongoRepository<Brand, Long> {

    void deleteByGid(Long gid);

    List<Brand> findAllByGid(long gid);

    Brand findByName(String name);

    Brand findByNameContains(String name);

    List<Brand> findTop8ByRidAndPriority(long rid, String priority);

    List<Brand> findTop8ByGidAndRidAndPriority(long gid, long rid, String priority);
}
