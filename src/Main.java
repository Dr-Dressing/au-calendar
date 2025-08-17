/*
 * @author Dr-Dressing
 * @version 1.0
 * @since 2025-08-15
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        // idiotproof.
        if (args.length != 1) {
            System.out.println("Usage: java Main <KEY>");
            System.out.println("Please provide a key to download the calendar.");
            return;
        }
        
        Downloader downloader = new Downloader(args[0]);
        downloader.initialize();
        
        Separator separator = new Separator();

        // I know using ArrayList is blasphemous, but right now, I don't care.
        HashSet<String> categories = new HashSet<>(separator.searchSeparate());
        ArrayList<Data> pointList = separator.collectPoints();
        separator.generateICS(categories, pointList);
    }
}