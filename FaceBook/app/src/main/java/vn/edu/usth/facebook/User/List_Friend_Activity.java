package vn.edu.usth.facebook.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class List_Friend_Activity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private FriendAdapter adapter;
    private List<FriendItem> items;
    private List<FriendItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        // Initialize RecyclerView and data list
        recyclerView = findViewById(R.id.recyclerviewlistfriend);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        items.add(new FriendItem("User",  R.drawable.user));
        items.add(new FriendItem("User1",  R.drawable.user));
        items.add(new FriendItem("User2",  R.drawable.user));
        items.add(new FriendItem("User3",  R.drawable.user));
        items.add(new FriendItem("User4",  R.drawable.user));
        items.add(new FriendItem("User5",  R.drawable.user));
        items.add(new FriendItem("User6",  R.drawable.user));
        items.add(new FriendItem("User7",  R.drawable.user));

        // Initially, the filtered list should contain all items
        filteredItems.addAll(items);

        // Set up the adapter with the filtered list
        adapter = new FriendAdapter(this, filteredItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Set up the search functionality
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

    private void setUpButton() {
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        ImageButton addfriend = findViewById(R.id.add_friend);
        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(List_Friend_Activity.this, ListAddFriendActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void filterList(String text) {
        filteredItems.clear();
        for (FriendItem item : items) {
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
