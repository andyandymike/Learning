package test.andy.hello;

public class Sort {

	private int[] input;

	public Sort(int[] input) {
		this.input = input;
	}

	public void printArray() {
		for (int i : input) {
			System.out.print(String.valueOf(i) + " ");
		}
		System.out.println("");
	}

	public int[] quickSort() {
		if (input.length < 2) {
			return input;
		}

		_quickSort(0, input.length - 1);

		return input;
	}
	
	public int[] selectSort() {
		if (input.length < 2) {
			return input;
		}
		
		for (int i = 0; i < input.length; i++) {
			int tmp = i;
			for (int j = i + 1; j < input.length; j++) {
				if (input[j] < input[i]) {
					tmp = j;
				}
			}
			if (tmp != i ) {
				shift(tmp, i);
			}
		}
		
		return input;
	}
	
	public int[] insertSort() {
		if (input.length < 2) {
			return input;
		}
		
		for (int i = 1; i < input.length; i++) {
			int tmp = input[i];
			int j;
			for (j = i - 1; j >= 0 && input[j] > tmp; j--) {
				input[j + 1] = input[j];
			}
			input[j + 1] = tmp;
		}
		
		return input;
	}
	
	public int[] bubbleSort() {
		if (input.length < 2) {
			return input;
		}
		
		for (int i = input.length - 1; i >= 0; i--) {
			for (int j = input.length - 1; j > input.length - 1 - i; j--) {
				if (input[j] <= input[j - 1]) {
					shift(j, j - 1);
				}
			}
			
		}
		
		return input;
	}
	
	private void shift(int index1, int index2) {
		if (index1 == index2) {
			return;
		}
		int temp = input[index1];
		input[index1] = input[index2];
		input[index2] = temp;
	}

	private void _quickSort(int start, int end) {
		if (start >= end) {
			return;
		}
		
		int value = input[start];
		int i = start;
		int j = end;

		while ( i != j) {
			while (input[j] >= value && i < j) {
				j--;
			}
			while (input[i] <= value && i < j) {
				i++;
			}
			if (i < j) {
				shift(i, j);
			}
		}
		
		shift(start, i);
		
		_quickSort(start, i - 1);
		_quickSort(i + 1, end);
	}

}
