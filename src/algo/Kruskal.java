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
        Collections.shuffle(edges);

        //int rand = (int) (Math.random() * (nbVertices + 1));
        //System.out.println("RANDOM : "+ rand);
        UnionFind unionFind = new UnionFind(edges.get(0).getFrom());
        unionFind.initDisjointSets(nbVertices);
        /*Mélange aléatoire des arêtes*/
        for(Edge e : edges){
            if(!unionFind.detectCycle(e.getFrom(),e.getTo())){
                e.setUsed(true);
            }
        }

        return res;
    }



}
