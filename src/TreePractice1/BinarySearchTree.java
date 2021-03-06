package TreePractice1;

/**
 * Binary Tree practice problems
 * 
 * @author Matt Boutell and Cooper Anderson. 2014.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the four methods below. I took most of them from
 * a CSSE230 exam given in a prior term. These can all be solved by recursion -
 * I encourage you to do so too, since most students find practicing recursion
 * to be more useful.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * This method counts the number of occurrences of positive Integers in the
	 * tree that is of type Integer. Hint: You may assume this tree contains
	 * integers, so may use a cast.
	 * 
	 * @return The number of positive integers in the tree.
	 */
	public int countPositives() {
		return this.root != NULL_NODE ? this.root.countPositives() : 0;
	}

	/**
	 * Recall that the depth of a node is number of edges in a path from this
	 * node to the root. Returns the depth of the given item in the tree. If the
	 * item isn't in the tree, then it returns -1.
	 * 
	 * @param item
	 * @return The depth, or -1 if it isn't in the tree.
	 */
	public int getDepth(T item) {
		return this.root.getDepth(item) - 1;
	}

	/**
	 * This method visits each node of the BST in pre-order and determines the
	 * number of children of each node. It produces a string of those numbers.
	 * If the tree is empty, an empty string is to be returned. For the
	 * following tree, the method returns the string: "2200110"
	 * 
	 * 10 5 15 2 7 18 10
	 * 
	 * @return A string representing the number of children of each node when
	 *         the nodes are visited in pre-order.
	 */
	public String numChildrenOfEachNode() {
		return this.root != NULL_NODE ? this.root.numChildrenOfEachNode("") : "";
	}

	/**
	 * This method determines if a BST forms a zig-zag pattern. By this we mean
	 * that each node has exactly one child, except for the leaf. In addition,
	 * the nodes alternate between being a left and a right child. An empty tree
	 * or a tree consisting of just the root are both said to form a zigzag
	 * pattern. For example, if you insert the elements 10, 5, 9, 6, 7 into a
	 * BST in that order. , you will get a zig-zag.
	 * 
	 * @return True if the tree forms a zigzag and false otherwise.
	 */
	public boolean isZigZag() {
		return this.root == NULL_NODE || this.root.isZigZag(0);
	}

	public void insert(T e) {
		root = root.insert(e);
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public T element;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(T element) {
			this.element = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode insert(T e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(element) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(element) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public int countPositives() {
			return ((Integer) this.element > 0 ? 1 : 0) + (this.left != NULL_NODE ? this.left.countPositives() : 0) + (this.right != NULL_NODE ? this.right.countPositives() : 0);
		}

		public int getDepth(T item) {
			if (this == NULL_NODE)
				return 0;
			int depth;
			int direction = item.compareTo(this.element);
			if (direction == 0)
				return 1;
			else if (direction > 0)
				depth = this.right.getDepth(item);
			else
				depth = this.left.getDepth(item);
			return depth != 0 ? depth + 1 : 0;
		}

		public String numChildrenOfEachNode(String output) {
			return output + "012".charAt((this.left != NULL_NODE ? 1 : 0) + (this.right != NULL_NODE ? 1 : 0))
					+ (this.left != NULL_NODE ? this.left.numChildrenOfEachNode(output) : "")
					+ (this.right != NULL_NODE ? this.right.numChildrenOfEachNode(output) : "");
		}

		public boolean isZigZag(int direction) {
			if (this.left == this.right) return true;
			if (this.right == NULL_NODE)
				return direction != -1 && this.left.isZigZag(-1);
			if (this.left == NULL_NODE)
				return direction != 1 && this.right.isZigZag(1);
			return false;
		}
	}
}