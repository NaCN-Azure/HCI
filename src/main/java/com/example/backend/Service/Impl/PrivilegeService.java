package com.example.backend.Service.Impl;

import com.example.backend.Model.Privilege;
import com.example.backend.Repository.PrivilegeRepository;
import com.example.backend.Service.IPrivilegeService;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService implements IPrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Privilege getPrivilegeByVidAndBid(long vid, long bid) {
        try {
            return privilegeRepository.findByVidAndBid(vid, bid);
        } catch (Exception e) {
            return null;
        }
    }
}
