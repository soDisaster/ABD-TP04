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
	
	Node parent;
	
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
		this.parent = null;
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
	
	public Node getParent(){
		return this.parent;
	}
	
	public boolean isEmpty(){
		boolean rtr = true;
		for(int i=0; i < this.values.length; i++){
			if(this.values[i] != -1){
				rtr = false;
			}
		}
		return rtr;
	}
	
	public boolean isNotFull(){
		boolean rtr = false;
		for(int i=0; i < this.values.length; i++){
			if(this.values[i] == -1){
				rtr = true;
			}
		}
		return rtr;
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
	
	public void insert(int k){
		int i_child = -1;
		boolean present = false;
		for(int i = 0; i < this.getValues().length; i++){
			if(k == this.values[i]){
				present = true;
				i_child = i;
			}
		}
		if(present){
			if(this.isLeaf())
				System.out.println("Pas de duplication possible");
			else
			{
					this.getChildren()[i_child].insert(k);
			}
		}
		else{
			if(this.isLeaf()){
					if(this.isNotFull()){
						this.insert(k);
					}
					else{
						Integer[] values2 = null;
						for(int i = this.getValues().length/2; i < this.values.length; i++){
							values2[i] = this.values[i];
							this.values[i] = -1;
						}	
						Node n2 = new Node(null, values2, null, null, false);
					}
						
				
			}
			else{
				if(this.isNotFull()){
					for(int i = 0; i < this.values.length; i++){
						if(k <= this.values[i])
							this.getChildren()[i].insert(k);
					}
				}
			}
		}
	
	}
	
	public void remove(int i){
		if(this.isLeaf()){
			
		}
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
