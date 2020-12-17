
public class Main {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Teacher());
		Thread t2 = new Thread(new Student());

		t1.start();
		t2.start();
	}

}
