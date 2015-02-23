package btree;

public class Main {

	public static void main(String[] args) {
		// Tests
		Node node = new Node(true);
		System.out.println(node.isLeaf());
		node.getChildren()[2] = new Node(false);
		System.out.println(node.isLeaf());
	}

}
