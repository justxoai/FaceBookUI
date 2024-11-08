package vn.edu.usth.facebook.Group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.response.group.GroupResponse;
import vn.edu.usth.facebook.retrofit.GroupApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class ListGroup_Activity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private GroupAdapter adapter;
    private List<GroupItem> items;
    private List<GroupItem> filteredItems;
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_group);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        recyclerView = findViewById(R.id.recyclerviewgroup_01);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        retrofitService = new RetrofitService(this);
        GroupApi groupApi = retrofitService.getRetrofit().create(GroupApi.class);

        Call<List<GroupResponse>> call = groupApi.getAllGroups();
        call.enqueue(new Callback<List<GroupResponse>>() {
            @Override
            public void onResponse(Call<List<GroupResponse>> call, Response<List<GroupResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GroupResponse> groupResponses = response.body();

                    // Convert each GroupResponse into a GroupItem and add to items
                    for (GroupResponse groupResponse : groupResponses) {
                        items.add(new GroupItem(
                                groupResponse.getTitle(),
                                groupResponse.getDescription(),
                                groupResponse.getBackgroundImageUrl()
                        ));
                    }

                    // Update the adapter with the new data
                    filteredItems.addAll(items);
                    adapter = new GroupAdapter(ListGroup_Activity.this, filteredItems);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListGroup_Activity.this));
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ListGroup_Activity.this, "Failed to load groups", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GroupResponse>> call, Throwable t) {
                Toast.makeText(ListGroup_Activity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

        setUpButton();

    }

    private void setUpButton (){
        LinearLayout creategroup = findViewById(R.id.create_group);
        creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListGroup_Activity.this, CreateGroup_Activity.class);
                startActivity(i);
                finish();
            }
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void filterList(String text) {
        filteredItems.clear();
        for (GroupItem item : items) {
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
    public void onBackPressed(){
        super.onBackPressed();
    }
}
