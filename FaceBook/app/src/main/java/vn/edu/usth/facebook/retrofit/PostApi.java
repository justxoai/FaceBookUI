package vn.edu.usth.facebook.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import vn.edu.usth.facebook.model.request.user.PostRequest;
import vn.edu.usth.facebook.model.response.user.PostResponse;

public interface PostApi {
    @POST("/api/v1/user/post/add")
    Call<PostResponse> createPost(@Body PostRequest postRequest);
}

