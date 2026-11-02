package in.example.demo.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import in.example.demo.exception.StudentNotFoundException;
import in.example.demo.model.Student;

@Service
public class StudentService {

    private final ArrayList<Student> students = new ArrayList<>();

    private long nextId = 1L;

    private Student copyStudent(Student student) {

        return new Student(
                student.getStudentId(),
                student.getStudentName(),
                student.getCourseName(),
                student.getEmail(),
                student.getAge()
        );
    }

    // Register student
    public Student addStudent(Student request) {

        checkNotBlank(request.getStudentName(),
                "Student name cannot be empty");

        checkNotBlank(request.getCourseName(),
                "Course name cannot be empty");

        checkNotBlank(request.getEmail(),
                "Email cannot be empty");

        Student student = new Student(
                nextId++,
                request.getStudentName().trim(),
                request.getCourseName().trim(),
                request.getEmail().trim(),
                request.getAge()
        );

        students.add(student);

        return copyStudent(student);
    }

    // Get all students
    public List<Student> getAllStudents() {

        return students.stream()
                .map(this::copyStudent)
                .toList();
    }

    // Find student by ID
    public Student getStudentById(Long id) {

        Student student = students.stream()
                .filter(item -> item.getStudentId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with ID: " + id
                        ));

        return copyStudent(student);
    }

    // Delete student
    public String deleteStudent(Long id) {

        Student student = students.stream()
                .filter(item -> item.getStudentId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with ID: " + id
                        ));

        students.remove(student);

        return "Student registration deleted successfully";
    }

    // Check empty values
    private void checkNotBlank(String value, String message) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    // Custom search 1: Search by student name
    public List<Student> searchByName(String keyword) {

        checkNotBlank(keyword, "Search keyword must not be blank");

        String searchText = keyword.trim()
                .toLowerCase(Locale.ROOT);

        return students.stream()
                .filter(student ->
                        student.getStudentName()
                                .toLowerCase(Locale.ROOT)
                                .contains(searchText))
                .map(this::copyStudent)
                .toList();
    }

    // Custom search 2: Search by course name
    public List<Student> getByCourseName(String courseName) {

        checkNotBlank(courseName, "Course name must not be blank");

        return students.stream()
                .filter(student ->
                        student.getCourseName()
                                .equalsIgnoreCase(courseName.trim()))
                .map(this::copyStudent)
                .toList();
    }
}