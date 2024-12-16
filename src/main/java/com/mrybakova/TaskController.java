package com.mrybakova;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Mono<Task> save(@RequestBody Task task) {
        return taskService.save(task);
    }

    @GetMapping
    public Flux<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/task")
    public Mono<Task> getTaskById(@RequestBody Long id) {
        return taskService.findById(id);
    }

    @DeleteMapping("/delete")
    public Mono<Void> deleteTaskById(@RequestBody Long id) {
        return taskService.deleteById(id);
    }
}
