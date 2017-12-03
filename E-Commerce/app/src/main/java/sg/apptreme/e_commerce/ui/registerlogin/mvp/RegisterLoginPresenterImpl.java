package sg.apptreme.e_commerce.ui.registerlogin.mvp;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import sg.apptreme.e_commerce.util.api.APICallListener;
import sg.apptreme.e_commerce.util.api.APICallManager;
import sg.apptreme.e_commerce.util.api.registerlogin.LoginResponseModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterResponseModel;

/**
 * Created by martinluternainggolan on 11/25/17.
 */

public class RegisterLoginPresenterImpl implements RegisterLoginPresenter, APICallListener {
    private final String TAG = getClass().getName();
    private RegisterLoginInteractor registerLoginInteractor;
    private RegisterLoginView registerLoginView;

    public RegisterLoginPresenterImpl(RegisterLoginView registerLoginView) {
        this.registerLoginInteractor = new RegisterLoginInteractorImpl(this);
        this.registerLoginView = registerLoginView;
    }

    @Override
    public void viewState(RegisterLoginView.ViewState viewState) {
        switch (viewState) {
            case IDLE:
                registerLoginView.viewState(RegisterLoginView.ViewState.IDLE);
                break;
            case LOADING:
                registerLoginView.viewState(RegisterLoginView.ViewState.LOADING);
                break;
            case SNACKBAR:
                registerLoginView.viewState(RegisterLoginView.ViewState.SNACKBAR);
                break;
            case REGISTER:
                registerLoginInteractor.register(registerLoginView.doRetrieveModel().getRegisterPhone(),
                        registerLoginView.doRetrieveModel().getRegisterPass(),
                        registerLoginView.doRetrieveModel().getRegisterEmail(),
                        registerLoginView.doRetrieveModel().getRegisterName());
                break;
            case REGISTER_SUCCESS:
                registerLoginView.viewState(RegisterLoginView.ViewState.REGISTER_SUCCESS);
                break;
            case REGISTER_FAILURE:
                registerLoginView.viewState(RegisterLoginView.ViewState.REGISTER_FAILURE);
                break;
            case LOGIN:
                registerLoginInteractor.login(registerLoginView.doRetrieveModel().getLoginPhone(),
                        registerLoginView.doRetrieveModel().getLoginPass());
                break;
            case LOGIN_SUCCESS:
                registerLoginView.viewState(RegisterLoginView.ViewState.LOGIN_SUCCESS);
                break;
            case LOGIN_FAILURE:
                registerLoginView.viewState(RegisterLoginView.ViewState.LOGIN_FAILURE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute route, Response response) {
        if (route == APICallManager.APIRoute.REGISTER) {
            registerLoginView.doRetrieveModel().setRegisterResponseModel((RegisterResponseModel) response.body());
            registerLoginView.viewState(RegisterLoginView.ViewState.REGISTER_SUCCESS);
        } else if (route == APICallManager.APIRoute.LOGIN) {
            registerLoginView.doRetrieveModel().setLoginResponseModel((LoginResponseModel) response.body());
            registerLoginView.viewState(RegisterLoginView.ViewState.LOGIN_SUCCESS);
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute route, Response response, Call call, Throwable t) {
        String errorMessage;

        if (response != null) {
            errorMessage = response.message();

            try {
                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                if (jsonObject.has("error_code")) {
                    registerLoginView.doRetrieveModel().setErrorCode(jsonObject.getString("error_code"));
                }
                if (jsonObject.has("error_msg")) {
                    registerLoginView.doRetrieveModel().setErrorMessage(jsonObject.getString("error_msg"));
                }
            } catch (Exception e) {
                registerLoginView.doRetrieveModel().setErrorCode("");
                registerLoginView.doRetrieveModel().setErrorMessage(e.getMessage());
                Crashlytics.log(Log.ERROR, TAG, e.getMessage());
            }
        } else {
            errorMessage = t.getMessage();
            registerLoginView.doRetrieveModel().setErrorCode("");
            registerLoginView.doRetrieveModel().setErrorMessage("");
        }
        call.cancel();

        if (route == APICallManager.APIRoute.REGISTER) {
            Crashlytics.log(Log.ERROR, TAG, errorMessage);
            registerLoginView.viewState(RegisterLoginView.ViewState.REGISTER_FAILURE);
        } else if (route == APICallManager.APIRoute.LOGIN) {
            Crashlytics.log(Log.ERROR, TAG, errorMessage);
            registerLoginView.viewState(RegisterLoginView.ViewState.LOGIN_FAILURE);
        }
    }
}
