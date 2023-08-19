package com.spring.testcase.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.testcase.entity.Student;
import com.spring.testcase.repository.StudentRepository;

@SpringBootTest
class StudentServiceTest
{
	@Autowired
	private StudentService studentService;
	
	@MockBean
	private StudentRepository studentRepository;
	private List<Student> students;
	private Optional<Student> student;
	@BeforeEach
	void setup()
	{
		student = Optional.of(new Student(10L,"abc",26L));
		students =new ArrayList<>();
		students.add(new Student(1L,"janu",26L));
 		Mockito.when(studentRepository.findById(10L)).thenReturn(student);
		Mockito.when(studentRepository.findAll()).thenReturn(students);
	}
	
	@Test
	public void testGetStudentBy_Id()
	{
		String student_name  = "abc";
		Optional<Student> student = studentService.getById(10L);
		assertEquals(student_name, student.get().getName());
	}
	
	@Test
	public void testGetAllStudents()
	{
		List<Student> student = studentService.getAll();
		assertEquals(1,student.size());
		assertEquals(students.get(0).getId(),student.get(0).getId());
	}
	
	@Test
	public void testAddstudent()
	{
		Student student = new Student(10L,"abc",63L);
		studentService.addStudent(student);
		verify(studentRepository).save(student);
	}
}
