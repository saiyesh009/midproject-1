package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Student_Entity> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student_Entity getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student_Entity createStudent(Student_Entity student) {
        validateStudent(student); // Validate student data
        return studentRepository.save(student);
    }

    public Student_Entity updateStudent(Integer id, Student_Entity updatedStudent) {
        Student_Entity existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            validateStudent(updatedStudent); // Validate updated student data
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setSubjectMajor(updatedStudent.getSubjectMajor());
            existingStudent.setYearOfGraduation(updatedStudent.getYearOfGraduation());
            return studentRepository.save(existingStudent);
        }
        return null; // Handle non-existent records
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    private void validateStudent(Student_Entity student) {
        // Validation for first name and last name (no special characters)
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        
        if (!pattern.matcher(student.getFirstName()).matches()) {
            throw new IllegalArgumentException("First name contains special characters.");
        }
        
        if (!pattern.matcher(student.getLastName()).matches()) {
            throw new IllegalArgumentException("Last name contains special characters.");
        }

        // Validation for subject major
        String[] validMajors = {"maths", "physics", "chemistry", "computer science", "electrical science"};
        boolean validMajor = false;
        for (String major : validMajors) {
            if (student.getSubjectMajor().equalsIgnoreCase(major)) {
                validMajor = true;
                break;
            }
        }
        if (!validMajor) {
            throw new IllegalArgumentException("Invalid subject major.");
        }

        // Validation for year of graduation
        int graduationYear = student.getYearOfGraduation();
        if (graduationYear < 1800 || graduationYear > 2100) {
            throw new IllegalArgumentException("Invalid year of graduation.");
        }
    }
}
