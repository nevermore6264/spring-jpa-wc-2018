package com.codegym.wc.service.impl;

import com.codegym.wc.model.Group;
import com.codegym.wc.repository.GroupRepository;
import com.codegym.wc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group save(Group group) {
        return this.groupRepository.save(group);
    }

    @Override
    public boolean existName(String name) {
        Group group = groupRepository.findByName(name);
        return (group != null);
    }

    @Override
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        groupRepository.delete(id);
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findOne(id);
    }
}

