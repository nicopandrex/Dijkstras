// jdh CS3240A / CS5240A Fall 2026

import java.util.Map;

public class Main {
  public static void main(String args[]) {
    testOne();
    testTwo();
  }

  //--------------------------------------------------------
  // graph on p. 139
  public static void testOne() {
    GraphNode n1 = new GraphNode("s");
    GraphNode n2 = new GraphNode("u");
    GraphNode n3 = new GraphNode("v");
    GraphNode n4 = new GraphNode("y");
    GraphNode n5 = new GraphNode("x");
    GraphNode n6 = new GraphNode("z");

    Graph G = new Graph(true);
    G.addNode(n6);
    G.addNode(n5);
    G.addNode(n4);
    G.addNode(n3);
    G.addNode(n2);
    G.addNode(n1);

    // add the edges from highest # node to lowest # node
    G.addEdge(n5, n6, 2);
    G.addEdge(n5, n4, 1);
    G.addEdge(n3, n6, 3);
    G.addEdge(n3, n5, 2);
    G.addEdge(n2, n5, 1);
    G.addEdge(n2, n4, 3);
    G.addEdge(n1, n5, 4);
    G.addEdge(n1, n3, 2);
    G.addEdge(n1, n2, 1);

    System.out.println("----------------- shortestPath() --------------------");
    Map<GraphNode, Integer> distances = G.shortestPath(n1);
    for (GraphNode n: distances.keySet()) {
      System.out.println("distance from node " + n1 + " to " + n + " = " + distances.get(n));
    }

    System.out.println();
    System.out.println("---------------- shortestPathPQ() -------------------");
    distances = G.shortestPathPQ(n1);
    for (GraphNode n: distances.keySet()) {
      System.out.println("distance from node " + n1 + " to " + n + " = " + distances.get(n));
    }
  }

  //--------------------------------------------------------

  public static void testTwo() {
    GraphNode nA = new GraphNode("A");
    GraphNode nB = new GraphNode("B");
    GraphNode nC = new GraphNode("C");
    GraphNode nD = new GraphNode("D");
    GraphNode nE = new GraphNode("E");
    GraphNode nF = new GraphNode("F");
    GraphNode nG = new GraphNode("G");
    GraphNode nH = new GraphNode("H");
    GraphNode nI = new GraphNode("I");
    GraphNode nJ = new GraphNode("J");
    GraphNode nK = new GraphNode("K");

    Graph G = new Graph(true);
    G.addNode(nA);
    G.addNode(nB);
    G.addNode(nC);
    G.addNode(nD);
    G.addNode(nE);
    G.addNode(nF);
    G.addNode(nG);
    G.addNode(nH);
    G.addNode(nI);
    G.addNode(nJ);
    G.addNode(nK);

    G.addEdge(nA, nB, 22);
    G.addEdge(nA, nC, 23);
    G.addEdge(nA, nD, 52);
    G.addEdge(nA, nE, 64);
    G.addEdge(nB, nE, 41);
    G.addEdge(nE, nG, 36);
    G.addEdge(nE, nI, 130);
    G.addEdge(nC, nE, 39);
    G.addEdge(nC, nG, 64);
    G.addEdge(nC, nF, 42);
    G.addEdge(nG, nI, 74);
    G.addEdge(nD, nF, 37);
    G.addEdge(nD, nH, 126);
    G.addEdge(nF, nG, 21);
    G.addEdge(nF, nH, 43);
    G.addEdge(nF, nI, 132);
    G.addEdge(nH, nI, 52);
    G.addEdge(nJ, nK, 12);
    G.addEdge(nJ, nH, 22);

    System.out.println("----------------- shortestPath() --------------------");
    G.shortestPath(nA);
    for (GraphNode n: G.nodes) {
      System.out.println("distance from node " + nA + " to " + n + " = " + n.distance);
    }

    for (GraphNode n: G.nodes)
      n.distance = 0;

    System.out.println();
    System.out.println("---------------- shortestPathPQ() -------------------");
    G.shortestPathPQ(nA);
    for (GraphNode n: G.nodes) {
      System.out.println("distance from node " + nA + " to " + n + " = " + n.distance);
    }
  } // testTwo()
} // class Main
