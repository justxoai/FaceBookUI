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

public class List_Add_Friend_Activity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private NotFriendAdapter adapter;
    private List<NotFriendItem> items;
    private List<NotFriendItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_add_friend);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        recyclerView = findViewById(R.id.recyclerviewaddfriend1);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        items.add(new NotFriendItem("User",  R.drawable.user));
        items.add(new NotFriendItem("User1",  R.drawable.user));
        items.add(new NotFriendItem("User2",  R.drawable.user));
        items.add(new NotFriendItem("User3",  R.drawable.user));
        items.add(new NotFriendItem("User4",  R.drawable.user));
        items.add(new NotFriendItem("User5",  R.drawable.user));
        items.add(new NotFriendItem("User6",  R.drawable.user));
        items.add(new NotFriendItem("User7",  R.drawable.user));

        filteredItems.addAll(items);
        adapter = new NotFriendAdapter(this, filteredItems);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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

        ImageButton searchbutton = findViewById(R.id.search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(List_Add_Friend_Activity.this, vn.edu.usth.facebook.Search.Search_Activity.class);
                startActivity(i);
            }
        });
    }

    private void filterList(String text) {
        filteredItems.clear();
        for (NotFriendItem item : items) {
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