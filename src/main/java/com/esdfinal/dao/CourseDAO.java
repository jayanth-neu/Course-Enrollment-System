package com.esdfinal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esdfinal.exception.CustomException;
import com.esdfinal.pojo.Course;

@Component
public class CourseDAO extends DAO{
    public void save(Course course) throws CustomException {
        try {
            begin();
            getSession().save(course);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not create course ", e);
        }
    }
    
    
    public void delete(Course course) throws CustomException {
        try {
            begin();
            getSession().delete(course);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not delete the course", e);
        }
    }
    
    public void update(Course course) throws CustomException {
        try {
            begin();
            getSession().update(course);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not update course ", e);
        }
    }

	public Course checkCourse(Long course_id) {
		
		// TODO Auto-generated method stub
		Query query = getSession().getNamedQuery("courseinfo");
		query.setParameter("course_id",course_id);

		return (Course)query.uniqueResult();
	}

	
    public List<Course> list() {
		Query<Course> query = getSession().createQuery("FROM Course");
		List<Course> list = query.list();
		return list;
	}


	public Course checkCourse(String course_name) {
		// TODO Auto-generated method stub
		Query query = getSession().getNamedQuery("courseinfo2");
		query.setParameter("course_name",course_name);

		return (Course)query.uniqueResult();
	}
}
