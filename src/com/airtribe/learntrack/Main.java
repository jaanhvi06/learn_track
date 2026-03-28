package com.airtribe.learntrack;

import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();

        boolean running = true;

        while (running) {
            System.out.println("\n=== LearnTrack System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. View Courses");
            System.out.println("5. Enroll Student");
            System.out.println("6. View Enrollments");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Batch: ");
                    String batch = scanner.nextLine();

                    studentService.addStudent(firstName, lastName, email, batch);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (studentService.getAllStudents().isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        studentService.getAllStudents().forEach(student ->
                                System.out.println("[ID: " + student.getId() + "] " + student.getDisplayName())
                        );
                    }
                    break;

                case 3:
                    System.out.print("Course Name: ");
                    String courseName = scanner.nextLine();

                    System.out.print("Description: ");
                    String description = scanner.nextLine();

                    System.out.print("Duration (weeks): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();

                    courseService.addCourse(courseName, description, duration);
                    System.out.println("Course added!");
                    break;

                case 4:
                    if (courseService.getAllCourses().isEmpty()) {
                        System.out.println("No courses available.");
                    } else {
                        courseService.getAllCourses().forEach(course ->
                                System.out.println("[ID: " + course.getId() + "] " + course.getCourseName())
                        );
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Student ID: ");
                        int studentId = scanner.nextInt();

                        System.out.print("Course ID: ");
                        int courseId = scanner.nextInt();
                        scanner.nextLine();

                        studentService.findStudentById(studentId);
                        courseService.findCourseById(courseId);

                        enrollmentService.enrollStudent(studentId, courseId);
                        System.out.println("Enrollment successful! ✨");

                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                        scanner.nextLine(); // recover input
                    }
                    break;

                case 6:
                    if (enrollmentService.getAllEnrollments().isEmpty()) {
                        System.out.println("No enrollments found.");
                    } else {
                        enrollmentService.getAllEnrollments().forEach(enrollment ->
                                System.out.println(
                                        "[Enrollment ID: " + enrollment.getId() +
                                                "] Student ID: " + enrollment.getStudentId() +
                                                ", Course ID: " + enrollment.getCourseId() +
                                                ", Status: " + enrollment.getStatus()
                                )
                        );
                    }
                    break;

                case 7:
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }
}