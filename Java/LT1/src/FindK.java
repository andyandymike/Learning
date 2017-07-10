import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindK {
	private List<Integer> nums;
	private int k;
	
	FindK(List<Integer> nums, int k) {
		this.nums = nums;
		this.k = k;
	}
	
	public List<Integer> findLittleK() {
		Collections.sort(this.nums);
		List<Integer> littleK = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			littleK.add(nums.get(i));
		}
		return littleK;
	}
}
