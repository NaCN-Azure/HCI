package com.example.backend.Service.Impl;

import com.example.backend.Model.Rank;
import com.example.backend.Repository.RankRepository;
import com.example.backend.Service.IRankService;
import org.springframework.stereotype.Service;

@Service
public class RankService implements IRankService {
    private final RankRepository rankRepository;

    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @Override
    public Rank getRankByNameContains(String name) {
        try {
            return rankRepository.findByNameContains(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Rank getRankById(long id) {
        try {
            return rankRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
}
