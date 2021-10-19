package com.example.backend.Service;

import com.example.backend.Model.Brand;


public interface IBrandService {

    /**
     * @param name
     * @return
     */
    Brand getBrandByNameContains(String name);

    /**
     * @param name
     * @return
     */
    String getLogoUrlByBrandName(String name);
}
