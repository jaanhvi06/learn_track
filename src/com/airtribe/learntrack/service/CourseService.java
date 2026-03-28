package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository = new CourseRepository();

    public void addCourse(String courseName, String description, int durationInWeeks) {
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courseRepository.addCourse(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public Course findCourseById(int id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with ID: " + id);
        }
        return course;
    }

    public boolean deactivateCourse(int id) {
        Course course = courseRepository.findById(id);
        if (course != null) {
            course.setActive(false);
            return true;
        }
        return false;
    }
}