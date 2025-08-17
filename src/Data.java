/**
 * Data.java
 * @author Dr-Dressing
 * @version 1.0
 * @since 2025-08-16
 */

public class Data {
    private String UID;
    private String DTSTAMP;
    private String DTSTART;
    private String DTEND;
    private String SUMMARY;
    private String LOCATION;
    private String DESCRIPTION;

    // I didn't want to make this a separate class, but it turns out it might be
    // necessary. Over engineered, but that's alright.

    /**
     * Basic datastructure for the calendar points. These are all references
     * to the calendar ics format. I also don't need mutator methods.
     * @param UID is the unique identifier for the calendar point.
     * @param DTSTAMP is the timestamp of the calendar point.
     * @param DTSTART is the start time of the calendar point.
     * @param DTEND is the end time of the calendar point.
     * @param SUMMARY is the summary of the calendar point.
     * @param LOCATION is the location of the calendar point.
     * @param DESCRIPTION is the description of the calendar point.
     */
    public Data(String UID, String DTSTAMP, String DTSTART, String DTEND, String SUMMARY, String LOCATION, String DESCRIPTION) {
        this.UID = UID;
        this.DTSTAMP = DTSTAMP;
        this.DTSTART = DTSTART;
        this.DTEND = DTEND;
        this.SUMMARY = SUMMARY;
        this.LOCATION = LOCATION;
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getUID() {
        return this.UID;
    }

    public String getDTSTAMP() {
        return this.DTSTAMP;
    }

    public String getDTSTART() {
        return this.DTSTART;
    }

    public String getDTEND() {
        return this.DTEND;
    }

    public String getSUMMARY() {
        return this.SUMMARY;
    }
    
    public String getLOCATION() {
        return this.LOCATION;
    }

    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    @Override
    public String toString() {
        return "Data{" +
                "UID='" + UID + '\'' +
                ", DTSTAMP='" + DTSTAMP + '\'' +
                ", DTSTART='" + DTSTART + '\'' +
                ", DTEND='" + DTEND + '\'' +
                ", SUMMARY='" + SUMMARY + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                '}';
    }

    /**
     * I made this to simplify the equals method.
     */
    @Override
    public int hashCode() {
        return this.UID.hashCode()     + this.DTSTAMP.hashCode() + 
               this.DTSTART.hashCode() + this.DTEND.hashCode()   + 
               this.SUMMARY.hashCode() + this.DESCRIPTION.hashCode();
    }

    /**
     * Trivial equals method.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Data)) return false;
        Data other = (Data)obj;
        return this.hashCode() == other.hashCode();
    }
}
