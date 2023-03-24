package com.enigma.demospringboot.controller;

import com.enigma.demospringboot.model.Course;
import com.enigma.demospringboot.model.request.CourseRequest;
import com.enigma.demospringboot.model.response.PagingResponse;
import com.enigma.demospringboot.model.response.SuccessResponse;
import com.enigma.demospringboot.service.ICourseService;
import com.enigma.demospringboot.util.constants.CourseKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private ICourseService courseService;
    private ModelMapper modelMapper;

    @Autowired
    public CourseController(ICourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity getAllCourse(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "courseId") String sortBy
    ) throws Exception {
        Page<Course> courses = courseService.list(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get course list", courses));
    }

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest courseRequest) throws Exception {
        System.out.println(courseRequest);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Course newCourse = modelMapper.map(courseRequest, Course.class);
//        Course newCourse = new Course();
//        newCourse.setTitle(courseRequest.getTitle());
//        newCourse.setDescription(courseRequest.getDescription());
//        newCourse.setLink(courseRequest.getLink());

        Course result = courseService.create(newCourse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse<Course>("Success create course", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {
        Optional<Course> course = courseService.get(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<Course>>("Success get course with id " + id, course));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Course course, @PathVariable String id) throws Exception {
        courseService.update(course, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<Course>("Success update course", course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        courseService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success delete course with id " + id, null));
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam @NotBlank(message = "{invalid.keyword.required}") String key, @RequestParam String value) throws Exception {
        Optional<List<Course>> courses = Optional.ofNullable(courseService.getBy(CourseKey.valueOf(key), value));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<List<Course>>>("Success get course with " + key, courses));
    }
}
