package com.example.backend.Service;

import com.example.backend.Model.Rank;

public interface IRankService {


    /**
     * @param name
     * @return
     */
    Rank getRankByNameContains(String name);

    /**
     * @param id
     * @return
     */
    Rank getRankById(long id);
}
