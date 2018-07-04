package com.codegym.wc.service;

import com.codegym.wc.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Group save(Group group);

    boolean existName(String name);

    Group findById(Long id);

    Page<Group> findAll(Pageable pageable);

    void delete(Long id);


}
