package vn.edu.usth.facebook.Notification;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.Search.SearchActivity;
import vn.edu.usth.facebook.model.response.user.NotificationResponse;
import vn.edu.usth.facebook.retrofit.NotificationApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class NotificationFragment extends Fragment {

    private List<NotificationItem> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerviewnotice);

        RetrofitService retrofitService = new RetrofitService(requireContext());
        NotificationApi notificationApi = retrofitService.getRetrofit().create(NotificationApi.class);

        Call<NotificationResponse> call = notificationApi.getAllNotifications();
        call.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Lấy dữ liệu từ response
                    NotificationResponse notificationResponse = response.body();
                    List<String> messages = notificationResponse.getMessage();
                    List<String> dates = notificationResponse.getDateCreated();

                    items.add(new NotificationItem("VTV24 was live", "12h", R.drawable.vid_ava_6));
                    items.add(new NotificationItem("Netflix posted a new video", "16h",  R.drawable.home_ava_6));
                    items.add(new NotificationItem("Vu Duc Duy invite you to like him", "2d",  R.drawable.vdd_avatar));
                    items.add(new NotificationItem("VALORANT was live", "3d", R.drawable.vid_ava_3));
                    items.add(new NotificationItem("500Bros CS2 posted a new video", "3d", R.drawable.vid_ava_4));
                    items.add(new NotificationItem("USTH's Capybara invited you to like USTH", "4d", R.drawable.capybara_usth));

                    // Chuyển đổi các dữ liệu thành các đối tượng NotificationItem
                    for (int i = 0; i < messages.size(); i++) {
                        String message = messages.get(i);
                        String date = dates.get(i);
                        int avatar = R.drawable.usth_avatar;
                        items.add(new NotificationItem(message, date, avatar));
                    }

                    // Cập nhật lại adapter với danh sách mới
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Toast.makeText(requireContext(), "Failed to load notifications", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Error fetching notifications", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new NotificationAdapter(requireContext(), items));

        setUpButton(v);

        return v;
    }

    private void setUpButton (View v){
        ImageButton searchbutton = v.findViewById(R.id.home_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), SearchActivity.class );
                startActivity(i);
            }
        });
    }
}