package sg.apptreme.e_commerce.util.api.registerlogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by martinluternainggolan on 11/25/17.
 */

public interface RegisterLoginService {
    @FormUrlEncoded
    @POST("v0/users/register.php")
    Call<RegisterResponseModel> register(@Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("email") String email,
                                         @Field("name") String name);

    @FormUrlEncoded
    @POST("v0/users/login.php")
    Call<LoginResponseModel> login(@Field("phone") String phone,
                                   @Field("password") String password);
}
