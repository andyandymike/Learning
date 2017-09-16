package test.andy.hello;

public class MinCost {
	public int minCost(int[][] costs) {
        int length = costs.length;
        if (length == 0) {
        	return 0;
        }
        int[] red = new int[length];
        int[] blue = new int[length];
        int[] green = new int[length];
        red[0] = costs[0][0];
        blue[0] = costs[0][1];
        green[0] = costs[0][2];
        for(int i = 1; i < length; i++) {
        	red[i] = Math.min(blue[i - 1], green[i - 1]) + costs[i][0];
        	blue[i] = Math.min(red[i - 1], green[i - 1]) + costs[i][1];
        	green[i] = Math.min(blue[i - 1], red[i - 1]) + costs[i][2];
        }
        return Math.min(Math.min(red[length - 1], blue[length - 1]), green[length - 1]);
    }
	
	private int findMin(int[] input, int ignore) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (i != ignore && input[i] < min) {
				min = input[i];
			}
		}
		return min;
	}
	
	public int minCostII(int[][] costs) {
        int houseNum = costs.length;
        if (houseNum == 0) {
        	return 0;
        }
        int colorNum = costs[0].length;
        int[][] colors = new int[houseNum][colorNum];
        for (int i = 0; i < colorNum; i++) {
        	colors[0][i] = costs[0][i];
        }
        for (int i = 1; i < houseNum; i++) {
        	for (int j = 0; j < colorNum; j++) {
        		colors[i][j] = findMin(colors[i - 1], j) + costs[i][j];
        	}
        }
        return findMin(colors[houseNum - 1], -1);
    }
}
