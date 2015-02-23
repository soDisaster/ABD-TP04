package btree;

/**
 * Classe Node
 * 
 * Node est un noeud (interne ou feuille) d'un B+-Tree
 * 
 * @author Anne-Sophie Saint-Omer & Thomas Bernard
 */
public class Node {
	
	/* ========= */
	/* ATTRIBUTS */
	/* ========= */
	
	/**
	 * Pointeurs sur les noeuds fils du noeud courant
	 */
	protected Node[] children;
	
	/**
	 * Valeurs des clés du noeud courant
	 */
	protected Integer [] values;
	
	/**
	 * Pointeur sur le noeud suivant
	 */
	Node next;
	
	/**
	 * Pointeur sur le noeud précédent
	 */
	Node previous;
	
	/**
	 * Racine du B+-Tree
	 */
	boolean root;
	
	/* ============= */
	/* CONSTRUCTEURS */
	/* ============= */
	
	public Node(boolean r) {
		this.children = null;
		this.values = null;
		this.next = null;
		this.previous = null;
		this.root = r;
	}
	
	/**
	 * Constructeur de la classe Node
	 * 
	 * @param c
	 * 			Noeuds fils du noeud courant
	 * @param v
	 * 			Valeurs des clés du noeud courant
	 * @param n
	 * 			Pointeur sur le noeud suivant
	 * @param p
	 * 			Pointeur sur le noeud précédent
	 * @param r
	 * 			Racine du B+-Tree
	 */
	public Node(Node[] c, Integer[] v, Node n, Node p, boolean r) {
		this.children = c;
		this.values = v;
		this.next = n;
		this.previous = p;
		this.root = r;
	}
	
	/* ======== */
	/* METHODES */
	/* ======== */
	
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
