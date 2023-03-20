package com.esdfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.validation.BindingResult;

import com.esdfinal.dao.CourseDAO;
import com.esdfinal.dao.EnrollmentDAO;
import com.esdfinal.dao.StudentDAO;
import com.esdfinal.exception.CustomException;
import com.esdfinal.pojo.Course;
import com.esdfinal.pojo.Enrollment;
import com.esdfinal.pojo.Student;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EnrollmentController {

	@GetMapping("/enroll.htm")
	public String enrollViewGet(HttpServletRequest request, EnrollmentDAO enrollmentdao) {
		
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		Student mydetails = ((Student)request.getSession().getAttribute("stdlogin"));
		
		if(mydetails.isAdmin()) {
			request.setAttribute("Enrollment", enrollmentdao.list());
			return "enroll";
		}
		else {
			request.setAttribute("Enrollment", enrollmentdao.personallist(mydetails.getStudent_id()));
			return "enroll";
		}
	}
	
	@GetMapping("/enroll-course.htm")
	public String enrollCourseGet(HttpServletRequest request) {
		
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}

		return "enroll-course";
	}
	
	@PostMapping("/enroll-course-next.htm")
	public String enrollcoursenextPost(HttpServletRequest request, EnrollmentDAO enrollmentdao, CourseDAO coursedao,
			Enrollment enrollment, Course course, Student student, SessionStatus status) {
		
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		String course_id = request.getParameter("course_id");
		
		String stud_id = "" + ((Student)request.getSession().getAttribute("stdlogin")).getStudent_id();
		
		Enrollment enrollcheck = enrollmentdao.checkEnrollment(course_id, stud_id);
		
		Course coursecheck = coursedao.checkCourse(Long.parseLong(course_id));

		if(enrollcheck != null) {
			request.setAttribute("Show", "Already registered for course");
			return "display";
		}
		
		if(coursecheck == null) {
			request.setAttribute("Show", "No course available with the mentioned course id");
			return "display";
		}
		
		course.setCourse_id(Long.parseLong(course_id));		
		student.setStudent_id(Long.parseLong(stud_id));
		enrollment.setCourse(course);
		enrollment.setStudent(student);
		
		try {
			enrollmentdao.save(enrollment);
			request.setAttribute("Show", "Enrolled to Selected Course");
		} catch (CustomException e) {
			request.setAttribute("Show", "Post cannot be Deleted");
		}
		
		status.setComplete();
		return "display";
	}
	
	@GetMapping("/del-enroll.htm")
	public String removeenrollGet(HttpServletRequest request) {
		
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}

		if(((Student)request.getSession().getAttribute("stdlogin")).isAdmin())
			return "del-enroll";
		else
			return "home";
	}

	@PostMapping("/del-enroll-next.htm")
	public String removeenrollPost(HttpServletRequest request, EnrollmentDAO enrollmentdao, SessionStatus status) {
		
		if(StudentController.sessvalidchk(request).equals("invalid")) {
			request.setAttribute("showerr", "Lgn Again");
			return "lgn-err";
		}
		
		String enroll_id = request.getParameter("enroll_id");
		
		Enrollment enrollremove = enrollmentdao.checkEnrollment(Long.parseLong(enroll_id));
		
		if(enrollremove == null) {
			request.setAttribute("Msg", "Enter valid EnrollmentID");
			return "display";
		}
		
		try {
			enrollmentdao.delete(enrollremove);
			request.setAttribute("Show", "Removed the enrollment");
		} catch (CustomException e) {
			request.setAttribute("Show", "Couldn't Remove the enrollment");
		}
		
		status.setComplete(); 
		return "display";
	}
	
}
