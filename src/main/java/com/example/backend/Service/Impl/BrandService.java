package com.example.backend.Service.Impl;

import com.example.backend.Model.Brand;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Service.IBrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements IBrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand getBrandByNameContains(String name) {
        Brand brand;
        try {
            brand = brandRepository.findByNameContains(name);
        } catch (Exception e) {
            brand = brandRepository.findByName(name);
        }

        return brand;
    }

    @Override
    public String getLogoUrlByBrandName(String name) {
        Brand brand = getBrandByNameContains(name);
        if (brand == null)
            return "";
        return brand.getImgsrc();
    }
}
