package com.codegym.wc.service.impl;

import com.codegym.wc.model.Country;
import com.codegym.wc.repository.CountryRepository;
import com.codegym.wc.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country findById(Long id) {
        return countryRepository.findOne(id);
    }

    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    public boolean existName(String name) {
        Country country = countryRepository.findByName(name);
        return (country != null);
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.delete(id);
    }

    @Override
    public Page<Country> findAllByNameContains(String name, Pageable pageable) {
        return countryRepository.findAllByNameContains(name, pageable);
    }
}
