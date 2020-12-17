import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Student extends JFrame implements Runnable {

	private JLabel name, schoolID, questionsAsked, questionsAnswered;
	private JTextField nameText, schoolIDText;
	private JTextArea questionsAskedText, questionsAnsweredText;
	private UserInformation userInformation;

	public Student() {
		// TODO Auto-generated constructor stub
		setSize(500, 500);
		setTitle("Student");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 2));

		userInformation = new UserInformation();

		name = new JLabel("Name:");
		schoolID = new JLabel("School ID:");
		questionsAsked = new JLabel("Questions Asked:");
		questionsAnswered = new JLabel("Questions Answered:");

		nameText = new JTextField("Yiðit Can Ayaz");
		schoolIDText = new JTextField("14401");

		questionsAskedText = new JTextArea(userInformation.getQuestionsAskedCount() + "");
		questionsAnsweredText = new JTextArea(userInformation.getQuestionsAnsweredCount() + "");

		questionsAskedText.setFont(new Font("Consolas", Font.BOLD, 30));
		questionsAnsweredText.setFont(new Font("Consolas", Font.BOLD, 30));

		nameText.setEditable(false);
		schoolIDText.setEditable(false);
		questionsAskedText.setEditable(false);
		questionsAnsweredText.setEditable(false);

		add(name);
		add(nameText);
		add(schoolID);
		add(schoolIDText);
		add(questionsAsked);
		add(questionsAskedText);
		add(questionsAnswered);
		add(questionsAnsweredText);

		setVisible(true);
	}

	public void answerQuestion() throws InterruptedException {
		synchronized (userInformation) {
			while (userInformation.getQuestionsAskedValue() == 0) {
				JOptionPane.showMessageDialog(null, "No Question to Answer... Waiting for teacher to ask new questions",
						"Minimum Value", JOptionPane.INFORMATION_MESSAGE);
				userInformation.wait(10000);
			}
		}
		synchronized (userInformation) {
			Thread.sleep(5000);
			userInformation.decreaseQuestionsAskedValue();
			System.out.println("Student solved a new question");
			System.out.println("Unsolved Questions: " + userInformation.getQuestionsAskedValue());
			System.out.println("**********************************");
			questionsAskedText.setText(userInformation.getQuestionsAskedCount() + "");
			questionsAnsweredText.setText(userInformation.getQuestionsAnsweredCount() + "");
			userInformation.notify();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				answerQuestion();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
