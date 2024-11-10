package vn.edu.usth.facebook.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.usth.facebook.model.request.group.GroupRequest;
import vn.edu.usth.facebook.model.response.group.GroupResponse;

public interface GroupApi {
    @GET("/api/v1/group/getAllGroups")
    Call<List<GroupResponse>> getAllGroups();


    @POST("/api/v1/group/add")
    Call<GroupResponse> createGroup(@Body GroupRequest groupRequest);
}
