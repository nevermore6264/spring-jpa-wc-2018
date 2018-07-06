package com.codegym.wc.controller;

import com.codegym.wc.model.Country;
import com.codegym.wc.model.Group;
import com.codegym.wc.model.Player;
import com.codegym.wc.service.CountryService;
import com.codegym.wc.service.GroupService;
import com.codegym.wc.service.PlayerService;
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
@RequestMapping("/players")
public class PlayerController {
    private CountryService countryService;
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService, CountryService countryService) {
        this.playerService = playerService;
        this.countryService = countryService;
    }

    @GetMapping("")
    public ModelAndView listPlayers(@RequestParam("string") Optional<String> s, Pageable pageable) {
        Page<Player> players;
        if (s.isPresent()) {
            players = playerService.findAllByNameContains(s.get(), pageable);
        } else {
            players = playerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/player/list");
        modelAndView.addObject("players", players);
        return modelAndView;
    }

    @ModelAttribute("countries")
    public Page<Country> getAllGroup(Pageable pageable) {
        Page<Country> countries = countryService.findAll(pageable);
        return countries;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/player/create");
        modelAndView.addObject("player", new Player());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createPlayer(@ModelAttribute("player") Player player) {

        ModelAndView modelAndView = new ModelAndView("/player/create");

        playerService.save(player);
        modelAndView.addObject("message", "New player is created");
        modelAndView.addObject("player", new Player());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Player player = playerService.findById(id);
        if (player == null) {
            return new ModelAndView("/error404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/player/edit");
            modelAndView.addObject("player", player);
            return modelAndView;
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updatePlayer(@Valid @ModelAttribute("player") Player player) {
        ModelAndView modelAndView = new ModelAndView("/player/edit");
        playerService.save(player);
        modelAndView.addObject("player", player);
        modelAndView.addObject("message", "Player is updated");
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Player player = playerService.findById(id);
        ModelAndView modelAndView;
        if (player != null) {
            modelAndView = new ModelAndView("/player/delete");
            modelAndView.addObject("player", player);
        } else {
            modelAndView = new ModelAndView("/error404");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteTest(@PathVariable("id") Long id) {
        ModelAndView modelAndView;
        Player player = playerService.findById(id);
        if (player != null) {
            playerService.delete(id);
            modelAndView = new ModelAndView("redirect:/players");
        } else {
            modelAndView = new ModelAndView("/error404");
        }

        return modelAndView;
    }
}
