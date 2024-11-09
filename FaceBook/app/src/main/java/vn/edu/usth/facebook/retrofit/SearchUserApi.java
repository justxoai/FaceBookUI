package vn.edu.usth.facebook.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.edu.usth.facebook.model.response.user.SearchUserResponse;

public interface SearchUserApi {
    @GET("/api/v1/user/findAllUsers")
    Call<List<SearchUserResponse>> getAllUsers();

    @GET("/api/v1/user/search-user-by-firstname")
    Call<List<SearchUserResponse>> searchByFirstName();

    @GET("/api/v1/user/search-user-by-lastname")
    Call<List<SearchUserResponse>> searchByLastName();

    @GET("/api/v1/user/search-user-by-username")
    Call<List<SearchUserResponse>> searchByUserName();
}
