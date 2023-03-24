package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.util.constants.CourseKey;
import com.enigma.demospringboot.util.specification.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    Page<Course> list(Integer page, Integer size, String direction, String sortBy) throws Exception;
    Course create(Course course) throws Exception;
    Optional<Course> get(String id) throws Exception;
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
    List<Course> getBy(CourseKey key, String value) throws Exception;
    List<Course> listBy(SearchCriteria searchCriteria) throws Exception;
}
