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
@NamedQuery(name = "courseinfo", query = "FROM Course WHERE course_id=:course_id")
@NamedQuery(name = "courseinfo2", query = "FROM Course WHERE course_name=:course_name")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long course_id;
	private String course_name;
	private String description;
	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
