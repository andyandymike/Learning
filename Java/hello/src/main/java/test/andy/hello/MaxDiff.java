package test.andy.hello;

public class MaxDiff {
	
	private int max;
	
	public int maxDiff(int[][] arrs) {
		max = 0;
		
		for ( int i = 0; i < arrs.length; i++ ) {
			int iLength = arrs[i].length;
			if ( iLength == 0 ) {
				continue;
			}
			for ( int j = 0; j < arrs.length; j++) {
				int jLength = arrs[j].length;
				if (jLength == 0 || i == j) {
					continue;
				}
				int diff = Math.abs(arrs[j][jLength - 1] - arrs[i][0]);
				max = diff > max ? diff : max;
			}
		}
		
        return max;
    }

}
