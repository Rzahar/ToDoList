package com.project.ToDoList.controller;

import com.project.ToDoList.entity.Task;
import com.project.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// URL : http://localhost:8080/ ou http://localhost:8080/list
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

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForme(@RequestParam Long taskId){
        ModelAndView mav = new ModelAndView("add-task");
        Task task = taskRepository.findById(taskId).get();
        mav.addObject("task",task);
        return mav;
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam Long taskId) {
        taskRepository.deleteById(taskId);
        return "redirect:/list";
    }

}

