package btree;

import java.util.Arrays;

public class BPlusTree {

	public void search(int k, Node node) {
		if (node.isLeaf()) {
			
			//
			// En fait, on peut pas se contenter de juste vérifier que la valeur est présente, car on doit récupérer
			// l'indice i de cette valeur dans le noeud
			//
			if (Arrays.asList(node.getValues()).contains(k)) {
				/* Le noeud est une feuille et la valeur cherchée appartient aux valeurs de ce noeud */
				
				//
				// TODO : Le diapo dit de retourner Pi, c'est à dire le pointeur qui se trouve juste
				// avant la valeur qu'on vient de trouver, mais ça me parait bizarre.
				// En attendant, je me contente de retourner le noeud
				//
				//return node;
				//
			} else {
				/* Le noeud est une feuille mais la valeur cherchée n'appartient pas aux valeurs de ce noeud */
				
			}
			
		} else {

			//
			// En fait, on peut pas se contenter de juste vérifier que la valeur est présente, car on doit récupérer
			// l'indice i de cette valeur dans le noeud
			//
			if (Arrays.asList(node.getValues()).contains(k)) {
				/* Le noeud est un noeud interne et la valeur cherchée appartient aux valeurs de ce noeud */
				
			} else {
				/* Le noeud est un noeud interne mais la valeur cherchée n'appartient pas aux valeurs de ce noeud */
				
			}
			
		}
	}
	
}
