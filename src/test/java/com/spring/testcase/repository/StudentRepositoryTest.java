package com.spring.testcase.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.spring.testcase.entity.Student;

@SpringJUnitConfig
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @MockBean
    private StudentRepository mockStudentRepository;

    @Test
     void testFindByNameAndFindByAge()
    {
        String name = "abc";
        Long age = 25L;

        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "abc", 25L));
        students.add(new Student(2L, "def", 50L));

        when(mockStudentRepository.findByNameAndFindByAge(name, age)).thenReturn(students);

        List<Student> result = studentRepository.findByNameAndFindByAge(name, age);

        verify(mockStudentRepository).findByNameAndFindByAge(name, age);

        assertEquals(2, result.size());
        assertEquals(name, result.get(0).getName());
        assertEquals(age, result.get(0).getAge());
        assertEquals(name, result.get(1).getName());
        assertEquals(age, result.get(1).getAge());
    }
}






