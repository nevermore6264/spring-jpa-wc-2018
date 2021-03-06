package com.codegym.wc.service.impl;

import com.codegym.wc.model.Country;
import com.codegym.wc.model.Player;
import com.codegym.wc.repository.PlayerRepository;
import com.codegym.wc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findOne(id);
    }

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    @Override
    public boolean existName(String name) {
        Player player = playerRepository.findByName(name);
        return (player != null);
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.delete(id);
    }

    @Override
    public Page<Player> findAllByNameContains(String name, Pageable pageable) {
        return playerRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public Page<Player> findAllByCountry(Country country, Pageable pageable) {
        return playerRepository.findAllByCountry(country,pageable);
    }
}

