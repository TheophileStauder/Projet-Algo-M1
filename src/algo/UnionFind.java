package algo;

import java.util.ArrayList;

public class UnionFind {

    /**
     * Structure Union Find
     * Servira pour la détection de cycle dans l'algorithme de Kruskal
     *
     */
    private Integer parentNode;
    private ArrayList<UnionFind> nodes;

    public UnionFind(Integer parent) {
        setParentNode(parent);
    }

    public void initDisjointSets(int totalNodes) {
        nodes = new ArrayList<>(totalNodes);
        for (int i = 0; i < totalNodes; i++) {
            nodes.add(new UnionFind(i));
        }
    }

    public void union(Integer rootU, Integer rootV) {
        UnionFind setInfoU = nodes.get(rootU);
        setInfoU.setParentNode(rootV);
    }

    public Integer find(Integer node) {
        Integer parent = nodes.get(node).getParentNode();
        if (parent.equals(node)) {
            return node;
        } else {
            return find(parent);
        }
    }

    public Integer pathCompressionFind(Integer node) {
        UnionFind setInfo = nodes.get(node);
        Integer parent = setInfo.getParentNode();
        if (parent.equals(node)) {
            return node;
        } else {
            Integer parentNode = find(parent);
            setInfo.setParentNode(parentNode);
            return parentNode;
        }
    }

    boolean detectCycle(Integer u, Integer v) {
        Integer rootU = pathCompressionFind(u);
        Integer rootV = pathCompressionFind(v);
        if (rootU.equals(rootV)) {
            return true;
        }
        union(rootU, rootV);
        return false;
    }

    public void setParentNode(Integer p){
        this.parentNode = p;
    }

    public Integer getParentNode(){
        return this.parentNode;
    }

}
