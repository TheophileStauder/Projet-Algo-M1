package graphe;

import algo.AldousBroder;
import algo.Kruskal;
import graphe.Graph;


/**
 * Classe labyrinthe , permet de générer un labyrinthe grâce
 * aux algos de recherche d'abre couvrant qui a implémenter dans le package algo
 */
public class Labyrinthe {

    private Graph graph;

    public Labyrinthe(int size) {
        this.graph = Graph.Grid(size);
    }

    public Graph getLabyKruskal() {
        Kruskal kruskal = new Kruskal(graph);
        return kruskal.calcul();
    }

    public Graph getLabyAldousBroder() {
        AldousBroder aldousBroder = new AldousBroder(graph);
        return aldousBroder.calcul();
    }
}
