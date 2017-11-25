package sg.apptreme.e_commerce.util.api;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by martinluternainggolan on 9/21/16.
 */
public class APICallManager {

    public enum APIRoute {
        GDAY, CUSTOMER_CONFIG, REGISTER, LOGIN,
        CHECK_PRODUCT, CHECK_CREDENTIAL, SUBSCRIBER_DETAIL, SUBSCRIBER_PREFERENCES,
        GET_CARD_LIST, DELETE_CARD, GET_ACTIVE_CARD_LIST,
        CHANGE_PIN, CHANGE_EMAIL, RESEND_EMAIL, UPDATE_USER_PREFERENCE,
        SURPRISE, FAQ, TNC, ABOUT, BANKLIST, CHECKSUM,
        HISTORY, DETAIL_HISTORY, RECENT, GET_SUB_CATEGORIES,
        PRODUCTS_DETAIL, PRE_PURCHASE, BALANCE_CHECK, QUERY_PURCHASE,
        INIT_PURCHASE, TOKEN_PURCHASE, BIN_CHECK, QUESTION_MARK,
        CLICK_QUESTION_MARK, CLICK_TANYA_BERUANG, VIEW_PAGE_QUESTION_MARK,
        QUERY_REFERRAL_TOKEN, VALIDATE_REFERRAL_TOKEN, QUERY_LIST_BONUSES
    }

    private static APICallManager instance;
    private Retrofit restAdapter, restAdapterQuestionMark, restAdapterWTFTracker;

    /**
     * Returns singleton class instance
     */
    public static APICallManager getInstance() {
        if (instance == null) {
            synchronized (APICallManager.class) {
                if (instance == null) {
                    instance = new APICallManager();
                }
            }

        }
        return instance;
    }

    public APICallManager() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        String endPoint, endPointQuestionMark, endPointWTFTracker;
        if (APIRootSettings.IS_READY_TO_PRODUCTION) {
            endPoint = APIProductionSettings.HOST;
        } else {
            endPoint = APITestingSettings.HOST;
        }
        endPointQuestionMark = APIRootSettings.HOST_QUESTION_MARK;
        endPointWTFTracker = APIRootSettings.HOST_WTF_TRACKER;

        restAdapter = new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restAdapterQuestionMark = new Retrofit.Builder()
                .baseUrl(endPointQuestionMark)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restAdapterWTFTracker = new Retrofit.Builder()
                .baseUrl(endPointWTFTracker)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

//    public boolean gDay(String authorization, GoodDayPayloadModel typedInput, Callback<GoodDayResponseModel> callback) {
//        SplashService splashService = restAdapter.create(SplashService.class);
//        Call<GoodDayResponseModel> callSplashService = splashService.goodDay(authorization, typedInput);
//        callSplashService.enqueue(callback);
//        return true;
//    }
}
