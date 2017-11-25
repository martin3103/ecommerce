package sg.apptreme.e_commerce.util.api;

/**
 * Created by martinluternainggolan on 9/13/16.
 */
public class APIProductionSettings {
    public static final boolean RELEASE = true; //false=staging
    public static final String PRODUCTION_HOST = "https://beruang.co.id/";
    public static final String STAGING_HOST = "https://beruang.ezy-mpay.com/";
    public static final String HOST = RELEASE ? PRODUCTION_HOST : STAGING_HOST;

    public static final String PRODUCTION_HOST_FORM_POST = "https://fpg.fpay.io/fpg/v3/preAuthPaymentForm/";
    public static final String STAGING_HOST_FORM_POST = "https://stg.fpay.io/fpg/v3/preAuthPaymentForm/";
    public static final String HOST_FORM_POST = RELEASE ? PRODUCTION_HOST_FORM_POST : STAGING_HOST_FORM_POST;
}
