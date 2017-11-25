package sg.apptreme.e_commerce.util.api.main;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by martinluternainggolan on 10/4/16.
 */

public interface MainService {
//    @GET("fdr/v3/cards/{fp_locale}/{fp_customer_code}/{fp_subscriber_id}")
//    Call<ListCardResponseModel> getListCard(@Header("Authorization") String authorization,
//                                            @Header("X-CSRF-TOKEN") String csrfToken,
//                                            @Path("fp_locale") String fpLocale,
//                                            @Path("fp_customer_code") String fpCustomerCode,
//                                            @Path("fp_subscriber_id") String fpSubscriberId);
//
//    @GET("fdr/v3/cards/{fp_locale}/{fp_customer_code}/{fp_subscriber_id}")
//    Call<ListCardResponseModel> getListActiveCard(@Header("Authorization") String authorization,
//                                                  @Header("X-CSRF-TOKEN") String csrfToken,
//                                                  @Path("fp_locale") String fpLocale,
//                                                  @Path("fp_customer_code") String fpCustomerCode,
//                                                  @Path("fp_subscriber_id") String fpSubscriberId,
//                                                  @Query("status") String status);
//
//
//    @DELETE("fdr/v3/cards/{fp_locale}/{fp_customer_code}/{fp_subscriber_id}")
//    Call<DeleteCardResponseModel> deleteCard(@Header("Authorization") String authorization,
//                                             @Header("X-CSRF-TOKEN") String csrfToken,
//                                             @Path("fp_locale") String fpLocale,
//                                             @Path("fp_customer_code") String fpCustomerCode,
//                                             @Path("fp_subscriber_id") String fpSubscriberId,
//                                             @Query("token") String fpTokenChiper,
//                                             @Query("request_id") String requestId);
//
//    @PUT("fdr/v3/user/pin")
//    Call<ChangePINResponseModel> changePIN(@Header("Authorization") String authorization,
//                                           @Header("X-CSRF-TOKEN") String csrfToken,
//                                           @Body ChangePINPayloadModel bodyRequestModel);
//
//    @PUT("fdr/v3/user/email")
//    Call<ChangeEmailResponseModel> changeEmail(@Header("Authorization") String authorization,
//                                               @Header("X-CSRF-TOKEN") String csrfToken,
//                                               @Body ChangeEmailPayloadModel bodyRequestModel);
//
//    @POST("fdr/v3/email?action=resend")
//    Call<ResendEmailResponseModel> resendEmail(@Header("Authorization") String authorization,
//                                               @Header("X-CSRF-TOKEN") String csrfToken,
//                                               @Body ResendEmailPayloadModel bodyRequestModel);
//
//    @PUT("fdr/v3/user/preference")
//    Call<UserPreferenceResponseModel> userPreference(@Header("Authorization") String authorization,
//                                                     @Header("X-CSRF-TOKEN") String csrfToken,
//                                                     @Body UserPreferencePayloadModel bodyRequestModel);
//
//    @GET("fdr/v3/user/{fp_locale}/{fp_customerCode}/{fp_subscriber_id}/details")
//    Call<SubscriberDetailResponseModel> getSubscriberDetail(@Header("Authorization") String authorization,
//                                                            @Header("X-CSRF-TOKEN") String csrfToken,
//                                                            @Path("fp_locale") String fpLocale,
//                                                            @Path("fp_customerCode") String fpCustomerCode,
//                                                            @Path("fp_subscriber_id") String fpSubscriberId,
//                                                            @Query("request_id") String requestId);
//
//    @GET("fdr/v3/user/preference/{fp_locale}/{fp_customerCode}/{fp_subscriber_id}/{fp_preference_key}")
//    Call<SubscriberPreferencesResponseModel> getSubscriberPreferences(@Header("Authorization") String authorization,
//                                                                      @Header("X-CSRF-TOKEN") String csrfToken,
//                                                                      @Path("fp_locale") String fpLocale,
//                                                                      @Path("fp_customerCode") String fpCustomerCode,
//                                                                      @Path("fp_subscriber_id") String fpSubscriberId,
//                                                                      @Path("fp_preference_key") String fpPreferenceKey,
//                                                                      @Query("request_id") String requestId);
//
//    @GET("fdr/v3/bonus/token/{fp_locale}/{fp_customer_code}/{fp_credential}/{fp_subscriber_id}")
//    Call<QueryReferralTokenResponseModel> getQueryReferralToken(@Header("Authorization") String authorization,
//                                                                @Header("X-CSRF-TOKEN") String csrfToken,
//                                                                @Path("fp_locale") String fpLocale,
//                                                                @Path("fp_customer_code") String fpCustomerCode,
//                                                                @Path("fp_credential") String fpCredential,
//                                                                @Path("fp_subscriber_id") String fpSubscriberId,
//                                                                @Query("status") String status,
//                                                                @Query("request_id") String requestId);
//
//    @POST("fdr/v3/bonus/token")
//    Call<ValidateReferralTokenResponseModel> validateReferralToken(@Header("Authorization") String authorization,
//                                                                   @Header("X-CSRF-TOKEN") String csrfToken,
//                                                                   @Body ValidateReferralTokenPayloadModel bodyRequestModel);
}
