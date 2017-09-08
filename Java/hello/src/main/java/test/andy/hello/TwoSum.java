package test.andy.hello;

public class TwoSum {
	
	public int[] twoSum(int[] nums, int target) {
		int[] output = new int[2];
		int[] temp = new int[nums.length];
		int jend = nums.length;
		Boolean endSet = Boolean.FALSE;
		for(int j = jend - 1; j >= 0; j--) {
			int gap = target - nums[j];
			temp[j] = gap;
			if (!endSet && nums[j] >= target) {
				jend = j;
				endSet = Boolean.TRUE;
			}
		}
		
		for(int i = 0; i <= jend; i++) {
			for(int j = 0; j <= jend; j++) {
				if (temp[j] == nums[i] && i != j) {
					output[0] = i + 1;
					output[1] = j + 1;
					return output;
				}	
			}
		}
		return output;
    }

}
