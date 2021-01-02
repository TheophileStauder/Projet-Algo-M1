package algo;

import graphe.Edge;
import graphe.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
    private Graph graph;

    /**
     * Algorithme de Kruskal
     *
     * On mélange les arrêtes puis on les
     * ajoute dans l'ordre une à une sans créer de cycle
     */
    public Kruskal(Graph g){
        this.graph = g;
    }

    public Graph calcul(){
        int nbVertices = graph.vertices();
        Graph res = graph;
        ArrayList<Edge> edges = graph.edges();

        /*Mélange aléatoire des arêtes*/
        Collections.shuffle(edges);

        UnionFind unionFind = new UnionFind(edges.get(0).getFrom());
        unionFind.initDisjointSets(nbVertices);


        for(Edge e : edges){
            /*Detection de cycle lorsqu'on tente d'jaouter l'arête e*/
            if(!unionFind.detectCycle(e.getFrom(),e.getTo())){
                e.setUsed(true);
            }
        }

        return res;
    }



}
