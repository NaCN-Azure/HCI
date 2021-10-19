package com.example.backend.Repository;

import com.example.backend.Model.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, Long> {

    Privilege findByVidAndBid(long vid, long bid);

    List<Privilege> findAllByBidOrderByBreakfastAsc(long bid);

    Privilege findFirstByBidOrderByBreakfastDescCheckoutDesc(long bid);

    List<Privilege> findAllByBidOrderByCheckoutAsc(long bid);

}
