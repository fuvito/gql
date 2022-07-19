package com.example.gql.service;

import com.example.gql.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void read() {
        final Student student = studentService.read(-1);
        assertNull(student);
    }

    @Test
    public void createUpdateDelete() {
        final Student student = new Student();
        final long ext = new Date().getTime();
        student.setFirstName("First" + ext);
        student.setLastName("Last" + ext);

        // create
        final Student studentCreated = studentService.create(student);
        assertNotNull(studentCreated);
        assertNotNull(studentCreated.getId());
        assertEquals(studentCreated.getFirstName(), student.getFirstName());
        assertEquals(studentCreated.getLastName(), student.getLastName());

        // read
        final Student studentRead = studentService.read(studentCreated.getId());
        assertNotNull(studentRead);
        assertEquals(studentCreated, studentRead);

        // update
        studentCreated.setFirstName("AnotherFirst");
        final Student studentUpdated = studentService.update(studentCreated);
        assertNotNull(studentUpdated);
        assertEquals(studentCreated, studentUpdated);

        // delete
        final Student studentDeleted = studentService.delete(studentCreated.getId());
        assertNotNull(studentDeleted);
        assertEquals(studentCreated, studentDeleted);

        // read again deleted one
        final Student studentAlreadyDeleted = studentService.read(studentCreated.getId());
        assertNull(studentAlreadyDeleted);
    }

}