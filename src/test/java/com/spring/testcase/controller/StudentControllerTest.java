package com.spring.testcase.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.spring.testcase.entity.Student;
import com.spring.testcase.service.StudentService;


@WebMvcTest(StudentController.class)
class StudentControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	
	@Test
	public void testGetAll() throws Exception
	{
		List<Student> student = new ArrayList<>();
		student.add(new Student(10L,"abc",25L));
		student.add(new Student(25L,"dcf",45L));
		
		when(studentService.getAll()).thenReturn((student));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
         .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(student.size()))
         .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(student.get(0).getId()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(student.get(0).getName()))
         .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(student.get(0).getAge()))
         .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(student.get(1).getId()))
         .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(student.get(1).getName()))
         .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(student.get(1).getAge()))
         .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testAdd() throws Exception
	{
		Student student = new Student(10L,"abc",25L);
		String studentJson = "{\"id\": 10, \"name\": \"abc\", \"age\": 25}";
		doNothing().when(studentService).addStudent(student);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testId() throws Exception
	{
		Student student = new Student(10L,"abc",25L);
		
		when(studentService.getById(10L)).thenReturn(Optional.of(student));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/student/id").param("id", "10"))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(student.getId()))
		 .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(student.getName()))
         .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(student.getAge()))
 		 .andExpect(MockMvcResultMatchers.status().isOk());
		                                    
	}
	@Test
    public void testFetchDataFromDatabase() {
        List<Student> students = studentService.getAll(); // Fetch data from the real database

        for (Student student : students) {
            System.out.println(student);
        }
    }
}




















































