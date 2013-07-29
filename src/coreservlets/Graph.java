/*************************************************************************
 *  Compilation:  javac Graph.java
 *  Dependencies: ST.java SET.java
 *  
 *  Undirected graph data type implemented using a symbol table
 *  whose keys are vertices (Vertex) and whose values are sets
 *  of neighbors (SET of Vertex).
 *
 *  Remarks
 *  -------
 *   - Parallel edges are not allowed
 *   - Self-loop are allowed
 *   - Adjacency lists store many different copies of the same
 *     Vertex. You can use less memory by interning the vertices.
 *
 *************************************************************************/
package coreservlets;

public class Graph<Vertex> {

    // symbol table: key = vertex, value = set of neighboring vertices
    private ST<Vertex, SET<Vertex>> st = new ST<Vertex, SET<Vertex>>();

    // add w to v's set of neighbors, and add v to w's set of neighbors
    public void addEdge(Vertex v, Vertex w) {
        if (!st.contains(v)) addVertex(v);
        if (!st.contains(w)) addVertex(w);
        st.get(v).add(w);
        st.get(w).add(v);
    }

    // add a new vertex v with no neighbors (if vertex does not yet exist)
    public void addVertex(Vertex v) {
        if (!st.contains(v))
            st.put(v, new SET<Vertex>());
    }

    // return list of neighbors of v
    public Iterable<Vertex> adj(Vertex v) {
        if (!st.contains(v)) return new SET<Vertex>();
        return st.get(v);
    }

    // not very efficient, intended for debugging only
    public String toString() {
        String s = "";
        for (Vertex v : st)
            s += v + ": " + st.get(v) + "\n";
        return s;
    }

    public static void main(String[] args) {
        Graph<String> G = new Graph<String>();
        G.addEdge("A", "B");
        G.addEdge("A", "C");
        G.addEdge("C", "D");
        G.addEdge("D", "E");
        G.addEdge("D", "G");
        G.addEdge("E", "G");
        G.addVertex("H");
        System.out.println(G);
    }

}
