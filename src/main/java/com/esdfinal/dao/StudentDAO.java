package com.esdfinal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esdfinal.exception.CustomException;
import com.esdfinal.pojo.Student;

@Component
public class StudentDAO extends DAO{
    public void save(Student student) throws CustomException {
        try {
            begin();
            getSession().save(student);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not create student ", e);
        }
    }
    
    
    public void delete(Student student) throws CustomException {
        try {
            begin();
            getSession().delete(student);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not delete the student", e);
        }
    }
    
    public void update(Student student) throws CustomException {
        try {
            begin();
            getSession().update(student);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not update student ", e);
        }
    }

	public Student checkStudLogin(String email_id, String pass_word) {
		// TODO Auto-generated method stub
		Query query = getSession().getNamedQuery("checkstudentinfo");
		query.setParameter("email_id",email_id);
		query.setParameter("pass_word",pass_word);
		
		return (Student)query.uniqueResult();
	}

	public Student checkStud(String email_id) {
		// TODO Auto-generated method stub
		Query query = getSession().getNamedQuery("checkStud");
		query.setParameter("email_id",email_id);
		
		return (Student)query.setMaxResults(1).uniqueResult();		
	}
	
    public List<Student> list() {
		Query<Student> query = getSession().createQuery("FROM Student");
		List<Student> list = query.list();
		return list;
	}
}
