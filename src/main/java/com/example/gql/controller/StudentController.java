package com.example.gql.controller;

import com.example.gql.model.Student;
import com.example.gql.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.gql.controller.ExceptionMessages.MSG_ENTITY_NOT_FOUND;

@RestController
@RequestMapping("/students")
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Student> listAll() {
        LOGGER.info("listing all students");
        return studentService.listAll();
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable("id") final Long id) {
        final Student student = studentService.read(id);

        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(MSG_ENTITY_NOT_FOUND, "Student", id));
        }

        return student;
    }
}
