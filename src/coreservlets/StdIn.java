/*************************************************************************
 *  Compilation:  javac StdIn.java
 *  Execution:    java StdIn
 *
 *  Reads in data of various types from standard input.
 *
 *************************************************************************/
package coreservlets;
import java.util.Scanner;

public final class StdIn {

    // assume input uses 8-bit Latin-1 alphabet
    // http://en.wikipedia.org/wiki/ISO_8859-1
    private static Scanner scanner = new Scanner(System.in, "ISO-8859-1");

    // can't instantiate
    private StdIn() { }

    // return true if only whitespace left
    public static boolean isEmpty()        { return !scanner.hasNext();      }

    // next string, int, double, float, long, byte, boolean
    public static String  readString()     { return scanner.next();          }
    public static int     readInt()        { return scanner.nextInt();       }
    public static double  readDouble()     { return scanner.nextDouble();    }
    public static float   readFloat()      { return scanner.nextFloat();     }
    public static long    readLong()       { return scanner.nextLong();      }
    public static byte    readByte()       { return scanner.nextByte();      }
    public static boolean readBoolean()    { return scanner.nextBoolean();   }

    // read until end of line
    public static String readLine()        { return scanner.nextLine();      }

    // read next char
    // a complete hack and inefficient - email me if you have a better
    public static char readChar() {
        // (?s) for DOTALL mode so . matches a line termination character
        // 1 says look only one character ahead
        // consider precompiling the pattern
        String s = scanner.findWithinHorizon("(?s).", 1);
        return s.charAt(0);
    }


    // return rest of input as string
    public static String readAll() {
        if (!scanner.hasNextLine()) return null;

        // reference: http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return scanner.useDelimiter("\\A").next();
    }




    // This method is just here to test the class
    public static void main(String[] args) {

        System.out.println("Type a string: ");
        String s = StdIn.readString();
        System.out.println("Your string was: " + s);
        System.out.println();

        System.out.println("Type an int: ");
        int a = StdIn.readInt();
        System.out.println("Your int was: " + a);
        System.out.println();

        System.out.println("Type a boolean: ");
        boolean b = StdIn.readBoolean();
        System.out.println("Your boolean was: " + b);
        System.out.println();
    }

}
