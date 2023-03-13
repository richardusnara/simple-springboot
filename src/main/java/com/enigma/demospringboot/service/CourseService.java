package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.repository.ICourseRepository;
import com.enigma.demospringboot.util.CourseKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService{

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> list() {
        try {
            List<Course> courses = courseRepository.getAll();
            if (courses.isEmpty()) {
                throw new Exception("Course not found");
            }

            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            return courseRepository.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            Optional<Course> courses = courseRepository.findById(id);
            if (courses.isEmpty()) {
                throw new Exception("Course not found");
            }

            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Course>> getBy(CourseKey key, String value) {
        try {
            Optional<List<Course>> courses = courseRepository.findBy(key, value);
            if (courses.isEmpty()) {
                throw new Exception("Course not found");
            }

            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
