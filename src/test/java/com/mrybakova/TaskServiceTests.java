package com.mrybakova;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Import(TaskService.class)
@ExtendWith(SpringExtension.class)
@DataR2dbcTest
public class TaskServiceTests {

    private final TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
    private final TaskService taskService = new TaskService(taskRepository);

    @Test
    void testSave() {
        Task task = new Task();
        Mockito.when(taskRepository.save(task)).thenReturn(Mono.just(task));

        StepVerifier.create(taskService.save(task))
                .expectNext(task)
                .verifyComplete();

        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
    }

    @Test
    void testFindAll() {
        Task task1 = new Task();
        Task task2 = new Task();
        Mockito.when(taskRepository.findAll()).thenReturn(Flux.just(task1, task2));

        StepVerifier.create(taskService.findAll())
                .expectNext(task1)
                .expectNext(task2)
                .verifyComplete();

        Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testFindById() {
        Task task = new Task();
        Long id = 1L;
        Mockito.when(taskRepository.findById(id)).thenReturn(Mono.just(task));

        StepVerifier.create(taskService.findById(id))
                .expectNext(task)
                .verifyComplete();

        Mockito.verify(taskRepository, Mockito.times(1)).findById(id);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        Mockito.when(taskRepository.deleteById(id)).thenReturn(Mono.empty());

        StepVerifier.create(taskService.deleteById(id))
                .verifyComplete();

        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(id);
    }
}
