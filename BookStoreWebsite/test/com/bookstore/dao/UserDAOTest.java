package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManager);

	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("fromUserDaoTest2@hotmail.com");
		user1.setFullname("user dao test2");
		user1.setPassword("passwordUserdao2");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user=new Users();
		user.setUserId(1);
		user.setEmail("yaksetig.patrick@gmail.com");
		user.setFullname("Hermes Nunez");
		user.setPassword("peponepass");
		
		user=userDAO.update(user);
		String expected="peponepass";
		String actual=user.getPassword();
		
		assertEquals(expected, actual);
		
	}

	@AfterClass
	public static void tearDownClassI() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
