package test;

import static org.junit.Assert.*;

import org.junit.Test;

import assign.User;

public class TestUser {
	@Test
	public void TestUser() {
		User testuser = new User("Asad");
		assertNotNull(testuser);
	}
	
	@Test
	public void testLogin() {
		User testuser = new User("Asad");
		testuser.setPassword("password123");
		boolean actual;
		actual = testuser.login("Asad", "password123");
		assertTrue(actual);
		
		actual = testuser.login("Asad", "password");
		assertFalse(actual);	
	}
	
	@Test
	public void testGetName() {
		User testuser = new User("Asad","1234","Student");
		String expected = "Asad";
		String actual = testuser.getName();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetRole() {
		User testuser = new User("Fahad Satti","1234","Instructor");
		String expected = "Instructor";
		String actual = testuser.getRole();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetScore() {
		User testuser = new User("Uzair");
		int expected = 9;
		testuser.setScore(expected);
		int actual = testuser.getScore();
		assertEquals(expected, actual);
	}
}
