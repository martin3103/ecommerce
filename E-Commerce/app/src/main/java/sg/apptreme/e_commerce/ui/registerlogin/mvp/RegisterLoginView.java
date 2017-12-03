package sg.apptreme.e_commerce.ui.registerlogin.mvp;

/**
 * Created by martinluternainggolan on 11/25/17.
 */

public interface RegisterLoginView {
    public enum ViewState {
        IDLE, LOADING, SNACKBAR,
        LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE,
        REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE
    }

    public void viewState(ViewState viewState);

    public RegisterLoginModel doRetrieveModel();
}
