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

import vn.edu.usth.facebook.R;

public class ListGroup_Activity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private GroupAdapter adapter;
    private List<GroupItem> items;
    private List<GroupItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_group);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        recyclerView = findViewById(R.id.recyclerviewgroup_01);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        items.add(new GroupItem("USTH",  "8 posts recently", R.drawable.usth_avatar));
        items.add(new GroupItem("Group",  "10 posts recently", R.drawable.usth_avatar));

        filteredItems.addAll(items);

        adapter = new GroupAdapter(this, filteredItems);
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
