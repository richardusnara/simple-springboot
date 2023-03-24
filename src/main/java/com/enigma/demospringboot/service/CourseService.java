package com.enigma.demospringboot.service;

import com.enigma.demospringboot.exception.NotFoundException;
import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.model.CourseType;
import com.enigma.demospringboot.repository.ICourseRepository;
import com.enigma.demospringboot.repository.ICourseTypeRepository;
import com.enigma.demospringboot.util.constants.CourseKey;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CourseService implements ICourseService{
    private ICourseRepository courseRepository;
    private ICourseTypeRepository courseTypeRepository;

    @Autowired
    public CourseService(ICourseRepository courseRepository, ICourseTypeRepository courseTypeRepository) {
        this.courseRepository = courseRepository;
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public Page<Course> list(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Course> result = courseRepository.findAll(pageable);
        return result;
    }

    @Override
    public Course create(Course course) {
        try {
            Optional<CourseType> courseType = courseTypeRepository.findById(course.getCourseType().getCourseTypeId());

            if (courseType.isEmpty()) {
                throw new NotFoundException("Course type is not found");
            }

            course.setCourseType(courseType.get());
            Course newCourse = courseRepository.save(course);
            return newCourse;
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data is Exist");
        }
    }

    @Override
    public Optional<Course> get(String id) {
        Optional<Course> courses = courseRepository.findById(id);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course not found");
        }

        return courses;
    }

    private List<Course> findByTitleContains(String value) {
        List<Course> courses = courseRepository.findByTitleContains(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " title is not found");
        }

        return courses;
    }

    private List<Course> findByDescriptionContains(String value) {
        List<Course> courses = courseRepository.findByDescriptionContains(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " description is not found");
        }

        return courses;
    }

    @Override
    public List<Course> getBy(CourseKey key, String val) {
        return switch (key) {
            case title -> findByTitleContains(val);
            case description -> findByDescriptionContains(val);
            default -> courseRepository.findAll();
        };
    }

    @Override
    public void update(Course course, String id) {
        try {
            Optional<Course> existingCourse = get(id);
            course.setCourseId(existingCourse.get().getCourseId());
            courseRepository.save(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Course> existingCourse = get(id);
            courseRepository.delete(existingCourse.get());
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
