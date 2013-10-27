I want this build to fail
package net.kozelka.zip;

import java.io.PrintStream;
import java.util.LinkedList;

/**
 * @author Petr Kozelka
 */
public class DefaultZdlRenderer implements ZdlRenderer {
    private static final PrintStream out = System.out;

    public void printDirectory(LinkedList<String> nesting, String name) {
        out.println(formatOutput(nesting, name));
    }

    public void printFile(LinkedList<String> nesting, String name) {
        out.println(formatOutput(nesting, name));
    }


    private static String formatOutput(LinkedList<String> nesting, String lastElement) {
        final StringBuilder sb = new StringBuilder();
        for (String container : nesting) {
            sb.append(container);
        }
        sb.append(lastElement);
        return sb.toString();
    }

}
