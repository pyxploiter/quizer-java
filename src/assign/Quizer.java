package assign;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Quizer{
   public QuizerModel model;
   public int i,j;
	
   public Quizer(){
	   model = new QuizerModel();
	   //serializeUser(student1,"student.ser");				//objects are serialized once
	   //serializeUser(instructor1,"instructor.ser");
	   
	   //deserializing user objects for further use
	   model.student = deserializeUser("student.ser");
	   model.instructor = deserializeUser("instructor.ser");
	   prepareGUI();
   }
   public static void main(String[] args){
	   Quizer quizerGUI = new Quizer(); 
	   //show GUI
	   quizerGUI.showGUI();
   }
   
   public void serializeUser(User user, String filename) {
       try
       {   
           //Saving of object in a file
           FileOutputStream file = new FileOutputStream(filename);
           ObjectOutputStream out = new ObjectOutputStream(file);
            
           // Method for serialization of object
           out.writeObject(user);
            
           out.close();
           file.close();
            
           System.out.println("User has been serialized");
       }
        
       catch(IOException ex)
       {
           System.out.println("IOException is caught in user serialization");
       }
   }
   
   public void serializeQuiz(Quiz quiz0, String filename) {
	   try
       {   
           //Saving of object in a file
           FileOutputStream file = new FileOutputStream(filename);
           ObjectOutputStream out = new ObjectOutputStream(file);
            
           // Method for serialization of object
           out.writeObject(quiz0);
            
           out.close();
           file.close();
            
           System.out.println("Quiz has been serialized");
       }
        
       catch(IOException ex)
       {
           System.out.println("IOException is caught in quiz serialization");
       }
   }
   
   public User deserializeUser(String filename) {
	   User user = null;
       try
       {   
           // Reading the object from a file
           FileInputStream file = new FileInputStream(filename);
           ObjectInputStream in = new ObjectInputStream(file);
            
           // Method for deserialization of object
           user = (User)in.readObject();
            
           in.close();
           file.close();
            
           System.out.println("User has been deserialized ");
       }
        
       catch(IOException ex)
       {
           System.out.println("IOException is caught in user deserialization");
       }
        
       catch(ClassNotFoundException ex)
       {
           System.out.println("ClassNotFoundException is caught");
       }
	   return user;
   }
   
   public Quiz deserializeQuiz(String filename) {
	   Quiz quiz0 = null;
	   try
       {   
           // Reading the object from a file
           FileInputStream file = new FileInputStream(filename);
           ObjectInputStream in = new ObjectInputStream(file);
            
           // Method for deserialization of object
           quiz0 = (Quiz)in.readObject();
            
           in.close();
           file.close();
            
           System.out.println("Quiz has been deserialized ");
       }
        
       catch(IOException ex)
       {
           System.out.println("IOException is caught in quiz deserailization");
       }
        
       catch(ClassNotFoundException ex)
       {
           System.out.println("ClassNotFoundException is caught");
       }
	   return quiz0;
   }
   
   
   private void prepareGUI(){
	  //create new JFrame for login window
      model.mainFrame = new JFrame("Login");
      model.mainFrame.setSize(400,400);
      
      //Listener for close button
      model.mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      model.mainFrame.getContentPane().setLayout(null);
      
      //Status Label for showing if credentials are incorrect or so.
      model.statusLabel = new JLabel("",JLabel.CENTER);    
      model.statusLabel.setLocation(0, 306);
      model.statusLabel.setSize(382,47);
      model.mainFrame.getContentPane().add(model.statusLabel);
      
      //Creating Panel that can hold components
      model.controlPanel = new JPanel();
      model.controlPanel.setBounds(0, 0, 382, 305);
      model.mainFrame.getContentPane().add(model.controlPanel);
            
      //Login Title Label
      JLabel lblLogin = new JLabel("Login");
      lblLogin.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 30));
      lblLogin.setBounds(147, 13, 79, 38);
      
      //Username label on login page
      JLabel namelabel= new JLabel("User Name:", SwingConstants.LEFT);
      namelabel.setBounds(76, 66, 79, 16);
      
      //password label on login page
      JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
      passwordLabel.setBounds(76, 131, 64, 16);
      
      //username textfield for login page
      final JTextField userText = new JTextField(6);
      userText.setBounds(76, 95, 238, 22);
      
      //password text field for login page
      final JPasswordField passwordText = new JPasswordField(6);      
      passwordText.setBounds(76, 158, 238, 22);
      passwordText.setEchoChar('*');
      
      //instructor radio button
      JRadioButton rdbtnInstructor = new JRadioButton("Instructor");
      rdbtnInstructor.setBounds(91, 194, 92, 25);
      
      //student radio button
      JRadioButton rdbtnStudent = new JRadioButton("Student");
      rdbtnStudent.setBounds(204, 194, 92, 25);
      
      //grouping both radio buttons to select each at a time
      ButtonGroup group = new ButtonGroup();
      group.add(rdbtnInstructor);
      group.add(rdbtnStudent);
      
      //Login button
      JButton loginButton = new JButton("Login");
      loginButton.setBounds(126, 241, 121, 38);
      
      //Login button listener...print username and password when clicked.
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 String username = userText.getText();
        	 String password = new String(passwordText.getPassword()); //it returns char[] so type casted to string
        	 if (rdbtnInstructor.isSelected()) {
        		 model.credentialCheck = model.instructor.login(username, password);
        	 }
        	 
        	 else if (rdbtnStudent.isSelected()) {
        		 model.credentialCheck = model.student.login(username, password);
        	 }
        	 
        	 else {
        		 model.statusLabel.setText("Invalid input.");
        	 }  
        	 
        	 //when login is successful
        	if (model.credentialCheck) {
        		//creating new frame after login
        		model.quizerFrame = new JFrame("Quizer");
        		model.quizerFrame.setSize(1400, 1000);
        		model.quizerFrame.addWindowListener(new WindowAdapter() {
        	         public void windowClosing(WindowEvent windowEvent){
        	            System.exit(0);
        	         }        
        	      });
        		 
        		model.quizerFrame.getContentPane().setLayout(null);
        		 //Creating Panel that can hold components
        		model.quizerPanel = new JPanel();
        		model.quizerPanel.setBounds(0, 0, 1400, 1000);
        		model.quizerFrame.getContentPane().add(model.quizerPanel);
        		model.quizerFrame.setVisible(true);
        		model.mainFrame.dispose();     
        	     userText.setText("");
        	     passwordText.setText("");
        		 if (rdbtnStudent.isSelected()) {
        			 attemptQuiz();
        		 }
        		 else {
        			 createQuiz();
        		 }
        	}
         }
      }); 
      
      //add all components to JPanel
      model.controlPanel.setLayout(null);
      model.controlPanel.add(lblLogin);
      model.controlPanel.add(namelabel);
      model.controlPanel.add(userText);
      model.controlPanel.add(passwordLabel);       
      model.controlPanel.add(passwordText);
      model.controlPanel.add(loginButton);
      model.controlPanel.add(rdbtnInstructor);
      model.controlPanel.add(rdbtnStudent);
      
   }
   
   private void showGUI(){
	  //make the window visible
	   model.mainFrame.setVisible(true);
   }
   
   public void createQuiz() {
	   Quiz quiz1 = new Quiz();
	   //show all questions
	   for (i=0;i<10;i++) {
		   for (j=0;j<4;j++) {
			   model.optionLabel[i][j].show();
			   model.questOptions[i][j].show();
		   }
	   }
	   model.quizerPanel.repaint();
	   //Create new Quiz label
	   JLabel quizLabel = new JLabel("Create New Quiz");
	   quizLabel.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 40));
	   quizLabel.setBounds(430, 60, 500, 50);
	   
	   //Quiz Title label
	   JLabel quizTitle = new JLabel("Title: ", SwingConstants.LEFT);
	   quizTitle.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 24));
	   quizTitle.setBounds(200, 185, 200, 20);
	   final JTextField titleText = new JTextField(6);
	   titleText.setBounds(400, 180, 400, 30);
	   
	   //Quiz description label
	   JLabel quizDescription = new JLabel("Description: ", SwingConstants.LEFT);
	   quizDescription.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 24));
	   quizDescription.setBounds(200, 265, 200, 20);
	   final JTextField descText = new JTextField(6);
	   descText.setBounds(400, 260, 800, 30);
	   
	   //Create Quiz button
	   JButton createQuizButton = new JButton("Create Quiz");
	   createQuizButton.setFont(new Font("Times", Font.PLAIN, 18));
	   createQuizButton.setBounds(700, 400, 170, 50);
	     
	   //create quiz button listener
	   createQuizButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   quiz1.title = titleText.getText();
			   quiz1.description = descText.getText();
			   
			   //clear panel by removing all components
			   model.quizerPanel.removeAll();
			   int x = 10, y = 10, w = 90, h = 30, wt = 1180, change=90;
			   int xo, yo, wo = w+100, ho = h-5;
			   
			   //setting position of all questions and option labels
			   for (i=0; i<10; i++) {
				   xo = 100; yo = y+h+10;
				   model.questLabel[i].setBounds(x, y, w, h);
				   model.questText[i].setBounds(x+w, y, wt, h);
				   model.comboBox[i].setBounds(x+w+wt, y, w, h);
				   y += change;
				   for (j=0; j<4; j++) {
					   model.optionLabel[i][j].setBounds(xo, yo-5, 30, 30);
					   model.questOptions[i][j].setBounds(xo+30, yo, wo, ho);
					   xo += wo+50;
				   }
				   model.answerLabel[i].setBounds(xo, yo-5, 80, 30);
				   model.answerText[i].setBounds(xo+60,yo,wo,ho);
				   ComboBoxEventListener(i);
			   }
			   
			   y += change;
			   //create quiz button
			   JButton createButton = new JButton("Create Quiz");
			   createButton.setBounds(600, 900, 150, h);
			   			
			   //adding all text labels and text fields to panel
			   for (i=0; i<10; i++) {
				   model.quizerPanel.add(model.questText[i]);
				   model.quizerPanel.add(model.questLabel[i]);
				   model.quizerPanel.add(model.comboBox[i]);
				   model.quizerPanel.add(model.answerLabel[i]);
				   model.quizerPanel.add(model.answerText[i]);
				   for (j=0; j<4; j++) {
					   model.quizerPanel.add(model.optionLabel[i][j]);
					   model.quizerPanel.add(model.questOptions[i][j]);
				   }
			   }
			   createButton.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e) {	   
					   for (i=0; i<10; i++) {
						   //storing questions in quiz1 object
						   quiz1.questions[i].setQuestion(model.questText[i].getText());
						   //storing question types in quiz1 object
						   quiz1.questions[i].setQuestType(model.comboBox[i].getSelectedIndex());
						   
						   //storing options in quiz1 object
						   quiz1.questions[i].option1 = model.questOptions[i][0].getText();
						   quiz1.questions[i].option2 = model.questOptions[i][1].getText();
						   quiz1.questions[i].option3 = model.questOptions[i][2].getText();
						   quiz1.questions[i].option4 = model.questOptions[i][3].getText();
						   
						   //storing answers in quiz1 object
						   quiz1.questions[i].setExpectedAnswer(model.answerText[i].getText());
						   
						   //setting question numbers
						   quiz1.questions[i].setQuestionNo(i+1);
					   }
					   //serialize the quiz
					   serializeQuiz(quiz1, quiz1.title+".ser");
					   
					   //pop-up options when quiz is created
					   String[] options = {"Create another Quiz", "Log Out", "Exit Program"};
		               int n = JOptionPane.showOptionDialog(null,quiz1.title+" is successfully created.","Create Quiz",
		            		   JOptionPane.DEFAULT_OPTION,
		            		   JOptionPane.QUESTION_MESSAGE,
		            		   null,options,options[0]);
		               if(options[n].contains("Create another Quiz")){
		            	   titleText.setText("");
		            	   descText.setText("");
		            	   for (i=0; i<10; i++) {
		            		   model.questText[i].setText("");
		            		   model.answerText[i].setText("");
		            		   model.comboBox[i].setSelectedIndex(-1);
		            		   for(j=0;j<4;j++) {
		            			   model.questOptions[i][j].setText("");		            		   
		            		   }
		            	   }
		            	   model.quizerPanel.removeAll();
		            	   createQuiz();
		               }
		               
		               else if(options[n].contains("Log Out")) {
		            	   titleText.setText("");
		            	   descText.setText("");
		            	   for (i=0; i<10; i++) {
		            		   model.questText[i].setText("");
		            		   model.answerText[i].setText("");
		            		   model.comboBox[i].setSelectedIndex(-1);
		            		   for(j=0;j<4;j++) {
		            			   model.questOptions[i][j].setText("");		            		   
		            		   }
		            	   }
		            	   model.quizerFrame.dispose();
		            	   model.mainFrame.setVisible(true);
		               }
		               
		               else if(options[n].contains("Exit Program")){
		                   System.exit(0);
		               }
				   }
			   });
			   model.quizerPanel.add(createButton);
			   model.quizerPanel.repaint();
		   }
	   });
	   
	   //adding components to the panel
	   model.quizerPanel.setLayout(null);
	   model.quizerPanel.add(quizLabel);
	   model.quizerPanel.add(quizTitle);
	   model.quizerPanel.add(quizDescription);
	   model.quizerPanel.add(titleText);
	   model.quizerPanel.add(descText);
	   model.quizerPanel.add(createQuizButton);
   }
   
   //option combobox event listening
   public void ComboBoxEventListener(int x) {
	   model.comboBox[x].addActionListener (new ActionListener () {
		   public void actionPerformed(ActionEvent event) {
			          if (model.comboBox[x].getSelectedIndex() == 0){
			        	  for (i=0;i<4;i++) {
			        		  model.optionLabel[x][i].show();
			        		  model.questOptions[x][i].show();
			        		  model.quizerPanel.repaint();
			        	  }
			          }
			          else if (model.comboBox[x].getSelectedIndex() == 1) {
			        	  for (i=0;i<4;i++) {
			        		  model.optionLabel[x][i].hide();
			        		  model.questOptions[x][i].hide();
			        		  model.quizerPanel.repaint();
			        	  }
			          }
			          else if (model.comboBox[x].getSelectedIndex() == 2){
			        	  model.optionLabel[x][0].show();
			        	  model.optionLabel[x][1].show();
			        	  model.questOptions[x][0].show();
			        	  model.questOptions[x][1].show();
			        	  model.optionLabel[x][2].hide();
			        	  model.optionLabel[x][3].hide();
			        	  model.questOptions[x][2].hide();
			        	  model.questOptions[x][3].hide();
			        	  model.quizerPanel.repaint();			        	  
			          }
	        }
		});
   }
   
   //attempt quiz controller
   public void attemptQuiz() {
	   model.countQuiz = 0;			//quiz counter
	   model.tempQuiz = new Quiz();
	   model.quizerPanel.repaint();
	   //Attempt Quiz Label
	   JLabel attemptLabel = new JLabel("Attempt Any Quiz");
	   attemptLabel.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 40));
	   attemptLabel.setBounds(400, 50, 500, 50);
	    
	   //using system commands to find quiz files in working directory
	   try {
	         String cmd = "cmd /C dir /b | find \".ser\"";
	         // create a process and execute cmd 
	         Process process = Runtime.getRuntime().exec(cmd,null);
	         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
	         String line = null;
	     
	         while ((line = in.readLine()) != null) {
	        	 //collect all serialized files other than instructor or student 
	        	 if (!(line.equals("instructor.ser") || line.equals("student.ser"))) {
	        		 //System.out.println(line);
	        		 model.quizFiles.add(line);
	        		 model.countQuiz++;
	        	 }
	         }
	         
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	   
	   //Create Quiz buttons
	   JButton[] quizButtons = new JButton[model.countQuiz];
	   int x=500, y=150, w=150, h=40;
	   for (i=0; i<model.countQuiz; i++) {
		   quizButtons[i] = new JButton("Quiz "+(i+1));
		   quizButtons[i].setBounds(x,y,w,h);
		   model.quizerPanel.add(quizButtons[i]);
		   model.tempQuiz = deserializeQuiz(model.quizFiles.get(i));
		   quizButtonEventListener(quizButtons[i], model.tempQuiz);
		   y+=60;
	   }
	   
	   //if there is no quiz generated by instructor
	   if (model.countQuiz < 1) {
		   //No Quiz Label
		   JLabel noQuizLabel = new JLabel("Currently, There is no Quiz!!");
		   noQuizLabel.setFont(new Font("Times", Font.PLAIN, 40));
		   noQuizLabel.setBounds(300, 300, 500, 50); 
		   model.quizerPanel.add(noQuizLabel);
	   }
	   
	   model.quizerPanel.setLayout(null);
	   model.quizerPanel.add(attemptLabel);
   }
   
   //attempt quiz button listener
   public void quizButtonEventListener(JButton quizButtons, Quiz tempQuiz) {
	   quizButtons.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   attemptGUI(tempQuiz);
		   }
	   });
   }
   
   //attempt quiz GUI
   public void attemptGUI(Quiz recQuiz) {
	   model.marks = 0;
	   model.quizerPanel.removeAll();
	   model.quizerPanel.repaint();
	   //Quiz Title Label
	   JLabel attQuizTitle = new JLabel(recQuiz.title);
	   attQuizTitle.setFont(new Font("Times", Font.PLAIN, 30));
	   attQuizTitle.setBounds(400, 5, 800, 60);
	   
	   //Quiz Description Label
	   JLabel attQuizDesc = new JLabel(recQuiz.description);
	   attQuizDesc.setFont(new Font("Times", Font.PLAIN, 18));
	   attQuizDesc.setBounds(200, 60, 1000, 20);
	   
	   //printing all questions and options
	   int x=10, y=120, w=90,h=20, xo;
	   for (i=0; i<10; i++) {
		   xo = x;
		   model.optionRadioButtons[i][0].setText(recQuiz.questions[i].option1);
		   model.optionRadioButtons[i][1].setText(recQuiz.questions[i].option2);
		   model.optionRadioButtons[i][2].setText(recQuiz.questions[i].option3);
		   model.optionRadioButtons[i][3].setText(recQuiz.questions[i].option4);
		   for (j=0;j<4;j++) {
			   model.optionRadioGroups[i].add(model.optionRadioButtons[i][j]);
			   model.optionRadioButtons[i][j].setFont(new Font("Times", Font.PLAIN, 14));
			   model.optionRadioButtons[i][j].setBounds(xo, y+30,320,h);
			   xo += 325;
			   model.quizerPanel.add(model.optionRadioButtons[i][j]);
		   }
		   
		   model.questLabel[i].setBounds(x,y,w,h);
		   model.questLabel[i].setFont(new Font("Times", Font.BOLD,15));
		   model.attQuestLabel[i].setText(recQuiz.questions[i].getQuestion());
		   model.attQuestLabel[i].setFont(new Font("Times", Font.PLAIN, 16));
		   model.attQuestLabel[i].setBounds(x+w, y, w+1090, h);
		   y+=70;
		   
		   if (recQuiz.questions[i].questionType.equals("MCQ")) {
			  
				   for(j=0;j<4;j++) {
					   model.optionRadioButtons[i][j].show();
				   }
			   
		   }
		   else if (recQuiz.questions[i].questionType.equals("True/False")) {
			   model.optionRadioButtons[i][0].show();
			   model.optionRadioButtons[i][1].show();
			   model.optionRadioButtons[i][2].hide();
			   model.optionRadioButtons[i][3].hide();
		   }
		   else {
			   model.answerLabel[i].setBounds(x, y-40, 80, 30);
			   model.answerText[i].setBounds(x+70, y-37,400,25);
			   for (j=0;j<4;j++) {
				   model.optionRadioButtons[i][j].hide();
			   }
			   model.quizerPanel.add(model.answerLabel[i]);
			   model.quizerPanel.add(model.answerText[i]);
		   }
		   model.quizerPanel.add(model.attQuestLabel[i]);
		   model.quizerPanel.add(model.questLabel[i]);
	   }
	   
	   //submit quiz button
	   JButton submitQuiz = new JButton("Submit Quiz");
	   submitQuiz.setBounds(510,y+20,150,40);
	   //cancel button
	   JButton cancelQuiz = new JButton("Cancel");
	   cancelQuiz.setBounds(670,y+20,110,40);
	   
	   //cancel button event listener
	   cancelQuiz.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   for (i=0;i<10;i++) {
				   model.optionRadioGroups[i].clearSelection();
				   model.answerText[i].setText("");
			   }
			   model.quizerPanel.removeAll();
			   attemptQuiz();
		   }
	   });
	   
	   //submit quiz button event listener
	   submitQuiz.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {	
			   for (i=0;i<10;i++) {
				   if (!recQuiz.questions[i].questionType.equals("Numeric")) {
					   for(j=0;j<4;j++) {
						   if (model.optionRadioButtons[i][j].isSelected()) {
							   model.attemptAnswers[i] = model.optionRadioButtons[i][j].getText();
						   }
						   if (recQuiz.questions[i].questionType.equals("True/False") && j>1) {
							   break;
						   }
					   }
				   }
				   else {
					   model.attemptAnswers[i] = model.answerText[i].getText();
				   }
				   
				   if (recQuiz.questions[i].expectedAnswer.equals(model.attemptAnswers[i]) && !model.attemptAnswers[i].equals("")) {
					   model.marks++;
				   }
			   }
			   
			   //pop-up box after submitting quiz
			   String[] options = {"Attempt another Quiz", "Log Out", "Exit Program"};
               int n = JOptionPane.showOptionDialog(null,"Your Obtained Marks: "+model.marks,"Result",
            		   JOptionPane.DEFAULT_OPTION,
            		   JOptionPane.QUESTION_MESSAGE,
            		   null,options,options[0]);
               if(options[n].contains("Attempt another Quiz")){
            	   for (i=0;i<10;i++) {
            		   model.optionRadioGroups[i].clearSelection();
            		   model.answerText[i].setText("");
            	   }
            	   model.quizerPanel.removeAll();
            	   attemptQuiz();
               }
               
               else if(options[n].contains("Log Out")) {
            	   for (i=0;i<10;i++) {
            		   model.optionRadioGroups[i].clearSelection();
            		   model.answerText[i].setText("");
            	   }
            	   model.quizerPanel.removeAll();
            	   model.quizerFrame.dispose();
            	   model.mainFrame.setVisible(true);
               }
               
               else if(options[n].contains("Exit Program")){
                   System.exit(0);
               }
               model.student.score = model.marks;
		   }
	   });
	   
	   //adding components to panel 
	   model.quizerPanel.add(cancelQuiz);
	   model.quizerPanel.add(submitQuiz);
	   model.quizerPanel.add(attQuizDesc);
	   model.quizerPanel.add(attQuizTitle);
	   model.quizerPanel.repaint();
   }
}