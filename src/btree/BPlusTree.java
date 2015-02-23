package btree;

import java.util.Arrays;

public class BPlusTree {

	public Node search(int k, Node node) {

		/* Si k est trouvé dans les valeurs de node, i vaut son index dans le tableau */
		/* Si k n'est pas trouvé, i vaut -1 */
		int i = Arrays.asList(node.getValues()).indexOf(k);
		
		/* Si le noeud est une feuille du B+-Tree */
		if (node.isLeaf()) {
			
			if (i != -1) {
				/* Le noeud est une feuille et la valeur cherchée appartient aux valeurs de ce noeud */
				return node;
			} else {
				/* Le noeud est une feuille mais la valeur cherchée n'appartient pas aux valeurs de ce noeud */
				return null;
			}
			
		}
		
		/* Si au contraire le noeud est un noeud interne du B+-Tree */
		else {

			if (Arrays.asList(node.getValues()).contains(k)) {
				/* Le noeud est un noeud interne et la valeur cherchée appartient aux valeurs de ce noeud */
				return null;
			} else {
				/* Le noeud est un noeud interne mais la valeur cherchée n'appartient pas aux valeurs de ce noeud */
				return null;
			}
			
		}
	}
	
}
