// Steven Liss
// Precept P03

// compilation: javac TextGenerator.java
// run: java TextGenerator < file.txt # #

// TextGenerator client for Markov chain program
package coreservlets;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder.Output;
//import javax.swing.JOptionPane;

public class TextGenerator {
	private String[] quotes; // array to hold output quotes
	
	public String[] generate(String s1, String s2, int numQuotes) {	
		quotes = new String[numQuotes];
		String hardInput1 = s1;//new String(JOptionPane.showInputDialog("1st Text:"));
		
		String hardInput2 = s2;//new String(JOptionPane.showInputDialog("2nd Text:"));
		
		MarkovChain mC1 = new MarkovChain();
		MarkovChain mC2 = new MarkovChain();
		
        int k = 6;//Integer.parseInt(args[0]); // order of Markov chain
		int c = 2;//Integer.parseInt(args[1]); // phase constant c determines probability of phase change given parallel nodes, with probability 1/c
		int m = 200;//Integer.parseInt(args[2]); // how many chars of new text to create
        
        
		
		int length = hardInput1.length();
        hardInput1 += hardInput1.substring(0, 2 * k);  // make string cyclical
		
        for (int i = 0; i < length - k; i++) { // create the 1st chain
            mC1.addTransition(hardInput1.substring(i, i + k),
							  hardInput1.substring(i + k, i + 2 * k));
        }
		
		length = hardInput2.length(); // now for the 2nd chain
        hardInput2 += hardInput2.substring(0, 2 * k);  // make string cyclical
		
        for (int i = 0; i < length - k; i++) { 
            mC2.addTransition(hardInput2.substring(i, i + k),
							  hardInput2.substring(i + k, i + 2 * k));
        }
		
		// build the output.  this is where the code is modified from the original single Markov chain version
        for(int i=0; i<numQuotes;i++) {
        length = hardInput1.length();
        int startIndex = (int)(Math.random() * length - 1); // random starting place
        
        while(!hardInput1.substring(startIndex,startIndex + 1).matches("[A-Z]")) {// while tail doesn't start with a capital letter
        	startIndex = (int)(Math.random() * length) - 1; // try another starting spot
        }
        
        String tail = hardInput1.substring(startIndex, startIndex + k); // the tail character-chunk of the output string
		String output = new String(tail);
		
		int activeMC = 0 ; // traversal begins on the first chain by default  // numerical attempt at switching active chains
		
		while (output.length() < m) {
			//System.out.print(tail);
			
			// super ugly redundant workaround because I forget how pointers work:
			if (activeMC == 0) { // if chain 1 is active
				if (mC2.contains(tail)) { // if the present, latest node is common to both chains...
					if (((int)(Math.random() * c)) % c == 0); // stochastically determine whether to swap chains based on phase constant
					{System.out.println("Switch!");
						activeMC = (activeMC + 1) % 2; // swap active and dormant chains by toggling between 1 and 2 (can go up to N for any combination of N chains)
					} 
				} // else, if the dormant chain lacks an analogue to the present node, continue on with the present active chain
				
				if (activeMC == 0) {
					tail = mC1.next(tail);
				}
				else {
					tail = mC2.next(tail);
				}
				output += tail;
			}
			else { // chain 2 is active
				if (mC1.contains(tail)) { // if the present, latest node is common to both chains...
					if((int) (Math.random() * 8 * k % ( k)) == 0) // probability of triggering chain-phase transition possibility
						if (((int)(Math.random() * c)) % c == 0); // stochastically determine whether to swap chains based on phase constant
					{System.out.println("Switch!");
						activeMC = (activeMC + 1) % 2; // swap active and dormant chains by toggling between 1 and 2 (can go up to N for any combination of N chains)
					} 
				} // else, if the dormant chain lacks an analogue to the present node, continue on with the present active chain
				
				if (activeMC == 0) {
					tail = mC1.next(tail);
				}
				else {
					tail = mC2.next(tail);
				}
				output += tail;	 
			}
			
		} 
		
		/*while (output.length() < m) { // simple output construction
		 tail = mC1.next(tail);
		 output += tail;
		 }*/
	    
		//output complete.  now for some string processing to make it nice for viewing	
		output = output.substring(0,output.lastIndexOf(".")+1); // cut off everything after the last period
		
        //System.out.println(output);
		quotes[i] = output;
        }
        return quotes;
    }
}


