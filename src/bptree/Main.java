package bptree;

public class Main {

	public static void main(String[] args) {
		
		/* Création d'un B+-Tree d'indice 4 */
		BPlusTree bptree = new BPlusTree(4);
		
		System.out.println("======== B+ Tree ========");
		System.out.println("");
		System.out.println("B+-Tree initial, d'indice 4 :");
		System.out.println("");
		
		bptree.display2();
		
		System.out.println("");
		System.out.println("Remplissage du B+-Tree...");

		/* Ajouts de clés/valeurs à ce B+Tree */
		bptree.add( 7, "Dwalin");
		bptree.add( 9, "Balin");
		bptree.add( 4, "Kili");
		bptree.add( 1, "Fili");
		bptree.add(14, "Dori");
		bptree.add(21, "Nori");
		bptree.add(35, "Ori");
		bptree.add( 2, "Oin");
		bptree.add( 8, "Gloin");
		bptree.add(10, "Bifur");
		bptree.add(12, "Bofur");
		bptree.add(17, "Bombur");
		bptree.add( 5, "Thorin");
	
		/* Affichage du B+-Tree après remplissage */
		System.out.println("");
		System.out.println("B+-Tree après remplissage :");
		System.out.println("");

		bptree.display2();
		
		/* Recherche dans le B+-Tree */
		
		System.out.println("");
		System.out.println("Recherches dans le B+-Tree :");
		System.out.println("");
		
		System.out.println("Clé = 14:\tValeur = " + bptree.search(14));
		System.out.println("Clé = 10:\tValeur = " + bptree.search(10));
		System.out.println("Clé = 8:\tValeur = " + bptree.search(8));

	}

}
