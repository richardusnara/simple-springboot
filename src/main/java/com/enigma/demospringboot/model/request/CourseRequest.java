package com.enigma.demospringboot.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseRequest {
    @NotBlank(message = "{invalid.title.required}")
    private String title;
    private String description;
    @NotBlank(message = "{invalid.link.required}")
    private String link;

    @NotNull(message = "{invalid.courseinfo.required}")
    private CourseInfoRequest courseInfo;

    @NotNull(message = "{invalid.coursetype.required}")
    private CourseTypeIdRequest courseType;

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

    public CourseInfoRequest getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfoRequest courseInfo) {
        this.courseInfo = courseInfo;
    }

    public CourseTypeIdRequest getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseTypeIdRequest courseType) {
        this.courseType = courseType;
    }

    @Override
    public String toString() {
        return "CourseRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", courseInfo=" + courseInfo +
                ", courseType=" + courseType +
                '}';
    }
}
