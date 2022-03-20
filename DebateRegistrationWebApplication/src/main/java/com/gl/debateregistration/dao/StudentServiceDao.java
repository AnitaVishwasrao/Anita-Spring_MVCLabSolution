package com.gl.debateregistration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gl.debateregistration.entity.Student;
import com.gl.debateregistration.service.IStudentService;

@Repository
public class StudentServiceDao implements IStudentService {
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public StudentServiceDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	@Override
	public List<Student> findAll() {

		Transaction transaction = session.beginTransaction();
		List<Student> students = session.createQuery("from Student", Student.class).list();
		transaction.commit();

		return students;
	}

	@Transactional
	@Override
	public Student findById(int studentId) {
		Student student = new Student();
		Transaction transaction = session.beginTransaction();
		student = session.get(Student.class, studentId);
		transaction.commit();

		return student;
	}

	@Transactional
	@Override
	public boolean save(Student student) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(student);

			// transaction.commit();
		} catch (Exception ex) {
			System.out.println("Failed to save student " + ex.getMessage());
			return false;
		} finally {
			if (transaction != null) {
				session.flush();
				transaction.commit();
			}
		}
		return true;
	}

	@Transactional
	@Override
	public boolean deleteById(int studentId) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Student student = session.get(Student.class, studentId);
			session.delete(student);
		} catch (Exception ex) {
			System.out.println("Failed to save student " + ex.getMessage());
			return false;
		} finally {
			if (transaction != null)
				transaction.commit();
		}

		return true;
	}

}
