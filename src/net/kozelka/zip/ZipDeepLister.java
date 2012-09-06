package net.kozelka.zip;

import java.io.*;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * @author Petr Kozelka
 * @todo option --xml - switch to xml renderer
 * @todo option --stats - show some stats (deepest level, counts per extension, counts of dirs)
 * @todo option --max-depth
 * @todo option --dont-explore [filemasks] - avoid showing some files
 */
public class ZipDeepLister {

    private final LinkedList<String> nesting = new LinkedList<String>();
    private ZdlRenderer renderer;

    private final File file;

    public ZipDeepLister(File file) {
        this.file = file;
    }

    public void setRenderer(ZdlRenderer renderer) {
        this.renderer = renderer;
    }

    private void doList() throws IOException {
        final FileInputStream is = new FileInputStream(file);
        try {
            listInputStream(is);
        } finally {
            is.close();
        }
    }

    private boolean listInputStream(InputStream is) throws IOException {
        final ZipInputStream zis = new ZipInputStream(is);
        ZipEntry zipEntry = zis.getNextEntry();
        if (zipEntry == null) return false;
        while (zipEntry != null) {
            printEntry(zis, zipEntry);
            zipEntry = zis.getNextEntry();
        }
        return true;
    }

    private void printEntry(ZipInputStream zis, ZipEntry zipEntry) throws IOException {
        if (zipEntry.isDirectory()) {
            renderer.printDirectory(nesting, zipEntry.getName());
        } else {
            renderer.printFile(nesting, zipEntry.getName());
            try {
                nesting.addLast(zipEntry.getName() + "!");
                if (listInputStream(zis)) {
                    //TODO yes it was an archive
//                    out.println(currentPathName + "/");
                }
            } catch (ZipException e) {
                // ignored - it's just not a zip file
                System.err.println("Not a zip file: " + nesting  + " entry:" + zipEntry.getName());
            } finally {
                nesting.removeLast();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("ERROR: expected exactly one argument (filename) but found " + args.length);
            System.exit(1);
        }

        final ZipDeepLister zls = new ZipDeepLister(new File(args[0]));
        zls.setRenderer(new DefaultZdlRenderer());
        zls.doList();
    }
}
