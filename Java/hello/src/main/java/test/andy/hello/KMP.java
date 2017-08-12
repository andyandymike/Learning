package test.andy.hello;

import java.util.ArrayList;

public class KMP {
	private final char[] target;
	private final char[] partten;
	private int[] next;
	
	public KMP(char[] target, char[] partten) {
		this.target = target;
		this.partten = partten;
	}
	
	private void generateNext() {
		int n = partten.length;
		next = new int[n];
		next[0] = -1;
		int j = 1;
		int i = 0;
		
		for(;j < n; j ++) {
			i = next[j-1];
			while(partten[i + 1] != partten[j] && i > 0) {
				i = next[i];
			}
			if(partten[i + 1] ==  partten[j]) {
				next[j] = i + 1;
			} else {
				next[j] = -1;
			}
		}
	}
	
	public ArrayList<Integer> match() {
		this.generateNext();
		ArrayList<Integer> match = new ArrayList<Integer>();
		int m = target.length;
		int n = partten.length;
		int j = 0;
		int i = -1;
		
		for(; j < m; j++) {
			while(target[j] != partten[i + 1] && i > 0) {
				i = next[i];
			}
			if(target[j] == partten[i + 1]) {
				i++;
			}
			if(i == n - 1) {
				match.add(new Integer(j - i));
				i = next[i];
			}
		}
		
		return match;
	}

}
