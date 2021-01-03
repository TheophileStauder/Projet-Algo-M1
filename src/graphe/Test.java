package graphe;

import algo.AldousBroder;
import algo.Kruskal;
import algo.Wilson;

import java.io.*;


import java.util.*;
public class Test {


	public static void printLaby(Graph G, int size, String file) {
		{
	/* suppose que G est une grille de taille size x size et 
           crée un .tex qui contient le labyrinthe correspondant */

			try {
				PrintWriter writer = new PrintWriter(file, "UTF-8");
				writer.println("\\documentclass{article}\\usepackage{tikz}\\begin{document}");
				writer.println("\\begin{tikzpicture}");

				for (int i = 0; i < size; i++)
					for (int j = 0; j < size; j++) {
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, j));
						writer.println("\\draw (0.1,0.1) -- (0.4,0.1);");
						writer.println("\\draw (0.6,0.1) -- (0.9,0.1);");
						writer.println("\\draw (0.1,0.9) -- (0.4,0.9);");
						writer.println("\\draw (0.6,0.9) -- (0.9,0.9);");
						writer.println("\\draw (0.1,0.1) -- (0.1, 0.4);");
						writer.println("\\draw (0.1,0.6) -- (0.1, 0.9);");
						writer.println("\\draw (0.9,0.1) -- (0.9,0.4);");
						writer.println("\\draw (0.9,0.6) -- (0.9,0.9);");
						writer.println("\\end{scope}");
					}

				/* bord */
				for (int i = 0; i < size; i++) {
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, 0));
					writer.println("\\draw(0.4,0.1) -- (0.6, 0.1);");
					writer.println("\\end{scope}");
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, size - 1));
					writer.println("\\draw(0.4,0.9) -- (0.6, 0.9);");
					writer.println("\\end{scope}");
					if (i > 0) {
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", 0, i));
						writer.println("\\draw(0.1,0.4) -- (0.1, 0.6);");
						writer.println("\\end{scope}");

					}
					if (i < size - 1) {
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", size - 1, i));
						writer.println("\\draw(0.9,0.4) -- (0.9, 0.6);");
						writer.println("\\end{scope}");

					}
					writer.println("\\draw (0,0.4) -- (0.1, 0.4);");
					writer.println("\\draw (0,0.6) -- (0.1, 0.6);");
					writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.4)  -- ++ (-0.1, 0); ", size, size - 1));
					writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.6)  -- ++ (-0.1, 0); ", size, size - 1));

				}


				for (Edge e : G.edges()) {
					int i = e.from % size;
					int j = e.from / size;
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, j));
					if (e.to == e.from + size) {
						/* arête verticale */
						if (!e.used) {
							writer.println("\\draw (0.4,0.9) -- (0.6,0.9);");
							writer.println("\\draw (0.4,1.1) -- (0.6,1.1);");
						} else {
							writer.println("\\draw (0.4,0.9) -- (0.4,1.1);");
							writer.println("\\draw (0.6,0.9) -- (0.6,1.1);");
						}
					} else {
						/* arête horizontale */

						if (!e.used) {
							writer.println("\\draw (0.9,0.4) -- (0.9,0.6);");
							writer.println("\\draw (1.1,0.4) -- (1.1,0.6);");
						} else {
							writer.println("\\draw (0.9,0.4) -- (1.1,0.4);");
							writer.println("\\draw (0.9,0.6) -- (1.1,0.6);");
						}
					}
					writer.println("\\end{scope}");
				}
				writer.println("\\end{tikzpicture}");
				writer.println("\\end{document}");
				writer.close();
			} catch (IOException e) {
			}
		}


	}


	public static void main(String[] args) {

		/* QUESTION 2 */
		//Declaration d'une arrayList pour stocker tous les arbres couvrants*/
		ArrayList<Graph> kruskalSpanningTrees = new ArrayList<>(1000000);

		Graph example;
		Kruskal kruskalSolver;
		for (int i = 0; i < 1000000; i++) {
			example = Graph.example();
			kruskalSolver = new Kruskal(example);
			Graph res = kruskalSolver.calcul();
			kruskalSpanningTrees.add(res);
			/*for (int j = 0; j < 8 ; j++){
				if(comptage[j].size() != 0 ){				//*****************************************************
					if(comptage[j].get(0).equals(res)){   	//does not work sadly ... equals method doesn't work here
						comptage[j].add(res);    			// even if she is implemented in the Graph class
						System.out.println("ici");			// if this works , avoid to look at the edges to classify spanning trees
					}										//*****************************************************
				}else{
					comptage[j].add(res);
				}
			}*/
		}

		/*RESULAT QUESTION 2 */
		System.out.println("Kruskal results : ");
		classifySpanningTreesForExampleGraph(kruskalSpanningTrees);
		//////////////////////////

		/* QUESTION 5 */
		AldousBroder aldousBroder;
		ArrayList<Graph> aldousBroderSpanningTrees = new ArrayList<>(1000000);
		for (int i = 0; i < 1000000; i++) {
			example = Graph.example();
			aldousBroder = new AldousBroder(example);
			Graph res = aldousBroder.calcul();
			aldousBroderSpanningTrees.add(res);
		}


		/*RESULTATS QUESTION 5*/
		System.out.println("\n\nAldousBroder results : ");
		classifySpanningTreesForExampleGraph(aldousBroderSpanningTrees);
		///////////////////////

		/* QUESTION 6 */
		Wilson wilson;
		ArrayList<Graph> wilsonSpanning = new ArrayList<>(1000000);

		example = Graph.example();
		wilson = new Wilson(example);
		Graph res = wilson.calcul();
		Display d = new Display();
		//d.setImage(res.toImage());

		/*QUESTION 7*/

		Labyrinthe labyrinthe;
		int impasseKruskal,impasseAldous,totalImpasseK, totalImpasseA;
		impasseKruskal = impasseAldous = totalImpasseK = totalImpasseA = 0;
		for(int i = 0 ; i < 2000 ; i++){
			labyrinthe = new Labyrinthe(Graph.Grid(20));
			Graph labyKruskal = labyrinthe.getLabyKruskal();
			labyrinthe = new Labyrinthe(Graph.Grid(20));
			Graph labyAldousBroder = labyrinthe.getLabyAldousBroder();
			int[] degreSommetKruskal = new int[labyKruskal.vertices()];
			int[] degreSommetAldous = new int[labyAldousBroder.vertices()];

			//On va compter le degre pour chaque sommet du labyrinthe
			for(Edge e : labyKruskal.edges()){
				degreSommetKruskal[e.getFrom()]++;
				degreSommetKruskal[e.getTo()]++;
			}

			for(Edge e : labyAldousBroder.edges()){
				degreSommetAldous[e.getFrom()]++;
				degreSommetAldous[e.getTo()]++;
			}

			//Une impasse est équivalent à un sommet de degre un, on va toutes les compter pour chaque labyrinthe
			for(int taille = 0; taille < labyKruskal.vertices() - 1 ; taille++){
				if(degreSommetKruskal[taille] == 1){impasseKruskal ++;System.out.println("COUCOU");}
				//System.out.println("SOMMET "+ taille + " nombre arete "+ degreSommetKruskal[taille]);
			}
			totalImpasseK = totalImpasseK + impasseKruskal;
			impasseKruskal = 0;
			//System.out.println("Impasse Kruskal : " + totalImpasseK);

			for(int taille = 0; taille < labyAldousBroder.vertices() - 1 ; taille++){
				if(degreSommetAldous[taille] == 1){impasseAldous ++;}
				//System.out.println("SOMMET "+ taille + " nombre arete "+ degreSommetKruskal[taille]);
			}
			totalImpasseA = totalImpasseA + impasseAldous;
			impasseAldous = 0;

		}
		System.out.println("Impasse Kruskal : " + totalImpasseK);
		System.out.println("Impasse Aldous : " + totalImpasseK);
		/*int size = 4;
		Graph G = Graph.Grid(size);
		Display d = new Display();
		//d.setImage(G.toImage());
		System.out.println("appuyez sur une touche");
		new Scanner(System.in).nextLine();
		d.close();
		printLaby(G, size, "toto.tex");*/

	}

	public static void classifySpanningTreesForExampleGraph(ArrayList<Graph> spanningTrees){
		int[] comptage = new int[8];
		/*To classify the 1 000 0000 spanning trees in 8 groups*/
		for (Graph spanningTree : spanningTrees) {

			ArrayList<Edge> redEdges = new ArrayList<>(3);
			for (Edge edge : spanningTree.edges()) {
				if (edge.used) {
					redEdges.add(edge);
				}
			}
			//horrible code , would be better if the ***** block works
			Edge zeroToOne = new Edge(0, 1);zeroToOne.used = true;
			Edge zeroToTwo = new Edge(0, 2);zeroToTwo.used = true;
			Edge zeroToThree = new Edge(0, 3);zeroToThree.used = true;
			Edge oneToTwo = new Edge(1, 2);oneToTwo.used = true;
			Edge oneToThree = new Edge(1, 3);oneToThree.used = true;
			if (redEdges.contains(zeroToTwo) && redEdges.contains(oneToTwo) && redEdges.contains(oneToThree)){comptage[0]++;}
			if (redEdges.contains(zeroToTwo) && redEdges.contains(zeroToThree) && redEdges.contains(oneToThree)){comptage[1]++;}
			if (redEdges.contains(zeroToTwo) && redEdges.contains(zeroToThree) && redEdges.contains(oneToTwo)){comptage[2]++;}
			if (redEdges.contains(zeroToThree) && redEdges.contains(oneToThree) && redEdges.contains(oneToTwo)){comptage[3]++;}
			if (redEdges.contains(zeroToTwo) && redEdges.contains(zeroToOne) && redEdges.contains(oneToThree)){comptage[4]++;}
			if (redEdges.contains(zeroToThree) && redEdges.contains(zeroToOne) && redEdges.contains(oneToTwo)){comptage[5]++;}
			if (redEdges.contains(zeroToTwo) && redEdges.contains(zeroToOne) && redEdges.contains(zeroToThree)){comptage[6]++;}
			if (redEdges.contains(oneToTwo) && redEdges.contains(zeroToOne) && redEdges.contains(oneToThree)){comptage[7]++; }
		}

		for (int j = 1; j < 9; j++) {
			System.out.println("Arbre couvrant " + j + " : " + comptage[j-1]);
		}
	}
}

