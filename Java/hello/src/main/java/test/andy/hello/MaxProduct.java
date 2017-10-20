package test.andy.hello;

import java.util.ArrayList;
import java.util.List;

public class MaxProduct {
	
	private int[] nums;
	private int length;
	
	private int calLeftRightMaxProduct(int index) {
		int leftMax = 1;
		int rightMax = 1;
		
		if(index == 0) {
			leftMax = Integer.MIN_VALUE;
		}
		if(index == length - 1) {
			rightMax = Integer.MIN_VALUE;
		}
		
		if (index > 0) {
			if (nums[index - 1] == 0) {
				leftMax = 0;
			} else {
				for(int i = index - 1; i >= 0; i--) {
					if(nums[i] != 0) {
						leftMax = leftMax * nums[i];
					} else {
						break;
					}
				}
			}
		}
		if (index < length - 1) {
			if (nums[index + 1] == 0) {
				rightMax = 0;
			} else {
				for(int i = index + 1; i <= length - 1; i++) {
					if(nums[i] != 0) {
						rightMax = rightMax * nums[i];
					} else {
						break;
					}
				}
			}
		}
		
		return leftMax >= rightMax ? leftMax : rightMax;
	}
	
	
    public int maxProduct(int[] nums) {
    	this.nums = nums;
    	this.length = nums.length;
    	
        int output = nums[0];
        
        if (length == 1) {
        	return output;
        }
        
        List<Integer> zeroMinusIndex = new ArrayList<>();
        int countZero = 0;
        int countMinus = 0;
        
        for (int i = 0; i < length; i++) {
        	if (nums[i] < 0) {
        		zeroMinusIndex.add(i);
        		countMinus++;
        	}
        	if (nums[i] == 0) {
        		zeroMinusIndex.add(i);
        		countZero++;
        	}
        }
        
        if (countZero == 0 && countMinus % 2 ==0) {
        	output = 1;
        	for (int i = 0; i < length; i++) {
        		output = output * nums[i];
        	}
        	return output;
        } else {
        	output = Integer.MIN_VALUE;
        	for(Integer index : zeroMinusIndex) {
        		int toutput = calLeftRightMaxProduct(index);
        		output = output > toutput ? output : toutput;
        	}
        }
        
        return output;
    }
}
