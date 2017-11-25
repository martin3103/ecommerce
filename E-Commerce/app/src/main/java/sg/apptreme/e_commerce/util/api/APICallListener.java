package sg.apptreme.e_commerce.util.api;

import retrofit2.Call;

/**
 * Created by martinluter on 11/4/15.
 */
public interface APICallListener {
    void onAPICallSucceed(APICallManager.APIRoute route, retrofit2.Response response);

    void onAPICallFailed(APICallManager.APIRoute route, retrofit2.Response response, Call call, Throwable t);
}
