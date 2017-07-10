
public class IMPSort implements Sort {
	private int[] unsortedInt;
	
	public IMPSort(int[] unsortedInt) {
		this.unsortedInt = unsortedInt;
	}

	@Override
	public void quickSortInt() {
		quickSortIntRange(0, unsortedInt.length - 1);
	}
	
	private void quickSortIntRange(int left, int right) {
		if(left > right) {
			return;
		}
		int base = unsortedInt[left];
		int oriLeft = left;
		int oriRight = right;
		while(left != right) {
			while(unsortedInt[right] >= base && left < right) {
				right--;
			}
			while(unsortedInt[left] <= base && left < right) {
				left++;
			}
			
			if(left < right) {
				int temp = unsortedInt[right];
				unsortedInt[right] = unsortedInt[left];
				unsortedInt[left] = temp;
			}
		}
		
		int temp = unsortedInt[right];
		unsortedInt[right] = base;
		unsortedInt[oriLeft] = temp;
		
		quickSortIntRange(oriLeft, left - 1);
		quickSortIntRange(right + 1, oriRight);
	}

	@Override
	public int[] getQuickSortResult() {
		return unsortedInt;
	}

}
