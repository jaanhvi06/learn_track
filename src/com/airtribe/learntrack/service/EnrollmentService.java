package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    public void enrollStudent(int studentId, int courseId) {
        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollmentRepository.addEnrollment(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.getAllEnrollments();
    }

    public Enrollment findEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }

    public boolean updateEnrollmentStatus(int id, String status) {
        Enrollment enrollment = enrollmentRepository.findById(id);
        if (enrollment != null) {
            enrollment.setStatus(status);
            return true;
        }
        return false;
    }
}