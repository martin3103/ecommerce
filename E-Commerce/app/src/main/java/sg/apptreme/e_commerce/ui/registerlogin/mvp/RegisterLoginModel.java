package sg.apptreme.e_commerce.ui.registerlogin.mvp;

import sg.apptreme.e_commerce.util.api.registerlogin.LoginPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.LoginResponseModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterResponseModel;

/**
 * Created by martinluternainggolan on 11/25/17.
 */

public class RegisterLoginModel {
    private LoginPayloadModel loginPayloadModel;
    private LoginResponseModel loginResponseModel;
    private RegisterPayloadModel registerPayloadModel;
    private RegisterResponseModel registerResponseModel;

    private String loginPass, loginPhone;
    private String registerPass, registerPhone, registerEmail, registerName;

    private String errorCode, errorMessage;
    private String snackBarMsg;

    public String getSnackBarMsg() {
        return snackBarMsg;
    }

    public void setSnackBarMsg(String snackBarMsg) {
        this.snackBarMsg = snackBarMsg;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public String getRegisterPass() {
        return registerPass;
    }

    public void setRegisterPass(String registerPass) {
        this.registerPass = registerPass;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LoginPayloadModel getLoginPayloadModel() {
        return loginPayloadModel;
    }

    public void setLoginPayloadModel(LoginPayloadModel loginPayloadModel) {
        this.loginPayloadModel = loginPayloadModel;
    }

    public LoginResponseModel getLoginResponseModel() {
        return loginResponseModel;
    }

    public void setLoginResponseModel(LoginResponseModel loginResponseModel) {
        this.loginResponseModel = loginResponseModel;
    }

    public RegisterPayloadModel getRegisterPayloadModel() {
        return registerPayloadModel;
    }

    public void setRegisterPayloadModel(RegisterPayloadModel registerPayloadModel) {
        this.registerPayloadModel = registerPayloadModel;
    }

    public RegisterResponseModel getRegisterResponseModel() {
        return registerResponseModel;
    }

    public void setRegisterResponseModel(RegisterResponseModel registerResponseModel) {
        this.registerResponseModel = registerResponseModel;
    }
}
