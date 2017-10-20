package test.andy.hello;

public class CanPartition {
	
	private int numsLength;
	private int[] nums;
	
	private boolean canPartitionByLength(int length) {
		boolean output = false;
		
		for (int i = 0; i < numsLength - length; i++) {
			int left = 0;
			int right = 0;
			for (int j = i; j < i + length; j++) {
				left += nums[j];
			}
			for (int j = 0; j < numsLength; j++) {
				if (j < i || j >= i + length) {
					right += nums[j];
				}
			}
			output = left == right ? true : false;
			if (output == true) {
				return output;
			}
		}
		
		return output;
	}
	
    public boolean canPartition(int[] nums) {
    	this.nums = nums;
    	this.numsLength = nums.length;
    	boolean output = false;
    	
    	for (int i = 1; i < numsLength; i++) {
    		output = canPartitionByLength(i);
    		if (output == true) {
    			return output;
    		}
    	}
    	
        return output;
    }
}
