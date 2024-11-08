package vn.edu.usth.facebook.retrofit.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.edu.usth.facebook.model.Page;

public interface PageAPI {
    @GET("/api/v1/page")
    Call<List<Page>> getAllPage(@Query("name") String name);
}
