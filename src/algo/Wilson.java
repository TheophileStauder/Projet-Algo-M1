package algo;

import graphe.Edge;
import graphe.Graph;

import java.util.*;

public class Wilson {

    private Graph graph;

    public Wilson(Graph g){this.graph = g;}

    public Graph calcul() {
        Graph res = graph;
        ArrayList<Integer> sommetVisites = new ArrayList<>();
        Random random = new Random();

        //On suppose un sommet visite ( un prend au hasard et l'extremite d'une arete

        int sommetBut = res.edges().get(random.nextInt(res.edges().size())).getFrom();
        int sommetActuel = res.edges().get(random.nextInt(res.edges().size())).getFrom();
        sommetVisites.add(sommetBut);
        boolean end = true;
        boolean start = true;
        ArrayList<Integer> chemin = new ArrayList<>();
        chemin.add(sommetActuel);


        while(end){

            while (sommetActuel != sommetBut){
                ArrayList<Edge> aretes = res.adj(sommetActuel);
                ArrayList<Integer> voisins = new ArrayList<>();

                //On recupère tous les sommets voisins
                for (Edge v : aretes) {
                    voisins.add(v.other(sommetActuel));
                }

                //On choisit le prochain sommet sur lequel on se déplace
                int prochainSommet = voisins.get(random.nextInt(voisins.size()));
                //Si le sommet n'est pas visité on le marque visité et on marque l'arête utilisée
                if (!sommetVisites.contains(prochainSommet)) {
                    res.getSpecificEdge(sommetActuel, prochainSommet).setUsed(true);
                    sommetVisites.add(prochainSommet);
                }

                //DEBUG
                /*System.out.println("Sommet actuel " + sommetActuel);
                System.out.println("Prochain sommet " + prochainSommet);
                System.out.println("SOMMET BUT " + sommetBut);*/
                sommetActuel = prochainSommet;
            }
            //Si il existe des cycles on les supprime
            /*System.out.println("cheminStart");
            for (Integer i :chemin){
                System.out.println(i);
            }*/
            //System.out.println("cheminEnd");
            if(hasDuplicate(chemin)){

                int index = getIndexFirstCycle(chemin);
                boolean cycle = true;
                int value = chemin.get(index);
                for(int i = index ; i < chemin.size() ; i++){
                    if(chemin.get(index) == value){
                        cycle = !cycle;
                    }
                    if(cycle){break;}
                    //On supprime l'arete du chemin et on spprime le sommet du chemin
                    res.getSpecificEdge(index, index + 1).setUsed(false);
                }
            }
            //Si tous les sommets sont visités on a termine
            if(sommetVisites.size() == res.vertices()){
                end = false;
            }
            else{
                //on continu l'algo -> on reprend un sommet non visite aléatoire et un sommet but
                boolean notReady = true;
                while(notReady){
                    sommetActuel = random.nextInt(res.vertices());
                    if(!sommetVisites.contains(sommetActuel)){notReady = false;}
                }
                boolean notReady2 = true;
                while(notReady2){
                    sommetBut = sommetVisites.get(random.nextInt(sommetVisites.size()));
                    if(!sommetVisites.contains(sommetActuel)){notReady2 = false;}
                }
                chemin.clear();
                chemin.add(sommetActuel);
            }
            System.out.println("NOMBRE SOMMET VISITES " + sommetVisites.size());
        }

        return res;
    }

    public int getIndexFirstCycle(ArrayList<Integer> chemin){
        int[] maxIndex = new int[chemin.size()];
        for(Integer inte : chemin){
            maxIndex[inte] ++;
        }
        int largest = 0;
        for ( int i = 1; i < maxIndex.length; i++ )
        {
            if ( maxIndex[i] > maxIndex[largest] ) largest = i;
        }
        return largest;
    }

    /**Fonction pour checker si il existe des elements dupliques
     * On utilisera cette fonction savoir si il existe
     * un cycle dans le chemin de notre sommet départ au sommet visité but
     */
    public boolean hasDuplicate(ArrayList<Integer> listToCheck){
        List<Integer> list = listToCheck;
        Set<Integer> set = new HashSet<Integer>(list);
        return set.size() < list.size();

    }

}
