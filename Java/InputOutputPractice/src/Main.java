import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// TODO: 输入上次成绩(int)
		System.out.println("Input last result:");
		int lastResult = scanner.nextInt();

		// TODO: 输入本次成绩(int)
		System.out.println("Input this result:");
		int thisResult = scanner.nextInt();
		
		// TODO: 输出成绩提高的百分比，保留两位小数(例如:21.75%)
		System.out.printf("You result rasied %.2f%%\n", (double)(thisResult - lastResult));
		
	}

}
