package com.enigma.demospringboot.controller;

import com.enigma.demospringboot.model.CourseType;
import com.enigma.demospringboot.model.request.CourseTypeRequest;
import com.enigma.demospringboot.model.response.PagingResponse;
import com.enigma.demospringboot.model.response.SuccessResponse;
import com.enigma.demospringboot.service.ICourseTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course-types")
public class CourseTypeController {
    ICourseTypeService courseTypeService;
    private ModelMapper modelMapper;

    @Autowired
    public CourseTypeController(ICourseTypeService courseTypeService, ModelMapper modelMapper) {
        this.courseTypeService = courseTypeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseTypeId") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) {
        Page<CourseType> result = courseTypeService.list(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all course type", result));
    }

    @PostMapping
    ResponseEntity createCourseType(@Valid @RequestBody CourseTypeRequest courseTypeRequest) {
        CourseType courseType = modelMapper.map(courseTypeRequest, CourseType.class);
        CourseType result = courseTypeService.create(courseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create course type", result));
    }
}
