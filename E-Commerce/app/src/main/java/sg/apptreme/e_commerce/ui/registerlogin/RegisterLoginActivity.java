package sg.apptreme.e_commerce.ui.registerlogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sg.apptreme.e_commerce.R;
import sg.apptreme.e_commerce.ui.main.MainActivity;
import sg.apptreme.e_commerce.ui.registerlogin.mvp.RegisterLoginModel;
import sg.apptreme.e_commerce.ui.registerlogin.mvp.RegisterLoginPresenter;
import sg.apptreme.e_commerce.ui.registerlogin.mvp.RegisterLoginPresenterImpl;
import sg.apptreme.e_commerce.ui.registerlogin.mvp.RegisterLoginView;
import sg.apptreme.e_commerce.util.api.registerlogin.LoginPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterPayloadModel;
import sg.apptreme.e_commerce.util.common.Connectivity;

/**
 * Created by martinluternainggolan on 11/21/17.
 */

public class RegisterLoginActivity extends AppCompatActivity implements RegisterLoginView {
    private final String TAG = getClass().getName();

    @BindView(R.id.btn_phone_login)
    RelativeLayout btnPhoneLogin;
    @BindView(R.id.progress_bar_registerlogin)
    FrameLayout loadingLayout;

    private PopupWindow mPopUp;
    private RegisterLoginModel registerLoginModel;
    private RegisterLoginPresenter registerLoginPresenter;

    @Override
    public void viewState(ViewState viewState) {
        switch (viewState) {
            case IDLE:
                showLoading(false);
                break;
            case LOADING:
                showLoading(true);
                break;
            case SNACKBAR:
                showSnackbar();
                break;
            case LOGIN_SUCCESS:
                loginSuccess();
                break;
            case LOGIN_FAILURE:
                loginFailure();
                break;
            case REGISTER_SUCCESS:
                registerSuccess();
                break;
            case REGISTER_FAILURE:
                registerFailure();
                break;
            default:
                break;
        }
    }

    private void registerFailure() {
        if (doRetrieveModel().getRegisterResponseModel() != null) {
            doRetrieveModel().setSnackBarMsg(doRetrieveModel().getRegisterResponseModel().getErrorMsg());
            registerLoginPresenter.viewState(ViewState.SNACKBAR);
        } else {
            if (Connectivity.isConnected(getApplicationContext())) {
                doRetrieveModel().setSnackBarMsg("Something went wrong");
                registerLoginPresenter.viewState(ViewState.SNACKBAR);
            } else {
                doRetrieveModel().setSnackBarMsg("Please check your connection");
                registerLoginPresenter.viewState(ViewState.SNACKBAR);
            }
        }
    }

    private void registerSuccess() {
        registerLoginPresenter.viewState(ViewState.IDLE);
        if (doRetrieveModel().getRegisterResponseModel() != null) {
            if (!doRetrieveModel().getRegisterResponseModel().getError()) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            } else {
                registerLoginPresenter.viewState(ViewState.REGISTER_FAILURE);
            }
        } else {
            registerLoginPresenter.viewState(ViewState.REGISTER_FAILURE);
        }
    }

    private void loginFailure() {
        if (doRetrieveModel().getLoginResponseModel() != null) {
            doRetrieveModel().setSnackBarMsg(doRetrieveModel().getLoginResponseModel().getErrorMsg());
            registerLoginPresenter.viewState(ViewState.SNACKBAR);
        } else {
            if (Connectivity.isConnected(getApplicationContext())) {
                doRetrieveModel().setSnackBarMsg("Something went wrong");
                registerLoginPresenter.viewState(ViewState.SNACKBAR);
            } else {
                doRetrieveModel().setSnackBarMsg("Please check your connection");
                registerLoginPresenter.viewState(ViewState.SNACKBAR);
            }
        }
    }

    private void loginSuccess() {
        registerLoginPresenter.viewState(ViewState.IDLE);
        if (doRetrieveModel().getLoginResponseModel() != null) {
            if (!doRetrieveModel().getLoginResponseModel().getError()) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            } else {
                registerLoginPresenter.viewState(ViewState.LOGIN_FAILURE);
            }
        } else {
            registerLoginPresenter.viewState(ViewState.LOGIN_FAILURE);
        }
    }

    private void showLoading(boolean b) {
        if (b) {
            loadingLayout.setVisibility(View.VISIBLE);
        } else {
            loadingLayout.setVisibility(View.GONE);
        }
    }

    private void showSnackbar() {
        if (doRetrieveModel().getSnackBarMsg() != null &&
                !doRetrieveModel().getSnackBarMsg().equalsIgnoreCase("")) {
            Snackbar.make(btnPhoneLogin, doRetrieveModel().getSnackBarMsg(), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public RegisterLoginModel doRetrieveModel() {
        if (registerLoginModel == null) {
            registerLoginModel = new RegisterLoginModel();
        }
        return registerLoginModel;
    }

    @OnClick(R.id.btn_phone_login)
    public void onClickLogin() {
        showDialogLogin();
    }

    @OnClick(R.id.txt_signup)
    public void onClickRegister() {
        showDialogRegister();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerlogin);
        ButterKnife.bind(this);
        registerLoginPresenter = new RegisterLoginPresenterImpl(this);
    }

    private void showDialogLogin() {
        LayoutInflater inflater = (LayoutInflater) RegisterLoginActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog_registerlogin,
                (ViewGroup) findViewById(R.id.dialog_content_layout_registerlogin));

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        mPopUp = new PopupWindow(layout, display.getWidth(),
                display.getHeight(), true);

        FrameLayout btnLogin = (FrameLayout) layout.findViewById(R.id.layout_btn_input_login);
        final EditText inputLoginPhone = (EditText) layout.findViewById(R.id.editext_input_login_phone);
        final EditText inputLoginPass = (EditText) layout.findViewById(R.id.editext_input_login_pass);
        final RelativeLayout dialogLogin = (RelativeLayout) layout.findViewById(R.id.dialog_login);
        final RelativeLayout dialogRegister = (RelativeLayout) layout.findViewById(R.id.dialog_register);

        dialogRegister.setVisibility(View.GONE);
        dialogLogin.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputLoginPhone.getText().toString().trim().isEmpty() &&
                        !inputLoginPass.getText().toString().trim().isEmpty()) {
                    mPopUp.dismiss();
                    registerLoginPresenter.viewState(ViewState.LOADING);
                    doRetrieveModel().setLoginPhone(inputLoginPhone.getText().toString());
                    doRetrieveModel().setLoginPass(inputLoginPass.getText().toString());
                    registerLoginPresenter.viewState(ViewState.LOGIN);
                } else {
                    Snackbar.make(layout, "Please fill the all input field", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    mPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
                } catch (Exception e) {
                    Crashlytics.logException(e);
                }
            }
        }, 100L);
    }

    private void showDialogRegister() {
        LayoutInflater inflater = (LayoutInflater) RegisterLoginActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog_registerlogin,
                (ViewGroup) findViewById(R.id.dialog_content_layout_registerlogin));

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        mPopUp = new PopupWindow(layout, display.getWidth(),
                display.getHeight(), true);

        FrameLayout btnLogin = (FrameLayout) layout.findViewById(R.id.layout_btn_input_register);
        final EditText inputRegisterPhone = (EditText) layout.findViewById(R.id.editext_input_register_phone);
        final EditText inputRegisterPass = (EditText) layout.findViewById(R.id.editext_input_register_pass);
        final EditText inputRegisterName = (EditText) layout.findViewById(R.id.editext_input_register_name);
        final EditText inputRegisterEmail = (EditText) layout.findViewById(R.id.editext_input_register_email);
        final RelativeLayout dialogLogin = (RelativeLayout) layout.findViewById(R.id.dialog_login);
        final RelativeLayout dialogRegister = (RelativeLayout) layout.findViewById(R.id.dialog_register);

        dialogLogin.setVisibility(View.GONE);
        dialogRegister.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputRegisterPhone.getText().toString().trim().isEmpty() &&
                        !inputRegisterPass.getText().toString().trim().isEmpty() &&
                        !inputRegisterEmail.getText().toString().trim().isEmpty()) {
                    mPopUp.dismiss();
                    registerLoginPresenter.viewState(ViewState.LOADING);
                    doRetrieveModel().setRegisterPhone(inputRegisterPhone.getText().toString());
                    doRetrieveModel().setRegisterPass(inputRegisterPass.getText().toString());
                    doRetrieveModel().setRegisterEmail(inputRegisterEmail.getText().toString());
                    doRetrieveModel().setRegisterName(inputRegisterName.getText().toString());
                    registerLoginPresenter.viewState(ViewState.REGISTER);
                } else {
                    Snackbar.make(layout, "Please fill the all input field", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    mPopUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
                } catch (Exception e) {
                    Crashlytics.logException(e);
                }
            }
        }, 100L);
    }
}
