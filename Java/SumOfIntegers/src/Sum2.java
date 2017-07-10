
public class Sum2 {

	/**
	 * 从M至N的自然数的和：M + ... + N
	 */
	public int sumM2N(int m, int n){
		int sum = 0;
		for (int i = m; i <= n; i ++) {
			sum += i;
		}
		return sum;
	}

	public static void main(String[] args) {
		int m = 50;
		int n = 100;
		// FIXME: 50 + 51 + 52 + ... + 100 = ?
		int sum = 0;
		// 检查结果是否为3825:
		Sum2 test = new Sum2();
		System.out.println(test.sumM2N(m,n));
	}

}
