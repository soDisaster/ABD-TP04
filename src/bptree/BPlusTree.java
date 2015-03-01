package bptree;

import java.util.Arrays;

/**
 * Classe BPlusTree
 * 
 * @author Anne-Sophie Saint-Omer & Thomas Bernard
 */
public class BPlusTree {

	/* ========= */
	/* ATTRIBUTS */
	/* ========= */

	/**
	 * Dégré du B+-Tree
	 */
	protected int degree;

	/**
	 * Racine du B+-Tree
	 */
	protected Node rootNode;

	/* ============ */
	/* CONSTRUCTEUR */
	/* ============ */

	/**
	 * Constructeur de la classe BPlusTree
	 * 
	 * @param degree
	 *            Degré du B+-Tree
	 */
	public BPlusTree(int degree) {
		this.degree = degree;
		this.rootNode = new Node();
		this.rootNode.isLeafNode = true;
	}

	/* ======== */
	/* METHODES */
	/* ======== */

	/* ========== RECHERCHE ========== */

	/**
	 * Recherche d'une clé dans le B+-Tree et retourne la valeur associée
	 * 
	 * @param key
	 *            Clé à chercher dans le B+-Tree
	 * @return la valeur associée à cette clé si elle est présente dans le
	 *         B+-Tree, null sinon
	 */
	public Object search(int key) {
		return this.search(this.rootNode, key);
	}

	private Object search(Node node, int key) {
		int indice = 0;

		/*
		 * Parcours des clés à la recherche de l'indice de la clé passée en
		 * paramètre, si elle est présente
		 */
		while (indice < node.numberKeys && key > node.keys[indice]) {
			indice++;
		}

		/* La clé est trouvée dans le noeud, on retourne la valeur associée */
		if (indice < node.numberKeys && key == node.keys[indice]) {
			return node.values[indice];
		}

		/* La clé n'est pas trouvée et le noeud est une feuille, on s'arrête */
		if (node.isLeafNode) {
			return null;
		}

		/* Recherche récursive dans le noeud fils correspondant à l'indice */
		return this.search(node.childNodes[indice], key);
	}

	/**
	 * Recherche d'une clé dans le B+-Tree et retourne le Node contenant cette
	 * clé
	 * 
	 * @param key
	 *            Clé à chercher dans le B+-Tree
	 * @return le Node qui contient cette clé si elle est présente dans le
	 *         B+-Tree, null sinon
	 */
	public Node searchNode(int key) {
		return this.searchNode(this.rootNode, key);
	}

	private Node searchNode(Node node, int key) {
		int indice = 0;

		/*
		 * Parcours des clés à la recherche de l'indice de la clé passée en
		 * paramètre, si elle est présente
		 */
		while (indice < node.numberKeys && key > node.keys[indice]) {
			indice++;
		}

		/* La clé est trouvée dans le noeud, on retourne celui-ci */
		if (indice < node.numberKeys && key == node.keys[indice]) {
			node.display();
			return node;
		}

		/* La clé n'est pas trouvée et le noeud est une feuille, on s'arrête */
		if (node.isLeafNode) {
			return null;
		}

		/* Recherche récursive dans le noeud fils correspondant à l'indice */
		return this.searchNode(node.childNodes[indice], key);
	}

	/* ========== INSERTION ========== */

	/**
	 * Ajoute une clé associée à une valeur au B+-Tree
	 * 
	 * @param key
	 *            Clé à ajouter dans le B+-Tree
	 * @param object
	 *            Valeur associée à la clé
	 */
	public void add(int key, Object object) {
		Node rootNode = this.rootNode;

		if (rootNode.isFull()) {
			/* Création d'une nouvelle racine */
			Node newRootNode = new Node();
			this.rootNode = newRootNode;
			newRootNode.isLeafNode = false;
			this.rootNode.childNodes[0] = rootNode;

			/* Split rootNode et ajoute sa clé centrale aux clés de newRootNode */
			splitChildNode(newRootNode, 0, rootNode);
			
			/* Ajoute la clé au B+-Tree avec cette nouvelle racine */
			insertIntoNonFullNode(newRootNode, key, object);
		} else {
			/* Ajoute la clé au B+-Tree avec la racine actuelle */
			insertIntoNonFullNode(rootNode, key, object);
		}
	}

	/**
	 * Split le noeud passé en paramètre en deux noeuds qui contiennent
	 * respectivement degré/2 - 1 et degré/2 éléments Ajoute sa clé centrale aux
	 * clés du noeud parent passé en paramètre
	 * 
	 * Cette méthode est appelée uniquement si le noeud est plein Le noeud passé
	 * en paramètre doit être le i-ème fils du noeud parentNode
	 * 
	 * @param parentNode
	 *            Noeud parent
	 * @param i
	 *            Indice de node en tant que fils de parentNode
	 * @param node
	 *            Noeud à spliter
	 */
	private void splitChildNode(Node parentNode, int i, Node node) {
		/* Création d'un nouveau noeud */
		Node newNode = new Node();
		newNode.isLeafNode = node.isLeafNode;
		newNode.numberKeys = this.degree / 2;
		
		/*
		 * Copie les degré/2 derniers éléments du noeud passé en paramètre dans
		 * newNode
		 */
		for (int j = 0; j < (this.degree / 2); j++) {
			newNode.keys[j] = node.keys[j + (this.degree / 2) - 1];
			newNode.values[j] = node.values[j + (this.degree / 2) - 1];
		}

		/*
		 * Si le nouveau noeud est un noeud interne, copie les degré/2 + 1
		 * pointeurs de childNodes dans newNode
		 */
		if (!newNode.isLeafNode) {
			for (int j = 0; j < (this.degree / 2); j++) {
				newNode.childNodes[j] = node.childNodes[j + (this.degree / 2)];
			}
			for (int j = (this.degree / 2); j <= node.numberKeys; j++) {
				node.childNodes[j] = null;
			}
		} else {
			/*
			 * Si le nouveau noeud est une feuille, on l'ajoute à la liste des
			 * feuilles
			 */
			newNode.nextNode = node.nextNode;
			node.nextNode = newNode;
		}
		
		/* Suppression des degré/2 derniers éléments du noeud après copie */
		for (int j = (this.degree / 2) - 1; j < node.numberKeys; j++) {
			node.keys[j] = 0;
			node.values[j] = null;
		}
		node.numberKeys = (this.degree / 2) - 1;

		/* AJoute un pointeur vers newNode aux enfants de parentNode */
		for (int j = parentNode.numberKeys; j >= i + 1; j--) {
			parentNode.childNodes[j + 1] = parentNode.childNodes[j];
		}
		
		parentNode.childNodes[i + 1] = newNode;
		
		for (int j = parentNode.numberKeys - 1; j >= i; j--) {
			parentNode.keys[j + 1] = parentNode.keys[j];
			parentNode.values[j + 1] = parentNode.values[j];
		}

		parentNode.keys[i] = newNode.keys[0];
		parentNode.values[i] = newNode.values[0];
		parentNode.numberKeys++;
	}

	/**
	 * Ajoute un couple clé-valeur au B+-Tree dans un noeud qui n'est pas plein
	 * 
	 * @param node
	 *            Noeud dans lequel insérer la clé
	 * @param key
	 *            Clé à insérer
	 * @param object
	 *            Valeur associée à la clé passée en paramètre
	 */
	void insertIntoNonFullNode(Node node, int key, Object object) {
		int i = node.numberKeys - 1;

		if (node.isLeafNode) {
			/* Ajoute la clé à sa place dans le noeud */
			while (i >= 0 && key < node.keys[i]) {
				node.keys[i + 1] = node.keys[i];
				node.values[i + 1] = node.values[i];
				i--;
			}
			i++;
			node.keys[i] = key;
			node.values[i] = object;
			node.numberKeys++;
		} else {
			/*
			 * Parcours des clés de node jusqu'à trouver le pointeur sur le
			 * noeud qui fait office de racine pour le sous-arbre du nouvel
			 * élément
			 */
			while (i >= 0 && key < node.keys[i]) {
				i--;
			}
			i++;
			if (node.childNodes[i].numberKeys == (2 * (this.degree / 2) - 1)) {
				this.splitChildNode(node, i, node.childNodes[i]);
				if (key > node.keys[i]) {
					i++;
				}
			}
			this.insertIntoNonFullNode(node.childNodes[i], key, object);
		}
	}

	/* ========== SUPPRESSION ========== */

	public void delete(int key) {
		this.delete(this.rootNode, key);
	}

	private void delete(Node node, int key) {
		System.out.println("La suppression d'une clé du B+-Tree n'est pas implémentée");
		return;
	}

	/* ========== AFFICHAGE ========== */

	public void display() {
		this.display(this.rootNode);
	}

	private void display(Node node) {
		node.display();

		for (int i = 0; i < node.keys.length; i++) {
			Node child = node.childNodes[i];

			if (child != null) {
				this.display(child);
			}
		}
	}

	public void display2() {
		this.display2(this.rootNode, "");
	}

	private void display2(Node node, String indent) {
		node.display2(indent);

		for (int i = 0; i < node.childNodes.length; i++) {
			Node child = node.childNodes[i];

			if (child != null) {
				this.display2(child, indent + "        ");
			}
		}
	}

	/**
	 * Classe Node
	 * 
	 * Node est un noeud (interne ou feuille) d'un B+-Tree
	 * 
	 * @author Anne-Sophie Saint-Omer & Thomas Bernard
	 */
	private class Node {

		/* ========= */
		/* ATTRIBUTS */
		/* ========= */

		/**
		 * Nombre de clés insérées dans le noeud
		 */
		protected int numberKeys;

		/**
		 * Clés contenues dans le noeud
		 */
		protected int[] keys;

		/**
		 * Valeurs associées aux clés contenues dans le noeud
		 */
		protected Object[] values;

		/**
		 * Pointeurs sur les noeuds fils
		 */
		protected Node[] childNodes;

		/**
		 * Feuille dans le B+-Tree
		 */
		protected boolean isLeafNode;

		/**
		 * Pointeur vers le noeud suivant
		 */
		protected Node nextNode;

		/* ============ */
		/* CONSTRUCTEUR */
		/* ============ */

		/**
		 * Constructeur de la classe Node
		 */
		public Node() {
			this.numberKeys = 0;
			this.keys = new int[BPlusTree.this.degree - 1];
			this.values = new Object[BPlusTree.this.degree - 1];
			this.childNodes = new Node[BPlusTree.this.degree];
			this.isLeafNode = false;
			this.nextNode = null;
		}

		/* ======== */
		/* METHODES */
		/* ======== */

		/**
		 * Vérifie si le noeud est plein, c'est à dire qu'il contient déjà le
		 * maximum de clés
		 * 
		 * @return true si le noeud est plein, false sinon
		 */
		public boolean isFull() {
			return this.numberKeys == this.keys.length;
		}

		/**
		 * Affiche le contenu du noeud
		 */
		public void display() {
			String s = "|";

			for (int i = 0; i < this.keys.length; i++) {
				String tmp;

				if (i < this.numberKeys) {
					tmp = " " + this.keys[i] + " |";
				} else {
					tmp = "  |";
				}

				s += tmp;
			}

			char[] lineArray = new char[s.length()];
			Arrays.fill(lineArray, '-');
			String lineString = new String(lineArray);

			System.out.println(lineString);
			System.out.println(s);
			System.out.println(lineString);
		}

		public void display2(String indent) {
			String s = indent + "|";

			for (int i = 0; i < this.keys.length; i++) {
				String tmp;

				if (i < this.numberKeys) {
					tmp = " " + this.keys[i] + " |";
				} else {
					tmp = "  |";
				}

				s += tmp;
			}

			char[] lineArray = new char[s.length() - indent.length()];
			Arrays.fill(lineArray, '-');
			String lineString = new String(lineArray);

			System.out.println(indent + lineString);
			System.out.println(s);
			System.out.println(indent + lineString);
		}

	}

}
