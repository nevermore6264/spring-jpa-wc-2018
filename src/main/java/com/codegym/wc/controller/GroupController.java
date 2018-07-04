package com.codegym.wc.controller;

import com.codegym.wc.model.Group;
import com.codegym.wc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("")
    public ModelAndView getAllGroup(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/group/list");

        Page<Group> groups = groupService.findAll(pageable);

        modelAndView.addObject("groups", groups);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/group/create");
        modelAndView.addObject("group", new Group());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createFrom(@ModelAttribute("group") Group group) {
        ModelAndView modelAndView = new ModelAndView("/group/create");
        groupService.save(group);
        modelAndView.addObject("message", "New group is created");
        modelAndView.addObject("group", new Group());
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {

        Group group = groupService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (group == null) {
            modelAndView.setViewName("/error404");
            return modelAndView;
        } else {
            modelAndView.setViewName("/group/delete");
            modelAndView.addObject("group", group);
            return modelAndView;
        }
    }


    @PostMapping("/{id}/delete")
    public ModelAndView deleteCategory(@PathVariable("id") Long id) {
        Group group = groupService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        if (group != null) {
            groupService.delete(id);
        }
        modelAndView.setViewName("redirect:/groups");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {

        ModelAndView modelAndView;
        Group group = groupService.findById(id);
        if (group != null) {
            modelAndView = new ModelAndView("/group/edit");
            modelAndView.addObject("group", group);
            return modelAndView;
        } else {
            return new ModelAndView("/error404");
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(@Valid @ModelAttribute("group") Group group) {

        ModelAndView modelAndView = new ModelAndView("/group/edit");
        groupService.save(group);
        modelAndView.addObject("group", group);
        modelAndView.addObject("message", "Update successfully");
        return modelAndView;
    }
}
