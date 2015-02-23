package btree;

import java.util.Arrays;

public class BPlusTree {

	public void search(int k, Node node) {
		if (node.isLeaf()) {
			
			if (Arrays.asList(node.getValues()).contains(k)) {
				/* Le noeud est une feuille et la valeur cherchée appartient aux valeurs de ce noeud */
				
			} else {
				/* Le noeud est une feuille mais la valeur cherchée n'appartient pas aux valeurs de ce noeud */
				
			}
			
		} else {
			
			if (Arrays.asList(node.getValues()).contains(k)) {
				/* Le noeud est un noeud interne et la valeur cherchée appartient aux valeurs de ce noeud */
				
			} else {
				/* Le noeud est un noeud interne mais la valeur cherchée n'appartient pas aux valeurs de ce noeud */
				
			}
			
		}
	}
	
}
