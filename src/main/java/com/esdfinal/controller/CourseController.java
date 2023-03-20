package com.esdfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.esdfinal.dao.CourseDAO;
import com.esdfinal.dao.StudentDAO;
import com.esdfinal.exception.CustomException;
import com.esdfinal.pojo.Course;
import com.esdfinal.pojo.Student;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CourseController {
	
	@GetMapping("/cou-list.htm")
	public String courseListGet(HttpServletRequest request, CourseDAO coursedao) {
		
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		request.setAttribute("courses", coursedao.list());

		return "cou-list";
	}
	
	@GetMapping("/create-cou.htm")
	public String createCouGet(ModelMap model, Course course, HttpServletRequest request) {
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		// command object
		model.addAttribute("course", course);

		if(((Student)request.getSession().getAttribute("stdlogin")).isAdmin())
			return "create-cou";
		else
			return "home";
	}

	@PostMapping("/create-cou.htm")
	public String createCouPost(@ModelAttribute("course") Course course, 
			BindingResult result, SessionStatus status, CourseDAO coursedao, HttpServletRequest request) {
		
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		
		Course courseadd = coursedao.checkCourse(course.getCourse_name());
		//System.out.println(student.getEmail_id());
		if(courseadd != null) {
			request.setAttribute("Show", "Course already there in the system");
			return "display";
		}
		
		if(course.getCourse_name().trim().equals("")) {
			request.setAttribute("Show", "Course Name can't be null");
			return "display";
		}
		
		if(course.getDescription().trim().equals("")) {
			request.setAttribute("Show", "Course Description can't be null");
			return "display";
		}		
		
		try {
			coursedao.save(course);
			request.setAttribute("Show", "Course Created in the system");
		} catch (CustomException e) {
			request.setAttribute("Show", "Couldn't Add Course");
		}
		
		status.setComplete(); 
		return "display";
	}
}
