package vn.edu.usth.facebook.retrofit.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.usth.facebook.model.request.user.UserAboutRequest;
import vn.edu.usth.facebook.model.response.user.UserAboutResponse;

public interface UserAboutApi {
    @GET("/api/v1/user/userAboutByUser")
    Call<UserAboutResponse> getUserAboutByUser();

    // POST method to create user about information
    @POST("/api/v1/user/createUserAbout")
    Call<UserAboutResponse> createUserAbout(@Body UserAboutRequest userAboutRequest);
}

