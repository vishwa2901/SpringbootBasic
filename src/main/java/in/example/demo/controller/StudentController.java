
package in.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.example.demo.model.Student;
import in.example.demo.service.StudentService;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService service;

    // 1. Register student
    @PostMapping("/save")
    public Student addStudent(@Valid @RequestBody Student student) {
        return service.addStudent(student);
    }

    // 2. View all students
    @GetMapping("/")
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // 3. Find student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    // 4. Delete student by ID
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return service.deleteStudent(id);
    }

    // 5. Search student by name
    @GetMapping("/search/name")
    public List<Student> searchByName(@RequestParam String keyword) {
        return service.searchByName(keyword);
    }

    // 6. Search students by course name
    @GetMapping("/search/course")
    public List<Student> getByCourseName(@RequestParam String courseName) {
        return service.getByCourseName(courseName);
    }
}
