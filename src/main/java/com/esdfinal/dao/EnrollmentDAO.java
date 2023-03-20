package com.esdfinal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esdfinal.exception.CustomException;
import com.esdfinal.pojo.Enrollment;

@Component
public class EnrollmentDAO extends DAO{
    public void save(Enrollment enrollment) throws CustomException {
        try {
            begin();
            getSession().save(enrollment);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not create enrollment ", e);
        }
    }
    
    
    public void delete(Enrollment enrollment) throws CustomException {
        try {
            begin();
            getSession().delete(enrollment);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not delete the enrollment", e);
        }
    }
    
    public void update(Enrollment enrollment) throws CustomException {
        try {
            begin();
            getSession().update(enrollment);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CustomException("Could not update enrollment ", e);
        }
    }

	public Enrollment checkEnrollment(long enroll_id) {
		// TODO Auto-generated method stub
		Query query = getSession().getNamedQuery("enrollmentinfo2");
		query.setParameter("enroll_id",enroll_id);
		
		return (Enrollment)query.uniqueResult();
	}

    public List<Enrollment> list() {
		Query<Enrollment> query = getSession().createQuery("FROM Enrollment");
		List<Enrollment> list = query.list();
		return list;
	}

	public List<Enrollment> personallist(long student_id) {
		Query query = getSession().getNamedQuery("personalenroll");
		query.setParameter("student_id",student_id);
		
		return (List<Enrollment>)query.list();
	}


	public Enrollment checkEnrollment(String course_id, String student_id) {
		Query query = getSession().getNamedQuery("enrollmentinfo");
		query.setParameter("course_id",course_id);
		query.setParameter("student_id",student_id);
		return (Enrollment)query.uniqueResult();
	}
}
