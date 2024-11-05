package vn.edu.usth.facebook.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.usth.facebook.model.request.SignInRequest;
import vn.edu.usth.facebook.model.request.SignUpRequest;
import vn.edu.usth.facebook.model.response.JwtAuthenticationResponse;

public interface AuthenticationApi {
    @POST("/api/v1/auth/signUp")
    Call<JwtAuthenticationResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST("/api/v1/auth/signIn")
    Call<JwtAuthenticationResponse> signIn(@Body SignInRequest signInRequest);

    @GET("api/v1/logout")
    Call<Void> logout();
}
