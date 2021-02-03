import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] ints = {-5, -4, 4, 2, 3, 6};
		System.out.println(Arrays.stream(ints).map(Math::abs).sorted().findFirst().getAsInt());
		Node node = new Node();
		node.value = 1;
		node.left = null;
		node.right = null;
		Node node1 = new Node();
		node1.right = node;
		node1.left = null;
		node1.value = 2;

		Node node2 = new Node();
		node2.right = node;
		node2.left = null;
		node2.value = 3;
		node.left = node2;
		System.out.println(findByVal(node1, 3));
	}

	public static Node findByVal(Node node, int val) {
		if (node == null) return null;
		if (node.value == val) return node;
		Node retval = findByVal(node.left, val);
		if (retval != null) return retval;
		return findByVal(node.right, val);
	}
}

class Node {
	Node left, right;
	int value;

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
