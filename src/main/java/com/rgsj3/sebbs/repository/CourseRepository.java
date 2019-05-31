package com.rgsj3.sebbs.repository;


import com.rgsj3.sebbs.domain.Course;
import com.rgsj3.sebbs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    public  Course findCourseByTeacherIs(User teacher);
    public List<Course> findAllByTeacherIs(User teacher);
}
