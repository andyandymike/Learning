package test.andy.hello;

public class MinSubString {
	private String source;
	private String target;
	private int srcLength;
	private int tgtLength;
	
	public MinSubString(String source, String target) {
		this.source = source;
		this.target = target;
		this.srcLength = source.length();
		this.tgtLength = target.length();
	}

	public String findMinSubString() {
		String output = "";
		
		int[] srcHash = new int[128];
		int[] tgtHash = new int[128];
		
		for (int i = 0; i < tgtLength; i ++) {
			tgtHash[target.charAt(i)]++;
		}
		
		int found = 0;
		int begin = -1, end = srcLength, minLength = srcLength;
		int start = 0;
		for (int i = 0; i < srcLength; i++) {	
			srcHash[source.charAt(i)]++;
			
			if (srcHash[source.charAt(i)] <= tgtHash[source.charAt(i)]) {
				found++;
			}
			
			if (found == tgtLength) {
				while (start < i && srcHash[source.charAt(start)] > tgtHash[source.charAt(start)]) {
					srcHash[source.charAt(start)]--;
					start++;
				}
				if (i - start < minLength) {
					minLength = i - start;
					begin = start;
					end = i;
				}
				srcHash[source.charAt(start)]--;
				found--;
				start++;
			}
		}
		
		output = begin == -1 ? "" : source.substring(begin, end + 1);
		
		return output;
	}

}
