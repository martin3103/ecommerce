package sg.apptreme.e_commerce.ui.registerlogin.mvp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.apptreme.e_commerce.util.api.APICallListener;
import sg.apptreme.e_commerce.util.api.APICallManager;
import sg.apptreme.e_commerce.util.api.registerlogin.LoginPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.LoginResponseModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterResponseModel;

/**
 * Created by martinluternainggolan on 11/25/17.
 */

public class RegisterLoginInteractorImpl implements RegisterLoginInteractor {
    private APICallListener apiCallListener;

    public RegisterLoginInteractorImpl(APICallListener apiCallListener) {
        this.apiCallListener = apiCallListener;
    }

    @Override
    public void login(String phone, String password) {
        final APICallManager.APIRoute route = APICallManager.APIRoute.LOGIN;
        APICallManager.getInstance().login(phone, password, new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    apiCallListener.onAPICallSucceed(route, response);
                } else {
                    apiCallListener.onAPICallFailed(route, response, call, null);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                apiCallListener.onAPICallFailed(route, null, call, t);
            }
        });
    }

    @Override
    public void register(String phone, String password, String email, String name) {
        final APICallManager.APIRoute route = APICallManager.APIRoute.REGISTER;
        APICallManager.getInstance().register(phone, password, email, name, new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    apiCallListener.onAPICallSucceed(route, response);
                } else {
                    apiCallListener.onAPICallFailed(route, response, call, null);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                apiCallListener.onAPICallFailed(route, null, call, t);
            }
        });
    }
}
