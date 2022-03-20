package com.gl.debateregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.debateregistration.entity.Student;
import com.gl.debateregistration.service.IStudentService;

@Controller
@RequestMapping("/registration")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@RequestMapping("/")
	public String welcome() {
		return "redirect:./..";
	}

	@RequestMapping("/registrations")
	public String getRegistrations(Model model) {

		List<Student> students = studentService.findAll();
		model.addAttribute("Registrations", students);
		return "registrations";
	}

	@RequestMapping("/registration-form")
	public String showRegistrationForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "registration-form";
	}

	@RequestMapping("/edit-registration-details")
	public String editRegistration(@RequestParam("studentId") int studentId, Model model) {
		Student student = studentService.findById(studentId);
		if (student != null) {
			model.addAttribute("student", student);
			return "registration-form";
		}
		return "redirect:./message?msg=" + "Failed to find student registration with id " + studentId;
	}

	@PostMapping("/save")
	public String register(Student studentModel, Model model) {
		Student student;
		if (studentModel.getStudentId() == 0) {
			student = new Student(studentModel.getStudentId(), studentModel.getName(), studentModel.getDepartment(),
					studentModel.getCountry());
		} else {
			student = studentService.findById(studentModel.getStudentId());
			if (student != null) {

				student.setName(studentModel.getName());
				student.setDepartment(studentModel.getDepartment());
				student.setCountry(studentModel.getCountry());
			}
		}

		return studentService.save(student) ? "redirect: ./registrations"
				: "redirect:./message?msg=Failed to save record. Please try again!!";

	}

	@RequestMapping("/delete-registration")
	public String delete(@RequestParam("studentId") int studentId) {
		return studentService.deleteById(studentId) ? "redirect:./registrations"
				: "redirect:./message?msg=" + "Failed to delete student registration record with id " + studentId;
	}

	@RequestMapping("/message")
	public String showMessagePage(@RequestParam("msg") String message, Model model) {
		model.addAttribute("message", message);
		return "message";
	}

}
