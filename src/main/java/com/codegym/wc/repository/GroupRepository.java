package com.codegym.wc.repository;

import com.codegym.wc.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
    Group findByName(String name);

    Page<Group> findAll(Pageable pageable);
}
