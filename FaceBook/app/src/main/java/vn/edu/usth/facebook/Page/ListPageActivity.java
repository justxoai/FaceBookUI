package vn.edu.usth.facebook.Page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.Page;
import vn.edu.usth.facebook.retrofit.RetrofitService;
import vn.edu.usth.facebook.retrofit.api.PageAPI;

public class ListPageActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private ListPageAdapter adapter;
    private List<ListPageItem> items;
    private PageAPI pageAPI;
    private RetrofitService retrofitService;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        init();
        sendRequest(name);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                name = newText;
                sendRequest(name);
                return true;
            }
        });
        setUpButtonListeners();
    }
    private void init() {
        items = new ArrayList<>();
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        recyclerView = findViewById(R.id.recyclerviewlistpage);
        adapter = new ListPageAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        retrofitService = new RetrofitService();
        pageAPI = retrofitService.getRetrofit().create(PageAPI.class);
    }
    private void setUpButtonListeners() {
        LinearLayout create_page = findViewById(R.id.create_page);
        create_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListPageActivity.this, CreatePageActivity.class);
                startActivity(i);
                finish();
            }
        });
        ImageButton back_button = findViewById(R.id.page_back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });
    }
    private void sendRequest(String name) {
        items.clear();
        pageAPI.findAllByNameContains(name).enqueue(new Callback<List<Page>>() {
            @Override
            public void onResponse(Call<List<Page>> call, Response<List<Page>> response) {
                if (response.isSuccessful()) {
                    for (Page page : response.body()) {
                        Bitmap avatarBitmap = null;
                        if (page.getAvatarB64() != null) {
                            byte[] avatarB64Bytes = Base64.decode(page.getAvatarB64(), Base64.DEFAULT);
                            avatarBitmap = BitmapFactory.decodeByteArray(avatarB64Bytes, 0, avatarB64Bytes.length);
                        }
                        items.add(new ListPageItem(page.getName(), avatarBitmap));
                    }
                    adapter.notifyDataSetChanged();
                    Log.i("ListPageActivity", "items size: " + items.size());
                } else {
                    Log.e("ListPageActivity", "have response but fail " + response);
                }
            }
            @Override
            public void onFailure(Call<List<Page>> call, Throwable t) {
                Log.e("ListPageActivity", "onFailure: " + t);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}