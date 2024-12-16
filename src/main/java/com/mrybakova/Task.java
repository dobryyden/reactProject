package com.mrybakova;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "task")
public class Task {

    @Id
    private Long id;
    private String title;
    private boolean isCompleted;

    private Task() {
    }

    public Task(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}