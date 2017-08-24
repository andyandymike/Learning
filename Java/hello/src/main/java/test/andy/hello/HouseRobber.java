package test.andy.hello;

public class HouseRobber {
	
	private int[] sub;
	private int end;
	
	public long houseRobber(int[] A) {
		int len = A.length;
		if(A == null || len == 0) {
			return 0;
		}
		long[] res = new long[len];
		for(int i = 0; i < len; i++) {
			if(i == 0) {
				res[i] = A[i];
				continue;
			}
			if(i == 1) {
				res[i] = Math.max(A[0], A[1]);
				continue;
			}
			res[i] = Math.max(res[i - 1], res[i - 2] + A[i]);
		}
		return res[len - 1];
		
    }
	
	public long ohouseRobber(int[] A) {
        long max = 0;
        this.sub = A;
        this.end = A.length - 1;
        if (A.length == 0) {
        	return 0;
        }
        if (A.length == 1) {
        	return A[0];
        }
        if (A.length == 2) {
        	return A[0] > A[1]? A[0] : A[1];
        }
        for (int i = 0; i < 2; i++) {
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
		if (start >= sub.length) {
			return 0;
		}
        if (sub.length == start + 2 || sub.length == start + 1) {
        	max = sub[start];
        	return max;
        }
        long max2 = sub[start] + rFindMaxSub(start + 2);
        long max3 = sub[start] + rFindMaxSub(start + 3);
        return max2 > max3? max2: max3;
        /*for(int i = start; i < sub.length; i++) {
        	long temp = sub[i] + rFindMaxSub(i + 2);
        	max = temp > max? temp : max;
        }*/
    }

}
