
public class StringFunc {
	private final String input;
	
	public StringFunc(String input) {
		this.input = input;
	}
	
	public char firstOneChar() {
		int[] hashTable = new int[256];
		for(int i = 0; i < 256; i++) {
			hashTable[i] = 0;
		}
		for(int i = 0; i < this.input.length(); i++) {
			hashTable[(int)(this.input.charAt(i))]++;
		}
		for(int i = 0; i < this.input.length(); i++) {
			if(hashTable[(int)(this.input.charAt(i))] == 1) {
				return this.input.charAt(i);
			}
		}
		return '\0';
	}

}
