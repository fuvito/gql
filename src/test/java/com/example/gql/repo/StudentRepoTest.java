package com.example.gql.repo;

import com.example.gql.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void findById() {
        final Optional<Student> student = studentRepo.findById(-1);
        assertFalse(student.isPresent());
    }

    @Test
    public void save() {
        final Student student = new Student();
        final long ext = new Date().getTime();
        student.setFirstName("First" + ext);
        student.setLastName("Last" + ext);

        final Student studentSaved = studentRepo.save(student);
        assertNotNull(studentSaved);
        assertNotNull(studentSaved.getId());
        assertEquals(studentSaved.getFirstName(), student.getFirstName());
        assertEquals(studentSaved.getLastName(), student.getLastName());

        final Optional<Student> studentFound = studentRepo.findById(studentSaved.getId());
        assertTrue(studentFound.isPresent());
        assertEquals(studentSaved, studentFound.get());
    }
}