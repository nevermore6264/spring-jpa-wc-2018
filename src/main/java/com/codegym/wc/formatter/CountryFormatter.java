package com.codegym.wc.formatter;

import com.codegym.wc.model.Country;
import com.codegym.wc.model.Group;
import com.codegym.wc.service.CountryService;
import com.codegym.wc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CountryFormatter implements Formatter<Country> {

    private CountryService countryService;

    @Autowired
    public CountryFormatter(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public Country parse(String text, Locale locale) throws ParseException {
        return countryService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Country object, Locale locale) {
        return null;
    }
}
