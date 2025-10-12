package com.path2prod.medium;

import java.util.List;
import java.util.Objects;

public record Student(String name, int age, double score) {

    public Student{
        Objects.requireNonNull(name);
        Objects.requireNonNull(age);
        Objects.requireNonNull(score);
        
        if (age < 0){
            throw new IllegalArgumentException("age cannot be negative");
        }

        if (score < 0 || score > 10){
            throw new IllegalArgumentException("score must be between 0 and 10");
        }

    }

    public static List<Student> getStudents(){
        return List.of(
            new Student("John", 18, 2.5),
            new Student("Jane", 22, 9.0),
            new Student("James", 25, 5.5),
            new Student("Alice", 23, 4.5)
        );
    }

}
