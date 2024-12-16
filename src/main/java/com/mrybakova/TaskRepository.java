package com.mrybakova;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {
}
