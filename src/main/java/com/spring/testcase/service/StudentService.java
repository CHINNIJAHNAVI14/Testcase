package com.spring.testcase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.testcase.entity.Student;
import com.spring.testcase.repository.StudentRepository;

@Service
public class StudentService
{
	@Autowired
	private StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) 
	{

		this.studentRepository = studentRepository;
	}
	
	public List<Student> getAll()
	{
		List<Student> student = new ArrayList<>();
		studentRepository.findAll().forEach(student::add);
		return student;
		
	}
	
	public void addStudent(Student student)
	{
		studentRepository.save(student);
	}
	
    public Optional<Student> getById(Long id)
    {
    	
    	return studentRepository.findById(id);
    }
    public List<Student> getByNameAndAge(String name, Long age)
    {
    	return studentRepository.findByNameAndFindByAge(name, age);
    }

}
