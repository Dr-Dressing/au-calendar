import java.io.*;
import java.util.*;

/**
 * Separator.java
 * @author Dr-Dressing
 * @version 1.0
 * @since 2025-08-16
 */
public class Separator {

    /**
     * This method finds as many categories as possible. 
     * It will be used to separate the calendar points into different files.
     * I might make a "combine" method later, but for now, this is fine.
     * @param categoryList stores all calendar point objects, regardless of timestamp.
     * @return a Set of categories found in the calendar.
     */
    public Set<String> searchSeparate() {
        Set<String> categories = new HashSet<>();

        try {
            File file = new File("calendar.ics");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if (data.contains("SUMMARY")){
                    categories.add(data);
                }
                // otherwise, ignore everything else. We exclusively need to know the categories for the set.
                // The rest of the points are added later.
            }
           reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            System.out.println("Found " + categories.size() + " different categories.\n\n");
            for (String string : categories) {
                System.out.println(string);
            }
            System.out.println("\n\n");
        }
        return categories;
    }


    public ArrayList<Data> collectPoints() {
        ArrayList<Data> dataList = new ArrayList<>();

        try {
            File file = new File("./calendar.ics");
            Scanner reader = new Scanner(file);

            String UID = null;
            String DTSTAMP = null;
            String DTSTART = null;
            String DTEND = null;
            String LOCATION = null;
            String SUMMARY = null;
            String DESCRIPTION = null;

            boolean filled = false;

             // This might look redundant, but I'd have to make mutator methods for Data, and overcomplicate searchSeparate.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                // Ternaries. Here, I want to store them in a Data object.
                UID         = (data.contains("UID"))           ? data : UID;
                DTSTAMP     = (data.contains("DTSTAMP"))       ? data : DTSTAMP;
                DTSTART     = (data.contains("DTSTART"))       ? data : DTSTART;
                DTEND       = (data.contains("DTEND"))         ? data : DTEND;
                if (data.contains("SUMMARY")){
                    SUMMARY = data;
                }
                LOCATION    = (data.contains("LOCATION"))      ? data : LOCATION;
                DESCRIPTION = (data.contains("DESCRIPTION"))   ? data : DESCRIPTION;

                // This is insanely slow. but I gotta finish this program at some point.
                // There must be a better way to do this.
                if (UID != null && DTSTAMP != null && DTSTART != null && DTEND != null && SUMMARY != null && LOCATION != null && DESCRIPTION != null) {
                    filled = true;
                }

                if (filled){
                    Data calendarData = new Data(UID, DTSTAMP, DTSTART, DTEND, SUMMARY, LOCATION, DESCRIPTION);
                    dataList.add(calendarData);

                    // Reset.
                    UID = null;
                    DTSTAMP = null;
                    DTSTART = null;
                    DTEND = null;
                    SUMMARY = null;
                    LOCATION = null;
                    DESCRIPTION = null;
                    filled = false;
                }
            }
           reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dataList;
    }


    public void generateICS(HashSet<String> categories, ArrayList<Data> dataList) {
        int calendarPoint = 1;

        for (String category : categories) {
            try {
                File newFile = new File("../ics/calendarCategory" + calendarPoint + ".ics");
                newFile.createNewFile();
                FileWriter writer = new FileWriter(newFile);
                writer.write("BEGIN:VCALENDAR\n");
                writer.write("VERSION:2.0\n");
                writer.write("PRODID:-//hacksw/handcal//NONSGML v1.0//EN\n");
                ArrayList<Data> temp = new ArrayList<>(dataList);

               for (Data data : temp) {
                    // System.out.println(data.getSUMMARY());
                    String point = data.getSUMMARY();
                    if (point.equals(category)) {
                    }
                    if (data.getSUMMARY().equals(category)) {
                       writer.write("BEGIN:VEVENT\n");
                       writer.write(data.getUID() + "\n");
                       writer.write(data.getDTSTAMP() + "\n");
                       writer.write(data.getDTSTART() + "\n");
                       writer.write(data.getDTEND() + "\n");
                       writer.write(data.getSUMMARY() + "\n");
                       writer.write(data.getDESCRIPTION() + "\n");
                       writer.write("END:VEVENT\n");
                   }
                    
               }
               writer.write("END:VCALENDAR");
               writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
                
            System.out.println("Created calendarCategory" + calendarPoint + ".ics for category " + category);
            calendarPoint++;
        }
    }
}
