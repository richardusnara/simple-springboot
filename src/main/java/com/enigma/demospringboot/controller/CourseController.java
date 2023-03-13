package com.enigma.demospringboot.controller;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.model.response.ErrorResponse;
import com.enigma.demospringboot.model.response.SuccessResponse;
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
        try {
            List<Course> courseList = courseService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Course>>(
                    "Success get all course", courseList
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody Course course) {
        try {
            Course newCourse = courseService.create(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Course>("Success create course", newCourse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        try {
            Optional<Course> course = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Course>>("Success get course with id " + id, course));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Course course, @PathVariable String id) {
        try {
            courseService.update(course, id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Course>("Success update course", course));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        try {
            courseService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete course with id " + id, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value) {
        try {
            Optional<List<Course>> courses = courseService.getBy(CourseKey.valueOf(key), value);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Course>>>("Success get course with " + key, courses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }
}
