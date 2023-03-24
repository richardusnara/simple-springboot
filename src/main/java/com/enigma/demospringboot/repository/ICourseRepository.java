package com.enigma.demospringboot.repository;

import com.enigma.demospringboot.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, String> {

    @Query("SELECT c FROM Course c WHERE c.title LIKE %?1%")
    List<Course> findByTitleContains(String title);

    @Query("SELECT c FROM Course c WHERE c.description LIKE %?1%")
    List<Course> findByDescriptionContains(String description);
}
