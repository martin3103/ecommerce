package sg.apptreme.e_commerce.util.common.sqlite.model;

/**
 * Created by martinluternainggolan on 9/13/16.
 */
public class SqlitePreference {

    String constant;
    String value;
    Long timestamp;

    public SqlitePreference() {

    }

    public SqlitePreference(String constant, String value) {
        this.constant = constant;
        this.value = value;
        timestamp = System.currentTimeMillis();
    }

    public SqlitePreference(String constant, String value, Long timestamp) {
        this.constant = constant;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getConstant() {
        return constant.toLowerCase();
    }

    public String getValue() {
        return value;
    }

    public void setConstant(String constant) {
        this.constant = constant.toLowerCase();
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
