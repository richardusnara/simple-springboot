package com.enigma.demospringboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "m_course_info")
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_info_id")
    private String courseInfoId;

    @Column(name = "duration", length = 50, nullable = false)
    private String duration;

    @Column(name = "level", length = 50, nullable = false)
    private String level;

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseInfoId='" + courseInfoId + '\'' +
                ", duration='" + duration + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public String getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(String courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
