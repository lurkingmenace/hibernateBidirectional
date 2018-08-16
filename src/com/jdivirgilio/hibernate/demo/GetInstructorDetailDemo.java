package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") // Default name of file. Not
																					// necessary to include here.
																					// Must be in class path though!
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();


		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		// get the instructor detail object
		int id = 2; // checked via MySQL workbench;
		InstructorDetail detail =
				session.get(InstructorDetail.class, id);
		
		// print the instructor detail
		System.out.println("Instructor Detail: " + detail);
				
		// print the associated instructor
		System.out.println("Instructor: " + detail.getInstructor());
		
		session.getTransaction().commit();
	
	}
}
