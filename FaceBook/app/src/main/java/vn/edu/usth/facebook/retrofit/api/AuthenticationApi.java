package vn.edu.usth.facebook.retrofit.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.usth.facebook.model.request.ForgotPasswordRequest;
import vn.edu.usth.facebook.model.request.ResetPasswordRequest;
import vn.edu.usth.facebook.model.request.SignInRequest;
import vn.edu.usth.facebook.model.request.SignUpRequest;
import vn.edu.usth.facebook.model.response.JwtAuthenticationResponse;

public interface AuthenticationApi {
    @POST("/api/v1/auth/signUp")
    Call<JwtAuthenticationResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST("/api/v1/auth/signIn")
    Call<JwtAuthenticationResponse> signIn(@Body SignInRequest signInRequest);

    @POST("/api/v1/auth/forgot-password")
    Call<JwtAuthenticationResponse> forgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

    @POST("/api/v1/auth/reset-password")
    Call<JwtAuthenticationResponse> resetPassword(@Body ResetPasswordRequest resetPasswordRequest);

    @GET("api/v1/logout")
    Call<Void> logout();
}
