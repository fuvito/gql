package com.example.gql.service;

import com.example.gql.model.Student;
import com.example.gql.repo.StudentRepo;
import graphql.com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepo studentRepo;

    public StudentService(@Autowired StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> listAll() {
        LOGGER.debug("retrieving all");
        return ImmutableList.copyOf(studentRepo.findAll());
    }

    public Student read(final Long id) {
        LOGGER.debug("Read Student by id: " + id);
        final Optional<Student> student = studentRepo.findById(id);
        // or throw exception?
        return student.orElse(null);
    }

    public Student create(final Student student) {
        LOGGER.debug("Create Student: " + student);
        if (student.getId() != null) {
            LOGGER.error("Create requires NULL student id: " + student);
            return null;
        }

        return studentRepo.save(student);
    }

    public Student update(final Student student) {
        LOGGER.debug("Update Student: " + student);
        if (student.getId() == null) {
            LOGGER.error("Update requires student id: " + student);
            return null;
        }

        return studentRepo.save(student);
    }

    public Student delete(final Long id) {
        LOGGER.debug("Delete Student by id: " + id);
        final Student deleted = read(id);
        if (deleted == null) {
            // log message
            return null;
        }

        studentRepo.deleteById(id);

        return deleted;
    }

}
