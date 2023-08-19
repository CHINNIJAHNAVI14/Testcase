package com.spring.testcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.testcase.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
	 @Query("SELECT u FROM Student u WHERE u.name=?1 AND u.age=?2")
	   List<Student> findByNameAndFindByAge(String name,Long age);	

}
