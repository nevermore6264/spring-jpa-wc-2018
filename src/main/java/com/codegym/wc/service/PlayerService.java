package com.codegym.wc.service;

import com.codegym.wc.model.Country;
import com.codegym.wc.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {
    Player findById(Long id);

    Page<Player> findAll(Pageable pageable);

    void save(Player player);

    boolean existName(String name);

    void delete(Long id);

    Page<Country> findAllByNameContains(String name, Pageable pageable);
}
