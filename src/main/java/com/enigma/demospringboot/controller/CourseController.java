package com.enigma.demospringboot.controller;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.service.ICourseService;
import com.enigma.demospringboot.util.CourseKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    ICourseService courseService;

    @GetMapping
    public ResponseEntity getAllCourse() {
        List<Course> courseList = courseService.list();
        return ResponseEntity.status(HttpStatus.OK).body(courseList);
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody Course course) {
        Course newCourse = courseService.create(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Course> course = courseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Course course, @PathVariable String id) {
        courseService.update(course, id);
        return ResponseEntity.status(HttpStatus.OK).body("Update successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value) {
        Optional<List<Course>> courses = courseService.getBy(CourseKey.valueOf(key), value);
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }
}
