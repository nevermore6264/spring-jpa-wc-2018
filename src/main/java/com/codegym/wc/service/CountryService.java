package com.codegym.wc.service;

import com.codegym.wc.model.Country;
import com.codegym.wc.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Country findById(Long id);

    Page<Country> findAll(Pageable pageable);

    void save(Country country);

    boolean existName(String name);

    void delete(Long id);

    Page<Country> findAllByNameContains(String name, Pageable pageable);

    Page<Country> findAllByCountry(Group group, Pageable pageable);

}
