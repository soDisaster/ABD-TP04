package btree;

public class Node {
	
	protected Node[] children;
	protected Integer [] values;
	Node next;
	Node previous;
	boolean root;
	
	public Node(Node[] c, Integer[] v, Node n, Node p, boolean r) {
		
		this.children = c;
		this.values = v;
		this.next = n;
		this.previous = p;
		this.root = r;
	}
	
	public Node[] getChildren() {
		return this.children;
	}
	
	public Integer[] getValues() {
		return this.values;
	}
	
	public Node getNext() {
		return this.next;
	}
	
	public Node getPrevious() {
		return this.previous;
	}
	
	public boolean isRoot() {
		return this.root;
	}


}
