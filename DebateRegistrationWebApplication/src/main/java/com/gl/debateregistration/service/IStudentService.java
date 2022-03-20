package com.gl.debateregistration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.debateregistration.entity.Student;

@Service
public interface IStudentService {

	public List<Student> findAll();

	public Student findById(int studentId);

	public boolean save(Student student);

	public boolean deleteById(int customerId);
}
