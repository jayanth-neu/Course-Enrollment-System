package com.esdfinal.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.springframework.stereotype.Component;

@Component
@Entity
@NamedQuery(name = "personalenroll", query = "FROM Enrollment WHERE student_student_id=:student_id")
@NamedQuery(name = "enrollmentinfo", query = "FROM Enrollment WHERE student_student_id=:student_id and course_course_id=:course_id")
@NamedQuery(name = "enrollmentinfo2", query = "FROM Enrollment WHERE enroll_id=:enroll_id")
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long enroll_id;
	@ManyToOne
	private Student student;
	@ManyToOne
	private Course course;
	public long getEnroll_id() {
		return enroll_id;
	}
	public void setEnroll_id(long enroll_id) {
		this.enroll_id = enroll_id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	

}
