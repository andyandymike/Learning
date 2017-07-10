
public class NumsFunc {
	private final int[] nums;
	
	public NumsFunc(int[] nums) {
		this.nums = nums;
	}
	
	public void findAloneNums() {
		int sum = 0;
		for(int num: this.nums) {
			sum ^= num;
		}
		System.out.println(Integer.toBinaryString(sum));
		int num1 = 0, num2 = 0;
		int index = this.findFirstBit1(sum);
		for(int num: this.nums) {
			if(this.isBit1(num, index)) {
				num1 ^= num;
			} else {
				num2 ^= num;
			}
		}
		System.out.println("num1: "+Integer.toString(num1)+" num2: "+Integer.toString(num2));
	}
	
	private int findFirstBit1(int num) {
		int index = 0;
		while((num & 1) == 0) {
			num = num >> 1;
			index++;
		}
		return index;
	}
	
	private boolean isBit1(int num, int index) {
		num = num >> index;
		if((num & 1) == 1) {
			return true;
		} else {
			return false;
		}
	}

}
