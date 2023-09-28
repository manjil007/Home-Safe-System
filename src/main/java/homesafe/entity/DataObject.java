package homesafe.entity;

/**
 * Interface used for the different Data Objects for input collection from the
 * display and keyboard.
 */
public interface DataObject {

    /**
     * Method to get the name of the active field in the Data Object.
     * @return name of active field
     */
    public String getActiveName();

    /**
     * Method to get the data in the active field of the Data Object
     * @return data in the active field
     */
    public String getActiveData();

    /**
     * Method to update the name of the active field in the Data Object.
     * @param field name of new active field
     */
    public void setActiveName(String field);

    /**
     * Method to append data to the current active field in the Data Object.
     * @param str data to append
     */
    public void appendToActiveData(String str);

    /**
     * Method that will process the data to the correct system depending on
     * the type of Data Object it is.
     */
    public void process();

}
