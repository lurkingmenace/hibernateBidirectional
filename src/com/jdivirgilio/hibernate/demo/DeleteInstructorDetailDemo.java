package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") // Default name of file. Not
																					// necessary to include here.
																					// Must be in class path though!
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();


		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();

			// get the instructor detail object
			int id = 3; // checked via MySQL workbench;
			InstructorDetail detail = session.get(InstructorDetail.class, id);

			// print the instructor detail
			System.out.println("Instructor Detail: " + detail);

			// print the associated instructor
			System.out.println("Instructor: " + detail.getInstructor());
			
			// remove the associated object reference...break the bi-directional link
			// without this you get a stack strace saying it will be re-saved (instructor detail)
			detail.getInstructor().setInstructorDetail(null);
			session.delete(detail);

			session.getTransaction().commit();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
