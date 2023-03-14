package com.enigma.demospringboot.repository;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.util.constants.CourseKey;
import com.enigma.demospringboot.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseArrayRepository implements ICourseRepository{
    private IRandomStringGenerator randomStringGenerator;
    private List<Course> courses;

    @Autowired
    public CourseArrayRepository(IRandomStringGenerator randomStringGenerator) {
        this.randomStringGenerator = randomStringGenerator;
        this.courses = new ArrayList<>();
    }

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for(Course existingCourse: courses) {
            if (existingCourse.getCourseId().equals(id)) {
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course course: courses) {
            if (course.getCourseId().equals(id)) {
                courses.remove(course);
                break;
            }
        }
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course: courses) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<Course>> findBy(CourseKey by, String value) {
        List<Course> courseList = new ArrayList<>();
        for (Course course: courses) {
            switch (by) {
                case title -> {
                    if (course.getTitle().toLowerCase().contains(value)) {
                        courseList.add(course);
                    }
                }
                case description -> {
                    if (course.getDescription().toLowerCase().contains(value)) {
                        courseList.add(course);
                    }
                }
                case link -> {
                    if (course.getLink().toLowerCase().contains(value)) {
                        courseList.add(course);
                    }
                }
            }
        }

        return courseList.isEmpty() ? Optional.empty() : Optional.of(courseList);
    }
}
