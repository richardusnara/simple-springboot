package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.util.constants.CourseKey;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> list() throws Exception;
    Course create(Course course) throws Exception;
    Optional<Course> get(String id) throws Exception;
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
    List<Course> getBy(CourseKey key, String value) throws Exception;
}
