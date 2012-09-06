package net.kozelka.zip;

import java.util.LinkedList;

/**
 * @author Petr Kozelka
 */
public interface ZdlRenderer {

    void printDirectory(LinkedList<String> nesting, String name);

    void printFile(LinkedList<String> nesting, String name);
}
