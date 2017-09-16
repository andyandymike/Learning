package test.andy.hello;

import java.util.Arrays;

public class WiggleSort {
	
	public void toPos(int[] nums, int source, int target) {
		for (int i = source; i > target; i--) {
			int temp = nums[i - 1];
			nums[i - 1] = nums[i];
			nums[i] = temp;
		}
	}
	
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int end = nums.length - 1;
        if (end < 2) {
        	return;
        }
        for (int i = 0, j = end; j - i > 1; ) {
        	toPos(nums, j, i + 1);
        	i += 2;
        }
    }

}
