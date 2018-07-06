package com.codegym.wc.repository;

import com.codegym.wc.model.Country;
import com.codegym.wc.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

    Page<Player> findAll(Pageable pageable);

    Player findByName(String name);

    Page<Player> findAllByNameContains(String name, Pageable pageable);

}