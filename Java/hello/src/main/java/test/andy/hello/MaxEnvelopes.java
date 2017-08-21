package test.andy.hello;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MaxEnvelopes {
	
	private class Sort implements Comparator<int[]> {

		@Override
		public int compare(int[] o1, int[] o2) {
			if (o1[0] > o2[0]) {
				return 1;
			}
			if (o1[0] < o2[0]) {
				return -1;
			}
			if (o1[1] > o2[1]) {
				return 1;
			}
			if (o1[1] < o2[1]) {
				return -1;
			}
			return 0;
		}
		
	}
	
	private class TreeNode {
		
		public final int[] self;
		public List<TreeNode> childList;
		public final int level;
		public final int index;
		
		public TreeNode(int[] self, int index, int level) {
			this.self = self;
			this.index = index;
			this.level = level;
			this.childList = new LinkedList<TreeNode>();
			
		}
	}
	
	private class Tree {
		
		public final int[][] ori;
		public int height;
		public TreeNode root;
		
		public Tree(int[][] ori, TreeNode root) {
			this.ori = ori;
			this.height = 0;
			this.root = root;
		}
		
		public void build() {
			buildTree(root);
		}
		
		public void buildTree(TreeNode node) {
			if (node.level > height) {
				height = node.level;
			}
			
			for(int i = node.index; i < ori.length; i++) {
				if (ori[i][0] > node.self[0] && ori[i][1] > node.self[1]) {
					TreeNode child = new TreeNode(ori[i], i, node.level + 1);
					node.childList.add(child);
					buildTree(child);
				}
			}
		}
	}
	
	public int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, new Sort());
		int max = 0;
		for(int i = 0; i < envelopes.length; i++) {
			TreeNode root = new TreeNode(envelopes[i], i, 1);
			Tree tree = new Tree(envelopes, root);
			tree.build();
			// System.out.println("[" + String.valueOf(envelopes[i][0]) + ", " + String.valueOf(envelopes[i][1]) + "] : "+ String.valueOf(tree.height));
			if (tree.height > max) {
				max = tree.height;
			}
		}
		return max;
    }

}
