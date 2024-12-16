package com.mrybakova;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    public final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Mono<Task> save(Task task) {
        return repository.save(task);
    }

    public Flux<Task> findAll() {
        return repository.findAll();
    }

    public Mono<Task> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
