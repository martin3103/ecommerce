package sg.apptreme.e_commerce.util.api;

/**
 * Created by martinluternainggolan on 9/13/16.
 */
public class APISettings {
    public static final boolean RELEASE = false; //false=staging
    public static final String PRODUCTION_HOST = "https://apptreme.sg/";
    public static final String STAGING_HOST = "https://staging.apptreme.sg/";
    public static final String HOST = RELEASE ? PRODUCTION_HOST : STAGING_HOST;
}
