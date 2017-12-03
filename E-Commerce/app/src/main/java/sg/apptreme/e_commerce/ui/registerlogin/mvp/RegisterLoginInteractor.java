package sg.apptreme.e_commerce.ui.registerlogin.mvp;

import sg.apptreme.e_commerce.util.api.registerlogin.LoginPayloadModel;
import sg.apptreme.e_commerce.util.api.registerlogin.RegisterPayloadModel;

/**
 * Created by martinluternainggolan on 11/25/17.
 */

public interface RegisterLoginInteractor {

    public void login(String phone, String password);

    public void register(String phone, String password, String email, String name);
}
