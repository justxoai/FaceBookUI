package vn.edu.usth.facebook.retrofit.api;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import vn.edu.usth.facebook.model.Page;
import vn.edu.usth.facebook.model.response.CommonResponse;

public interface PageAPI {
    @GET("/api/v1/page")
    Call<List<Page>> findAllByNameContains(@Query("name") String name);
    @POST("/api/v1/page")
    Call<CommonResponse> createPage(@Body RequestBody requestBody);
}
