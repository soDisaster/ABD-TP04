ABD - TP 04
===========

Auteurs
-------

- Anne-Sophie Saint-Omer
- Thomas Bernard

Description
-----------

L'objectif de ce TP est d'implémenter des classes Java pour gérer les opérations de base sur un arbre B+-Tree.

- **OK :** Implémentation de l’algorithme de recherche d'une clé dans l'arbre.

Deux méthodes de recherche sont disponibles sur les instances de `BPlusTree` :  
La méthode `search(int key)` retourne la valeur associée à la clé passée en paramètre.  
La méthode`searchNode(int key)` retourne le noeud qui contient la clé passée en paramètre.  

- **OK :** Implémentation de l’algorithme d'insertion d'une clé dans l'arbre.

La méthode `add(int key, Object object)` permet d'insérer dans le B+-Tree une clé `key` associée à une valeur `object`.  

- Implémentation de l’algorithme de suppression d'une clé dans l'arbre.

La suppression d'une clé dans l'arbre n'a pas été implémentée.  

Structure
---------

L'ensemble de l'implémentation repose sur la classe `BPlusTree.java` qui contient les opérations de base sur l'arbre.  
Une classe interne `Node` est présente pour la gestion des noeuds (internes ou feuilles) du B+-Tree.  

Une classe `Main.java` est fournie pour tester l'implémentation.

L'arbre peut être affiché avec la méthode `display()`, qui se contente de lister les noeuds, ou `display2()` qui essaye de représenter la structure de l'arbre en indentant les noeuds de celui-ci.

Utilisation
-----------

```
$ java -jar b_plus_tree.jar
```
