package com.squirrel.courses.dataaccess.dto;

import com.squirrel.courses.dataaccess.model.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ThemesAndCoursesDTO {

    private List<String> themes;
    private List<Course> courses;
}
