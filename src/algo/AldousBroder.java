package algo;

import com.sun.security.jgss.GSSUtil;
import graphe.Edge;
import graphe.Graph;

import java.util.ArrayList;
import java.util.Random;

public class AldousBroder {
    private Graph graph;

    /**
     * Algorithme de AldousBroder
     *
     * On va effectuer une marche aléatoire sur le graphe en partant d'un
     * sommet quelconque , à chaque sommet non visité on garde l'arête utilisée pour arriver à ce sommet
     * On ajouter cette arête à l'abre couvrant
     */
    public AldousBroder(Graph g){
        this.graph = g;
    }

    public Graph calcul(){
        Graph res = this.graph;
        ArrayList<Integer> sommetsNonvisites = new ArrayList<>();
        for(int i = 0; i < graph.vertices() ; i++){sommetsNonvisites.add(i);}
        Random random = new Random();
        int sommetDepart = sommetsNonvisites.get(random.nextInt(sommetsNonvisites.size()));
        sommetsNonvisites.remove(sommetDepart);
        int sommetActuel = sommetDepart;
        while(sommetsNonvisites.size() != 0){
            ArrayList<Edge> aretes = res.adj(sommetActuel);
            ArrayList<Integer> voisins = new ArrayList<>();
            //On recupère tous les sommets voisins
            for(Edge v : aretes){
                voisins.add(v.other(sommetActuel));
            }

            //On choisit le prochain sommet sur lequel on se déplace
            int prochainSommet = voisins.get(random.nextInt(voisins.size()));
            //Si le sommet n'est pas visité on le marque visité et on marque l'arête utilisée
            if(sommetsNonvisites.contains(prochainSommet)){
                res.getSpecificEdge(sommetActuel,prochainSommet).setUsed(true);
                sommetsNonvisites.remove(new Integer(prochainSommet));
            }
            sommetActuel = prochainSommet;
            System.out.println(sommetsNonvisites.size());
        }

        return res;
    }
}
