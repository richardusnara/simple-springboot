package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.util.CourseKey;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> list();
    Course create(Course course);
    Optional<Course> get(String id);
    void update(Course course, String id);
    void delete(String id);
    Optional<List<Course>> getBy(CourseKey key, String value);
}
