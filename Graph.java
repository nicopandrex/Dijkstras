// jdh CS 3240A / CS 5240A Fall 2026

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

public class Graph {
  List<GraphNode> nodes;
  boolean isDirectedGraph;

  public Graph() {
    this.nodes = new ArrayList<GraphNode>();
    this.isDirectedGraph = false;
  }

  public Graph(boolean isDirected) {
    this.nodes = new ArrayList<GraphNode>();
    this.isDirectedGraph = isDirected;
  }

  public void addNode(GraphNode newNode) {
    for (GraphNode n: this.nodes) {
      if (n == newNode) {
        System.out.println("ERROR: graph already has a node " + n.name);
        assert false;
      }
    }
    nodes.add(newNode);
  }

  public void addEdge(GraphNode n1, GraphNode n2, int weight) {
    // make sure edge does not already exist
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 >= 0) {
      for (Link link: this.nodes.get(idx1).adjlist) {
        if (link.n2 == n2) {
          System.out.println("ERROR: there is already an edge from " + n1.name + " to " + n2.name);
          return;
        }
      }
      this.nodes.get(idx1).addEdge(n2, weight);
    } else {
      System.out.println("ERROR: node " + n1.name + " not found in graph");
    }

    if (!this.isDirectedGraph) {
      int idx2 = this.nodes.indexOf(n2);
      if (idx2 >= 0) {
        this.nodes.get(idx2).addEdge(n1, weight);
      } else {
        System.out.println("ERROR: node " + n2.name + " not found in graph");
      }
    }
  } // addEdge()

  public void print() {
    for (GraphNode n1: this.nodes) {
      System.out.print(n1 + ":");
      for (Link link: n1.adjlist) {
        System.out.print(" " + link.n2.name + " (d=" + link.weight + ")");
      }
      System.out.print("|");
    }
    System.out.println();
  } // print()

  //----------------------------------------------------

  public static void updatePQnode(PriorityQueue<GraphNode> pqueue, GraphNode n) {
    pqueue.remove(n);
    pqueue.add(n);
  }

  //----------------------------------------------------

  public Map<GraphNode, Integer> shortestPath(GraphNode s) {
    // implement this
    List<GraphNode> S = new ArrayList<>();
    //setting the distances
    for(GraphNode node : this.nodes ){
      if(node == s){
        node.distance = 0;
      }
      else{
        node.distance = Integer.MAX_VALUE;
      }
    }

    S.add(s);

    while(S.size() < this.nodes.size()){
      int canidate_dist = Integer.MAX_VALUE;
      GraphNode canidate_next = null;
      for(GraphNode u: S){
        for(Link e: u.adjlist){
          if(!S.contains(e.n2)){
            int dist = u.distance + e.weight;
            GraphNode v = e.n2;
            if (dist < canidate_dist){
              canidate_dist = dist;
              canidate_next = v;

            }
          }
        }
      }
      if(canidate_next == null){
        break;
      }
      else{
        canidate_next.distance = canidate_dist;
        S.add(canidate_next);
      }

    }

    Map<GraphNode, Integer> map = new HashMap<>();
    for(GraphNode node: S){
      map.put(node, node.distance);

    }

    return map;



    
  } // shortestPath()

  //----------------------------------------------------

  public Map<GraphNode, Integer> shortestPathPQ(GraphNode s) {
    // implement this
    for(GraphNode node : this.nodes){
      if(node == s){
        node.distance = 0;
      }
      else{
        node.distance = Integer.MAX_VALUE;
      }
    }

    PriorityQueue<GraphNode> pq = new PriorityQueue<>();

    for(GraphNode node : this.nodes){
      pq.add(node);
    }

    List<GraphNode> S = new ArrayList<GraphNode>();

    while(!pq.isEmpty()){

      GraphNode v = pq.poll();

      if (v.distance == Integer.MAX_VALUE){
        break;
      }

      else{
        for(Link e: v.adjlist){
          if (v.distance + e.weight < e.n2.distance){
            e.n2.distance = v.distance + e.weight;

            updatePQnode(pq,e.n2);
          }
        }
      }


      S.add(v);

    }

    Map<GraphNode, Integer> map = new HashMap<>();
      for(GraphNode node : S){
        map.put(node, node.distance);
      }

    return map;



  } // shortestPathPQ()

} // class Graph
