package sg.apptreme.e_commerce.util.api;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import sg.apptreme.e_commerce.util.api.registerlogin.LoginPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.LoginResponseModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterLoginService;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterResponseModel;

/**
 * Created by martinluternainggolan on 9/21/16.
 */
public class APICallManager {

    public enum APIRoute {
        LOGIN, REGISTER
    }

    private static APICallManager instance;
    private Retrofit restAdapter;

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
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        restAdapter = new Retrofit.Builder()
                .baseUrl(APISettings.HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public boolean register(String phone, String password, String email, String name, Callback<RegisterResponseModel> callback) {
        RegisterLoginService splashService = restAdapter.create(RegisterLoginService.class);
        Call<RegisterResponseModel> callSplashService = splashService.register(phone, password, email, name);
        callSplashService.enqueue(callback);
        return true;
    }

    public boolean login(String phone, String password, Callback<LoginResponseModel> callback) {
        RegisterLoginService splashService = restAdapter.create(RegisterLoginService.class);
        Call<LoginResponseModel> callSplashService = splashService.login(phone, password);
        callSplashService.enqueue(callback);
        return true;
    }
}
