package test.andy.hello;

public class FindDuplicate {
	
	public int findDuplicate(int[] nums) {
		int[] count = new int[nums.length];
		int depNum = 0;
		for(int i = 0; i < nums.length; i++) {
			count[nums[i] - 1]++;
		}
		for(int i = 0; i < nums.length; i++) {
			if(count[i] > 1) {
				depNum = i + 1;
				break;
			}
		}
		return depNum;
    }

}
