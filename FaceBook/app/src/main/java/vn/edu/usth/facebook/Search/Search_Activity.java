package vn.edu.usth.facebook.Search;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.Page.ListPageAdapter;
import vn.edu.usth.facebook.Page.ListPageItem;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.response.user.SearchUserResponse;
import vn.edu.usth.facebook.retrofit.RetrofitService;
import vn.edu.usth.facebook.retrofit.SearchUserApi;

public class Search_Activity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private UserSearchAdapter adapter;
    private List<UserSearchItem> items;
    private List<UserSearchItem> filteredItems;
    private RetrofitService retrofitService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        recyclerView = findViewById(R.id.recyclerviewsearch);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        retrofitService = new RetrofitService(this);
        SearchUserApi searchUserApi = retrofitService.getRetrofit().create(SearchUserApi.class);

        // Gọi API getAllUsers
        Call<List<SearchUserResponse>> call = searchUserApi.getAllUsers();
        call.enqueue(new Callback<List<SearchUserResponse>>() {
            @Override
            public void onResponse(Call<List<SearchUserResponse>> call, Response<List<SearchUserResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SearchUserResponse> userList = response.body();
                    for (SearchUserResponse user : userList) {
                        String imageUrl = user.getImageUrl();
                        UserSearchItem item = new UserSearchItem(user.getFirstName() + " " + user.getLastName(), user.getUserName(), imageUrl);
                        items.add(item);
                    }

                    // Cập nhật adapter và dữ liệu
                    filteredItems.addAll(items);
                    adapter = new UserSearchAdapter(Search_Activity.this, filteredItems);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Search_Activity.this));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<SearchUserResponse>> call, Throwable t) {
                Toast.makeText(Search_Activity.this, "Error fetching users", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void filterList(String text) {
        filteredItems.clear();
        for (UserSearchItem item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase()) || item.getContent().toLowerCase().contains(text.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        if (filteredItems.isEmpty()) {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}