package com.mrybakova;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@WebFluxTest(TaskService.class)
public class TaskServiceTests {

    @Autowired
    private final TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
    private final TaskService taskService = new TaskService(taskRepository);

    @Test
    void testSave() {
        Task task = new Task("Task one", false);
        Mockito.when(taskRepository.save(task)).thenReturn(Mono.just(task));

        StepVerifier.create(taskService.save(task))
                .expectNext(task)
                .verifyComplete();

        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
    }
}
