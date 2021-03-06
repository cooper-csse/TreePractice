package TreePractice2;

/**
 * More Binary Tree practice problems. This problem creates BSTs of type
 * Integer: 1. Neither problem makes use of the BST ordering property; I just
 * found insert() to be a convenient way to build trees for testing. 2. I used
 * Integer instead of T since the makeTree method sets the data value of each
 * node to be a depth, which is an Integer.
 * 
 * @author Matt Boutell and Cooper Anderson.
 */

public class BinarySearchTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * This constructor creates a full tree of Integers, where the value of each
	 * node is just the depth of that node in the tree.
	 * 
	 * @param maxDepth
	 *            The depth of the leaves in the tree.
	 */
	public BinarySearchTree(int maxDepth) {
		if (maxDepth < 0) this.root = NULL_NODE;
		else {
			this.root = new BinaryNode(0);
			this.root.insertToDepth(maxDepth, 0);
		}
	}

	public int getSumOfHeights() {
		if (this.root == NULL_NODE) return -1;
		return this.root.getSumOfHeights()[0];
	}

	// These are here for testing.
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public int[] getSumOfHeights() {
			int[] left = this.left != NULL_NODE ? this.left.getSumOfHeights() : new int[] {0, 0};
			int[] right = this.right != NULL_NODE ? this.right.getSumOfHeights() : new int[] {0, 0};
			return new int[] {left[0] + right[0] + Math.max(left[1], right[1]), Math.max(left[1], right[1]) + 1};
		}

		public void insertToDepth(Integer maxDepth, Integer currentDepth) {
			if (currentDepth < maxDepth) {
				this.left = new BinaryNode(currentDepth + 1);
				this.right = new BinaryNode(currentDepth + 1);
				this.left.insertToDepth(maxDepth, currentDepth + 1);
				this.right.insertToDepth(maxDepth, currentDepth + 1);
			}
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "[" + left.toStructuredString() + this.data
					+ right.toStructuredString() + "]";
		}
	}
}