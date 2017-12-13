package test.andy.hello;

public class Cutting {
	
	private int[] temp;
	
    public int cutting(int[] prices, int n) {
        int max = 0;
        temp = new int[n + 1];
        
        if (prices.length != n) {
        	return max;
        }
        
        max = cut(prices, n);
        
        return max;
    }
    
    private int cut(int[] prices, int n) {
    	if (temp[n - 1] != 0) {
    		return temp[n - 1];
    	}
        
        for (int j = 0; j <= n; j++) {
        	int max = 0;
        	for (int i = 0; i < j; i++) {
        		max = Math.max(max, prices[i] + temp[j - i -1]);
        	}
        	temp[j] = max;
        }
        
        return temp[n];
    }

}
