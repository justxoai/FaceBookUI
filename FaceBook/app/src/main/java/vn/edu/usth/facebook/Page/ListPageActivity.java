package vn.edu.usth.facebook.Page;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private List<ListPageItem> filteredItems;
    private PageAPI pageAPI;
    private String name = "";
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_page);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        retrofitService = new RetrofitService();
        items = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewlistpage);
        filteredItems = new ArrayList<>();

        pageAPI = RetrofitService.getInstance().create(PageAPI.class);
        Call<List<Page>> call = pageAPI.getAllPage(name);
        call.enqueue(new Callback<List<Page>>() {
            @Override
            public void onResponse(Call<List<Page>> call, Response<List<Page>> response) {
                if (response.isSuccessful()) {
                    for (Page page : response.body()) {
                        items.add(new ListPageItem(page.getName(), R.drawable.bridge));
                    }
                } else {
                    Log.e("ListPageActivity", "request fail");
                }
            }

            @Override
            public void onFailure(Call<List<Page>> call, Throwable t) {
                // Handle failure
            }
        });

//        items.add(new ListPageItem("Nature Exploring",  R.drawable.bridge));
//        items.add(new ListPageItem("Love from Nature",  R.drawable.woods));
//        items.add(new ListPageItem("Beauty of the Sea",  R.drawable.girl_hat));
//        items.add(new ListPageItem("Rag - Demi Store",  R.drawable.jeans));
//        items.add(new ListPageItem("Capybara",  R.drawable.capybara_usth));
//        items.add(new ListPageItem("USTH",  R.drawable.usth_avatar));
//        items.add(new ListPageItem("12A1.1",  R.drawable.a1_1));

        filteredItems.addAll(items);

        adapter = new ListPageAdapter(this, filteredItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                filterList(newText);
                name = newText;
                return true;
            }
        });

        setUpButton();

    }

    private void setUpButton() {
        LinearLayout create_page = findViewById(R.id.create_page);
        create_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListPageActivity.this, CreatePage_Activity.class);
                startActivity(i);
                finish();
            }
        });

        ImageButton back_button = findViewById(R.id.page_back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void filterList(String text) {
        filteredItems.clear();
        for (ListPageItem item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        if (filteredItems.isEmpty()) {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}