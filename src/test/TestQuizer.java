package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import assign.Quiz;
import assign.Quizer;
import assign.User;

public class TestQuizer {
	private Quizer test;
	
	@Before
	public void setUp()
	{
	   test = new Quizer();
	}
		
	@Test
	public void testQuizer() {
		assertNotNull(test);
	}
	
	@Test
	public void testDeserializeUser() {
		Quiz quiz = new Quiz();
		quiz = test.deserializeQuiz("random");	//no such file so it returns null object
		assertNull(quiz);
	}

	@Test
	public void testDeserializeQuiz() {
		User user = new User("new");
		user = test.deserializeUser("student.ser");	//file exists
		assertNotNull(user);
		
		user = test.deserializeUser("randomfile");	//file doesn't exists
		assertNull(user);	
	}
}
