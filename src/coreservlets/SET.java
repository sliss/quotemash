/*************************************************************************
 *  Compilation:  javac SET.java
 *  Execution:    java SET
 *  
 *  Symbol table implementation using Java's HashMap library.
 *  Does not allow duplicates.
 *
 *  % java sET
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 *************************************************************************/
package coreservlets;
import java.util.TreeSet;
import java.util.Iterator;

public class SET<Key> implements Iterable<Key> {
    private TreeSet<Key> st = new TreeSet<Key>();

    public void add(Key key)          { st.add(key);               }
    public boolean contains(Key key)  { return st.contains(key);   }
    public void remove(Key key)       { st.remove(key);            }
    public int size()                 { return st.size();          }
    public Iterator<Key> iterator()   { return st.iterator();      }

    public String toString() {
        String s = "";
        for (Key key : this)
            s += key + " ";
        return s;
    }

   /***********************************************************************
    * Test routine.
    **********************************************************************/
    public static void main(String[] args) {
        SET<String> set = new SET<String>();

        // insert some keys
        set.add("www.cs.princeton.edu");
        set.add("www.princeton.edu");
        set.add("www.yale.edu");
        set.add("www.amazon.com");
        set.add("www.simpsons.com");

        // does given key exist?
        System.out.println(set.contains("www.cs.princeton.edu"));
        System.out.println(set.contains("www.amazon.com"));
        System.out.println(set.contains("www.amazon.edu"));
        System.out.println();

        // print the set using toString()
        System.out.println(set);

        // print out all keys in the set using foreach
        for (String s : set)
            System.out.println(s);

    }

}
