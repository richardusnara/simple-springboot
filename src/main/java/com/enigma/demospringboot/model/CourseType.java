package com.enigma.demospringboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "m_course_type")
public class CourseType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_type_id")
    private String courseTypeId;

    @Column(name = "type_name", nullable = false, length = 50, unique = true)
    private String typeName;

    @Override
    public String toString() {
        return "CourseType{" +
                "courseTypeId='" + courseTypeId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
