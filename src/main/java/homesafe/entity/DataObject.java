package homesafe.entity;

/**
 * created by:
 * author: MichaelMillar
 */
public interface DataObject {
    public String getActiveName();
    public String getActiveData();
    public void setActiveName(String field);
    public void appendToActiveData(String str);
    public void process();

}
