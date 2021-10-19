package com.example.backend.Service.Impl;

import com.example.backend.Model.Vip;
import com.example.backend.Repository.VipRepository;
import com.example.backend.Service.IVipService;
import org.springframework.stereotype.Service;

@Service
public class VipService implements IVipService {
    private final VipRepository vipRepository;

    public VipService(VipRepository vipRepository) {
        this.vipRepository = vipRepository;
    }

    @Override
    public Vip getVipByNameContains(String name) {
        try {
            return vipRepository.findByNameContains(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Vip getVipById(long id) {
        try {
            return vipRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
}
