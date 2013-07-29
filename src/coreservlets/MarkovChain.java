/**********************************************************************
 *  Compilation:  javac MarkovChain.java
 *  Dependencies: ST.java SET.java
 *  
 *  Directed graph data type implemented using a symbol table
 *  whose keys are states (String) and whose values are sets
 *  of neighbors (SET of String).
 *
 *  Remarks
 *  -------
 *   - Parallel edges are allowed
 *   - Self-loop are allowed
 *   - Adjacency lists store many different copies of the same
 *     String. You can use less memory by interning the vertices.
 *
 **********************************************************************/
package coreservlets;

public class MarkovChain {

    // symbol table: key = String, value = set of neighboring vertices
    private ST<String, RandomQueue<String>> st =
        new ST<String, RandomQueue<String>>();

    // add w to v's set of neighbors, and add v to w's set of neighbors
    public void addTransition(String v, String w) {
        if (!st.contains(v)) {
            addState(v);
        }
        if (!st.contains(w)) {
            addState(w);
        }
        st.get(v).add(w);
        //st.get(w).add(v); <-- makes undirected edge (goes both ways)
    }

    // add a new String v with no neighbors (if String does not yet exist)
    public void addState(String v) {
        if (!st.contains(v)) {
            st.put(v, new RandomQueue<String>());
        }
    }

    // pick random transition from v, return the resulting state
    public String next(String v) {
        return st.get(v).sample(); // gets v, samples its transitions
    }
	
	// see if ST contains a given key, return boolean result
	public boolean contains(String key){
		return st.contains(key);
	}

    // not very efficient, intended for debugging only
    public String toString() {
        String s = "";
        for (String v : st) {
            s += v + ": " + st.get(v) + "\n";
        }
        return s;
    }


}
