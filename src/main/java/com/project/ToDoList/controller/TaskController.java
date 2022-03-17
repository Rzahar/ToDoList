package com.project.ToDoList.controller;

import com.project.ToDoList.entity.Task;
import com.project.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping({"/list", "/"})
    public ModelAndView getAllTasks() {
        ModelAndView mav = new ModelAndView("list-tasks");
        mav.addObject("tasks", taskRepository.findAll());
        return mav;
    }

    @GetMapping("/add-task")
    public ModelAndView addTaskForm() {
        ModelAndView mav = new ModelAndView("add-task");
        Task newTask = new Task();
        mav.addObject("task", newTask);
        return mav;
    }
}

