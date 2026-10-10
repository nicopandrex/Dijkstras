// jdh CS3240A/CS5240A Fall 2026

import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class GraphNode implements Comparable {
  List<Link> adjlist;
  String name;
  int distance;

  public GraphNode(String name) {
    this.name = name;
    this.adjlist = new ArrayList<Link>();
  }

  public void addEdge(GraphNode otherNode, int weight) {
    // make sure that this edge doesn't already exist
    for (Link link: this.adjlist) {
      if (link.n2 == otherNode) {
        System.out.println("ERROR: there is already an edge from " + this.name + " to " + otherNode.name);
        return;
      }
    }

    // add edge from this to edge.tail
    this.adjlist.add(new Link(otherNode, weight));
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;

    if ( ! (o instanceof GraphNode) )
      return false;

     GraphNode otherNode = (GraphNode) o;
     if (otherNode.name == this.name)
       return true;

    return false;
  }

  @Override
  public int compareTo(Object o) {
    GraphNode otherNode = (GraphNode) o;
    if (this.distance < otherNode.distance)
      return -1;
    else if (this.distance > otherNode.distance)
      return 1;
    else
      return 0;
  }

  @Override
  public String toString() {
    String s = "N " + this.name;
    return s;
  }
} // class GraphNode
