package assign;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class QuizerModel {
	   public JFrame mainFrame;
	   public JFrame quizerFrame;
	   public JPanel quizerPanel;
	   public JLabel statusLabel;
	   public JPanel controlPanel;
	   public boolean credentialCheck;
	   public int countQuiz;
	   public int i,j;
	   public ArrayList<String> quizFiles = new ArrayList<String>();
	   //private User student1;
	   //private User instructor1;
	   public User student;
	   public User instructor;
	   public Quiz tempQuiz = new Quiz();
	   public int marks;
	   public final JTextField[][] questOptions = new JTextField[10][4];
	   public final JTextField[] questText = new JTextField[10];
	   public final JTextField[] answerText = new JTextField[10];
	   public final JLabel[] questLabel = new JLabel[10];
	   public final JLabel[][] optionLabel = new JLabel[10][4];
	   public final JLabel[] answerLabel = new JLabel[10];
	   public final JLabel[] attQuestLabel = new JLabel[10];
	   public final JComboBox[] comboBox = new JComboBox[10]; 
	   public final JRadioButton[][] optionRadioButtons = new JRadioButton[10][4];
	   public final ButtonGroup[] optionRadioGroups = new ButtonGroup[10];
	   public String[] attemptAnswers = new String[10];
	   public final JRadioButton rdbtnStudent = new JRadioButton("Student");
	   public final JRadioButton rdbtnInstructor = new JRadioButton("Instructor");
	   public final JLabel attemptLabel = new JLabel("");
	   public final JTextField[] questionNoText = new JTextField[10];
	   
	   public QuizerModel() {
		 //student1 = new User("asadali", "1234", "student");
		   //instructor1 = new User("xeno", "asdf", "instructor");
		   
		   //initializing variables
		   for (i=0; i<10; i++) {
			   questLabel[i] = new JLabel("Question "+(i+1)+":", SwingConstants.LEFT);
			   answerLabel[i] = new JLabel("Answer: ", SwingConstants.LEFT);
			   attQuestLabel[i] = new JLabel("", SwingConstants.LEFT);
			   questText[i] = new JTextField(6);
			   questionNoText[i] = new JTextField(6);
			   answerText[i] = new JTextField(6);
			   comboBox[i] = new JComboBox();
			   comboBox[i].setModel(new DefaultComboBoxModel(new String[] {"MCQ", "Numeric", "True/False"}));
			   optionRadioGroups[i] = new ButtonGroup();
			   attemptAnswers[i] = new String();
			   for (j=0; j<4; j++) {
				   questOptions[i][j] = new JTextField(6);
				   optionLabel[i][j] = new JLabel("("+(j+1)+")",SwingConstants.LEFT);
				   optionRadioButtons[i][j] = new JRadioButton("");
			   }
		   }
	   }
}
