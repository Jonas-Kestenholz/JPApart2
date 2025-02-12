package org.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createdAt;
    @Column(unique = true)
    private String email;
    private String name;
    private LocalDateTime updateAt;

    @ManyToOne
    private Course courses;
    @PrePersist
    private void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    @PostUpdate
    private void setUpdateAt() {
        updateAt = LocalDateTime.now();
    }
}
