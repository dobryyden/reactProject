package com.mrybakova;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "task")
@Getter
@Setter
@RequiredArgsConstructor
public class Task {
    @Id
    private Long id;
    private String title;
    @JsonProperty("isCompleted")
    private boolean isCompleted;
}