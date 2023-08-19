package com.spring.testcase.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.testcase.entity.Student;
import com.spring.testcase.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public List<Student> getAllStudent()
	{
		return studentService.getAll();
	}
	
	@PostMapping
	public void addStudent(@RequestBody Student student)
	{
		studentService.addStudent(student);

	}
	
	@GetMapping("/id")
	public Optional<Student> getId(@RequestParam("id") Long id)
	{
	
		return studentService.getById(id);
	}
	@GetMapping("/nameAndAge")
	public List<Student> getNameAndAge(@Param("name") String name, @Param("age") Long age)
	{
		return studentService.getByNameAndAge(name, age);
	}

}
