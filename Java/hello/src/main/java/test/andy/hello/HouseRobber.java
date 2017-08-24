package test.andy.hello;

public class HouseRobber {
	
	private int[] sub;
	
	public long houseRobber(int[] A) {
        long max = 0;
        this.sub = A;
        for (int i = 0; i < A.length; i++) {
        	long cmax = rFindMaxSub(i);
        	System.out.println("index: " + String.valueOf(i) + " value: " + String.valueOf(A[i]) + " max: " + String.valueOf(cmax));
        	if (cmax > max) {
        		max = cmax;
        	}
        }
        return max;
    }
	
	private long rFindMaxSub(int start) {
		long max = 0;
        if (sub.length - start == 1) {
        	max = sub[sub.length - 1];
        	return max;
        }
        if (sub.length - start == 2) {
        	max = sub[sub.length - 1] > sub[sub.length - 2] ? sub[sub.length - 1] : sub[sub.length - 2];
        	return max; 
        }
        for(int i = start; i < sub.length; i++) {
        	long temp = sub[i] + rFindMaxSub(i + 2);
        	max = temp > max? temp : max;
        }
        return max;
    }

}
