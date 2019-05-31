package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.Course;
import com.rgsj3.sebbs.domain.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
    public Integer countByCourse(Course course);
}
