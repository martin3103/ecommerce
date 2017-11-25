package sg.apptreme.e_commerce.util.api;

/**
 * Created by martinluternainggolan on 2/10/17.
 */

public class APITestingSettings {
    public static final boolean RELEASE = true; //false=dev
    public static final String QA_HOST = "http://iaskl.duckdns.org/qa_branch/";
    public static final String DEV_HOST = "http://iaskl.duckdns.org/dev_trunk/";
    public static final String HOST = RELEASE ? QA_HOST : DEV_HOST;

    public static final String HOST_FORM_POST = "http://iaskl.duckdns.org/qa_branch/fpg/v3/preAuthPaymentForm/";
}
