package org.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate startDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private CourseName courseName;
    private LocalDate endDate;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Student> student = new HashSet<>();
    @ManyToOne
    private Teacher teacher;

    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    private void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
