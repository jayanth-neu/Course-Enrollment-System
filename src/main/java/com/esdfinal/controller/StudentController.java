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
import com.esdfinal.pojo.Student;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController {
	
	public static String sessvalidchk(HttpServletRequest request)  {
		if(request.getSession().getAttribute("stdlogin")==null) {
			return "invalid";
		}
		else 
			return "valid";
	}
	
	@GetMapping("/")
	public String dftPage() {
		return "unilogin";
	}
	
	@GetMapping("/unilogin.htm")
	public String empLoginGet() {
		return "unilogin";
	}
	
	@GetMapping("/homepage.htm")
	public String stdHomeGet(HttpServletRequest request) {
		
		if(sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		if(((Student)request.getSession().getAttribute("stdlogin")).isAdmin())
			return "home-admin";
		else
			return "home";
	}
		
	@PostMapping("/homepage.htm")
	public String stdHomePost(HttpServletRequest request, StudentDAO studentdao) {
		String email_id = request.getParameter("email_id");
		String pass_word = request.getParameter("pass_word");
		
		Student stdlogin = studentdao.checkStudLogin(email_id,pass_word);
		
		if(stdlogin == null) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		request.getSession().setAttribute("stdlogin",stdlogin);
		
		if(stdlogin.isAdmin())
			return "home-admin";
		else
			return "home";
	}
	
	@GetMapping("/lgt.htm")
	public String sessLgoutGet(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "unilogin";
	}
	
	@GetMapping("/stu-list.htm")
	public String studListViewGet(HttpServletRequest request, StudentDAO studentdao) {
		
		if(sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		request.setAttribute("AllStudents", studentdao.list());

		return "stu-list";
	}
	
	@GetMapping("/create-stu.htm")
	public String createStudGet(ModelMap model, Student student, HttpServletRequest request) {
		if(sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		// command object
		model.addAttribute("student", student);

		if(((Student)request.getSession().getAttribute("stdlogin")).isAdmin())
			return "create-stu";
		else
			return "home";
	}

	@PostMapping("/create-stu.htm")
	public String createStudPost(@ModelAttribute("student") Student student, 
			BindingResult result, SessionStatus status, StudentDAO studentdao, HttpServletRequest request) {
		
		if(sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		
		Student studentadd = studentdao.checkStud(student.getEmail_id());
		//System.out.println(student.getEmail_id());
		if(studentadd != null) {
			request.setAttribute("Show", "EmailID already there in the system");
			return "display";
		}
		
		if(student.getEmail_id().trim().equals("")) {
			request.setAttribute("Show", "EmailID can't be null");
			return "display";
		}
		
		if(student.getPass_word().trim().equals("")) {
			request.setAttribute("Show", "Password can't be null");
			return "display";
		}		
		
		try {
			studentdao.save(student);
			request.setAttribute("Show", "Student Created in the system");
		} catch (CustomException e) {
			request.setAttribute("Show", "Couldn't Add Student");
		}
		
		status.setComplete(); 
		return "display";
	}
	
	

}
