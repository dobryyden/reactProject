package com.mrybakova;

import com.mrybakova.logging.Loggable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    public final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Loggable(methodType = "POST")
    public Mono<Task> save(Task task) {
        return repository.save(task);
    }

    @Loggable(methodType = "GET")
    public Flux<Task> findAll() {
        return repository.findAll();
    }

    @Loggable(methodType = "GET")
    public Mono<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Loggable(methodType = "DELETE")
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
