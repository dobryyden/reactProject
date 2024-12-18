package com.mrybakova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSave() {
        Task taskToSave = new Task();
        taskToSave.setTitle("Task 1");
        taskToSave.setCompleted(false);

        assert webTestClient
                .post().uri("/tasks")
                .bodyValue(taskToSave)
                .exchange()
                .expectBody(Task.class)
                .returnResult().getResponseBody() != null;
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setCompleted(false);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setCompleted(false);

        webTestClient.post().uri("/tasks")
                .bodyValue(task1)
                .exchange()
                .expectBody(Task.class);

        webTestClient.post().uri("/tasks")
                .bodyValue(task2)
                .exchange()
                .expectBody(Task.class);

        webTestClient.get().uri("/tasks")
                .exchange()
                .expectBodyList(Task.class)
                .hasSize(2);
    }

    @Test
    void testGetTaskById() {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setCompleted(false);

        webTestClient.post().uri("/tasks")
                .bodyValue(task1)
                .exchange()
                .expectBody(Task.class);

        assert webTestClient.get().uri("/tasks/task/1")
                .exchange()
                .expectBody(Task.class)
                .returnResult().getResponseBody() != null;
    }

    @Test
    void testDeleteById(){
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setCompleted(false);

        webTestClient.post().uri("/tasks")
                .bodyValue(task1)
                .exchange()
                .expectBody(Task.class);

        assert webTestClient.delete().uri("/tasks/delete/1")
                .exchange()
                .expectBody(Task.class)
                .returnResult().getStatus().equals(HttpStatus.NO_CONTENT);
    }
}