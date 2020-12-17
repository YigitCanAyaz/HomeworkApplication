
public class UserInformation {

	private static int questionsAskedValue = 0, questionsAskedCount = 0, questionsAnsweredCount = 0;

	public int getQuestionsAskedValue() {
		return questionsAskedValue;
	}

	public void increaseQuestionsAskedValue() {
		questionsAskedValue++;
		questionsAskedCount++;
	}

	public void decreaseQuestionsAskedValue() {
		questionsAskedValue--;
		questionsAnsweredCount++;
	}

	public int getQuestionsAskedCount() {
		return questionsAskedCount;
	}

	public int getQuestionsAnsweredCount() {
		return questionsAnsweredCount;
	}

}
