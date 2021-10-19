package com.example.backend.Service;

import com.example.backend.Model.Vip;

public interface IVipService {


    /**
     * @param name
     * @return
     */
    Vip getVipByNameContains(String name);

    /**
     * @param id
     * @return
     */
    Vip getVipById(long id);
}
