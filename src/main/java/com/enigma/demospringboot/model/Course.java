package com.enigma.demospringboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "m_course")
public class Course {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String courseId;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    private String title;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "link", nullable = false, length = 200)
    private String link;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_info_id", referencedColumnName = "course_info_id")
    private CourseInfo courseInfo;

    @ManyToOne
    @JoinColumn(name = "course_type_id", referencedColumnName = "course_type_id", nullable = false)
    private CourseType courseType;

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", courseInfo=" + courseInfo +
                ", courseType=" + courseType +
                '}';
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
