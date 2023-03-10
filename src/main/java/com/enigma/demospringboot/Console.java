package com.enigma.demospringboot;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.service.ICourseService;
import com.enigma.demospringboot.util.CourseKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Console implements CommandLineRunner {

    @Autowired
    ICourseService courseService;

    @Override
    public void run(String... args) throws Exception{
        Course course = new Course();
        course.setTitle("Title 1");
        course.setDescription("Description");
        course.setLink("Link");

        courseService.create(course);

        Course course1 = new Course();
        course1.setTitle("Title 2");
        course1.setDescription("Description");
        course1.setLink("Link");

        courseService.create(course1);

        Optional<List<Course>> courseList = courseService.getBy(CourseKey.title, "ottt");
        System.out.println(courseList);
    }
}
