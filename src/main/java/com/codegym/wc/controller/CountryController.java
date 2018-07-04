package com.codegym.wc.controller;

import com.codegym.wc.model.Country;
import com.codegym.wc.model.Group;
import com.codegym.wc.service.CountryService;
import com.codegym.wc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/countries")
public class CountryController {
    private GroupService groupService;
    private CountryService countryService;

    @Autowired
    public CountryController(GroupService groupService, CountryService countryService) {
        this.groupService = groupService;
        this.countryService = countryService;
    }

    @GetMapping("")
    public ModelAndView listCustomers(@RequestParam("string") Optional<String> s, Pageable pageable) {
        Page<Country> countries;
        if (s.isPresent()) {
            countries = countryService.findAllByNameContains(s.get(), pageable);
        } else {
            countries = countryService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @ModelAttribute("groups")
    public Page<Group> getAllGroup(Pageable pageable) {
        Page<Group> groups = groupService.findAll(pageable);
        return groups;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/country/create");
        modelAndView.addObject("countries", new Country());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createTest(@ModelAttribute("country") Country country) {

        ModelAndView modelAndView = new ModelAndView("/country/create");

        countryService.save(country);
        modelAndView.addObject("message", "New country is created");
        modelAndView.addObject("countries", new Country());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Country countries = countryService.findById(id);
        if (countries == null) {
            return new ModelAndView("/error404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/country/edit");
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateTest(@Valid @ModelAttribute("countries") Country countries) {
        ModelAndView modelAndView = new ModelAndView("/country/edit");
        countryService.save(countries);
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("message", "Country is updated");
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Country countries = countryService.findById(id);
        ModelAndView modelAndView;
        if (countries != null) {
            modelAndView = new ModelAndView("/country/delete");
            modelAndView.addObject("countries", countries);
        } else {
            modelAndView = new ModelAndView("/error404");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteTest(@PathVariable("id") Long id) {
        ModelAndView modelAndView;
        Country countries = countryService.findById(id);
        if (countries != null) {
            countryService.delete(id);
            modelAndView = new ModelAndView("redirect:/countries");
        } else {
            modelAndView = new ModelAndView("/error404");
        }

        return modelAndView;
    }
}
