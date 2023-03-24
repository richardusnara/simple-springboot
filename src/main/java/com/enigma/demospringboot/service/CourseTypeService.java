package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.CourseType;
import com.enigma.demospringboot.repository.ICourseTypeRepository;
import com.enigma.demospringboot.util.IRandomStringGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CourseTypeService implements ICourseTypeService {
    ICourseTypeRepository courseTypeRepository;
    IRandomStringGenerator randomStringGenerator;

    @Autowired
    public CourseTypeService(ICourseTypeRepository courseTypeRepository, IRandomStringGenerator randomStringGenerator) {
        this.courseTypeRepository = courseTypeRepository;
        this.randomStringGenerator = randomStringGenerator;
    }

    @Override
    public Page<CourseType> list(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<CourseType> courses = courseTypeRepository.findAll(pageable);
        return courses;
    }

    @Override
    public CourseType create(CourseType courseType) {
        try {
            courseType.setCourseTypeId(randomStringGenerator.random());
            CourseType newCourseType = courseTypeRepository.save(courseType);
            return newCourseType;
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data is Exist");
        }
    }
}
