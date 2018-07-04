package com.codegym.wc.repository;

import com.codegym.wc.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
    Page<Country> findAll(Pageable pageable);

    Country findByName(String name);

    Page<Country> findAllByNameContains(String name, Pageable pageable);
}
