package com.enigma.demospringboot.repository;

import com.enigma.demospringboot.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseTypeRepository extends JpaRepository<CourseType, String> {
}
