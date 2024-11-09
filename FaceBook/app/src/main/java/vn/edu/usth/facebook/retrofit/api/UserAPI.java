package vn.edu.usth.facebook.retrofit.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.edu.usth.facebook.model.Page;
import vn.edu.usth.facebook.model.response.UserResponse;

public interface UserAPI {
    @GET("/api/v1/user/profile")
    Call<UserResponse> getProfile();
}
