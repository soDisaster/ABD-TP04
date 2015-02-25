package btree;

import java.util.Arrays;

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
	 * Indice du B+-Tree
	 */
	protected int indice;
	
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
	
	public Node(boolean r){
		this.children = new Node[5];
		this.values = new Integer[5 - 1];
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
	public Node(Node[] c, Integer[] v, Node n, Node p, boolean r){
		this.indice = 4;
		this.children = c;
		this.values = v;
		this.next = n;
		this.previous = p;
		this.root = r;
	}
	
	/* ======== */
	/* METHODES */
	/* ======== */
	
	public int getIndice() {
		return this.indice;
	}
	
	public Node[] getChildren(){
		return this.children;
	}
	
	public Integer[] getValues(){
		return this.values;
	}
	
	public Node getNext(){
		return this.next;
	}
	
	public Node getPrevious(){
		return this.previous;
	}
	
	/**
	 * Retourne vrai si le noeud courant est la racine du B+-Tree
	 *
	 * @return true si le noeud est la racine du B+-Tree, false sinon
	 */
	public boolean isRoot(){
		return this.root;
	}

	/**
	 * Retourne vrai si le noeud courant ne contient pas de pointeur vers des noeuds fils, et qu'il est donc une feuille du B+-Tree
	 *
	 * @return true si le noeud est une feuille du B+-Tree, false sinon
	 */
	public boolean isLeaf() {
		boolean empty = true;
		
		for (Object ob : this.getChildren()) {
			if (ob != null) {
				empty = false;
				break;
			}
		}
		
		return empty;
	}
	
	public void insert(int i){
	
	}
	
	public void remove(int i){
		
	}
	
	public Node search(int k) {

		/* Si k est trouvé dans les valeurs de node, i vaut son index dans le tableau */
		/* Si k n'est pas trouvé, i vaut -1 */
		int i = Arrays.asList(this.getValues()).indexOf(k);
		
		/* Si k est trouvé dans les valeurs du noeud */
		if (i != -1) {
			
			/* Si le noeud est une feuille du B+-Tree */
			if (this.isLeaf()) {
				return this;
			} else {
				return this.getChildren()[i + 1].search(k);
			}
			
		}
		
		/* Si au contraire k n'est pas présent dans les valeurs du noeud */
		else {

			/* Si le noeud est une feuille du B+-Tree */
			if (this.isLeaf()) {
				System.out.println("ERREUR: Clé non trouvé dans l'arbre");
				return null;
			} else {
				int j = 0;
				
				while (k >= this.getValues()[j]) {
					 j++;
				}

				return this.getChildren()[j].search(k);
			}
			
		}
	}
	
}
