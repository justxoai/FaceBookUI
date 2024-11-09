package vn.edu.usth.facebook.User;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.Page.ListPageItem;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.Search.SearchActivity;
import vn.edu.usth.facebook.model.Page;
import vn.edu.usth.facebook.model.response.UserResponse;
import vn.edu.usth.facebook.retrofit.RetrofitService;
import vn.edu.usth.facebook.retrofit.api.UserFriendAPI;

public class ListAddFriendActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private NotFriendAdapter adapter;
    private List<NotFriendItem> items;
    private UserFriendAPI userFriendAPI;
    private RetrofitService retrofitService;
    private String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add_friend);
        init();
        sendRequest(query);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                query = newText;
                sendRequest(query);
                return true;
            }
        });
        setUpButtonListeners();
    }
    private void init() {
        items = new ArrayList<>();
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        recyclerView = findViewById(R.id.recyclerviewaddfriend1);
        adapter = new NotFriendAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        retrofitService = new RetrofitService(this);
        userFriendAPI = retrofitService.getRetrofit().create(UserFriendAPI.class);
    }
    private void setUpButtonListeners() {
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });
        ImageButton searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAddFriendActivity.this, SearchActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void sendRequest(String query) {
        items.clear();
        userFriendAPI.getStrangers(query).enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if (response.isSuccessful()) {
                    for (UserResponse userResponse : response.body()) {
                        Bitmap avatarBitmap = null;
                        if (userResponse.getAvatarB64() != null) {
                            byte[] avatarB64Bytes = Base64.decode(userResponse.getAvatarB64(), Base64.DEFAULT);
                            avatarBitmap = BitmapFactory.decodeByteArray(avatarB64Bytes, 0, avatarB64Bytes.length);
                        }
                        items.add(new NotFriendItem(userResponse.getFullname(), avatarBitmap));
                    }
                    adapter.notifyDataSetChanged();
                    Log.i("ListAddFriendActivity", "items size: " + items.size());
                } else {
                    Log.e("ListAddFriendActivity", "have response but fail " + response);
                }
            }
            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e("ListAddFriendActivity", "onFailure: " + t);
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}