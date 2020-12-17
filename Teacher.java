import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Teacher extends JFrame implements Runnable {

	private JLabel name, lessonSubject, questionsAsked, questionsAnswered;
	private JTextField nameText, lessonSubjectText;
	private JTextArea questionsAskedText, questionsAnsweredText;
	private UserInformation userInformation;
	private long start = System.currentTimeMillis();
	private long end = start + 60 * 100; // 60 seconds * 1000 ms/sec
	private final int LIMIT = 5;
	private int x = 0;

	public Teacher() {
		// TODO Auto-generated constructor stub
		setSize(500, 500);
		setTitle("Teacher");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 2));

		userInformation = new UserInformation();

		name = new JLabel("Name:");
		lessonSubject = new JLabel("Lesson Subject:");
		questionsAsked = new JLabel("Questions Asked:");
		questionsAnswered = new JLabel("Questions Answered:");

		nameText = new JTextField("Murat Orhun");
		lessonSubjectText = new JTextField("CMPE 261");

		questionsAskedText = new JTextArea(userInformation.getQuestionsAskedCount() + "");
		questionsAnsweredText = new JTextArea(userInformation.getQuestionsAnsweredCount() + "");
		questionsAskedText.setFont(new Font("Consolas", Font.BOLD, 30));
		questionsAnsweredText.setFont(new Font("Consolas", Font.BOLD, 30));

		nameText.setEditable(false);
		lessonSubjectText.setEditable(false);
		questionsAskedText.setEditable(false);
		questionsAnsweredText.setEditable(false);

		add(name);
		add(nameText);
		add(lessonSubject);
		add(lessonSubjectText);
		add(questionsAsked);
		add(questionsAskedText);
		add(questionsAnswered);
		add(questionsAnsweredText);

		setVisible(true);
	}

	public void askQuestion() throws InterruptedException {

		synchronized (userInformation) {
			while (userInformation.getQuestionsAskedValue() == LIMIT) {
				JOptionPane.showMessageDialog(null,
						"Questions have reached the maximum unsolved value. Waiting for answers!", "Maximum Value",
						JOptionPane.INFORMATION_MESSAGE);
				userInformation.wait(25000);
			}
		}
		synchronized (userInformation) {
			Thread.sleep(2000);
			userInformation.increaseQuestionsAskedValue();
			System.out.println("Teacher asked a new question!");
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
				askQuestion();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
