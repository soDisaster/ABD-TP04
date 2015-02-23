
public class Node {
	
	protected Node[] children;
	protected Integer [] values;
	Node next;
	Node previous;
	boolean root;
	
	public Node(Node[] c, Integer[] v, Node n, Node p, boolean r){
		
		this.children = c;
		this.values = v;
		this.next = n;
		this.previous = p;
		this.root = r;
	}
	


}
