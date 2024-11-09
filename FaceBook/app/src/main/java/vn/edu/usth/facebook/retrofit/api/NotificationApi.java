package vn.edu.usth.facebook.retrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.edu.usth.facebook.model.response.user.NotificationResponse;

public interface NotificationApi {
    @GET("/api/v1/user/notifications/getNotificationsByUser")
    Call<NotificationResponse> getAllNotifications();
}
