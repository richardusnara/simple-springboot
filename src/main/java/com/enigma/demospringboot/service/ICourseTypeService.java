package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.CourseType;
import org.springframework.data.domain.Page;

public interface ICourseTypeService {
    Page<CourseType> list(Integer page, Integer size, String sortBy, String direction);
    CourseType create(CourseType courseType);
}
