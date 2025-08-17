import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.net.URL;
import java.io.FileOutputStream;
import java.net.URI;

/**
 * @author Dr-Dressing
 * @version 1.0
 * @since 2025-08-16
 * 
 */
public class Downloader {
    private String key;


    public Downloader(String key) {
        this.key = key.trim();
    }


    /**
     * Honestly, this is just to make the Main class cleaner.
     * This method does it all in chronological order. 
     * 
     * It downloads and handles exceptions for the download.
     * @throws Exception if download fails.
     */
    public void initialize() {
        try {
            this.download();
        } catch (Exception e) {
            System.err.println("An error occurred while downloading the calendar: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Downloads the calendar using the provided key.
     * @param key is the ical key to the calendar.
     * @throws Exception If an error occurs during the download.
     */
    public void download() throws Exception {
        String URL = "https://mitstudie.au.dk/calendar?icalkey=" + this.key;
        
        // For some reason, calling a consturctor to URL is deprecated.
        URL website = new URI(URL).toURL(); 

        try (ReadableByteChannel rbc = Channels.newChannel(website.openStream());) {

            FileOutputStream fos = new FileOutputStream("calendar.ics");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE); 
            fos.close();
        
        } catch (Exception e) {
            throw new Exception("Failed to download calendar: " + e.getMessage(), e);
        }

        return;
    }

}