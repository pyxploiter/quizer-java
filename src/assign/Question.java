package assign;

public class Question implements java.io.Serializable {
	public String questionType;
	public int questionNo;
	public String question;
	public String option1;
	public String option2;
	public String option3;
	public String option4;
	public String expectedAnswer;
	
	public Question(){
		
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestType(int index) {
		if (index == 0) {
			questionType = "MCQ";
		}
		else if (index == 1) {
			questionType = "Numeric";
		}
		else {
			questionType = "True/False";
		}
	}

	public String getExpectedAnswer() {
		return expectedAnswer;
	}

	public void setExpectedAnswer(String expectedAnswer) {
		this.expectedAnswer = expectedAnswer;
	}	
	
	public void setQuestionNo(int num) {
		this.questionNo = num;
	}
	
	public void showQuestion() {
		System.out.println(question);
		System.out.println(questionType);
		System.out.println(expectedAnswer);
		
	}
}
