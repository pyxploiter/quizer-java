package assign;

public class Quiz implements java.io.Serializable {
	public String title;
	public String description;
	public Question[] questions = new Question[10];
	
	public Quiz() {
		for (int i=0; i<10;i++) {
			questions[i] = new Question();
		}
	}
	
}
