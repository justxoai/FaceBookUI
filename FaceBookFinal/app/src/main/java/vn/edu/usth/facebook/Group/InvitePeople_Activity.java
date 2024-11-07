package vn.edu.usth.facebook.Group;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class InvitePeople_Activity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private InvitePeopleAdapter adapter;
    private List<InvitePeopleItem> items;
    private List<InvitePeopleItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_people);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        // Initialize RecyclerView and data list
        recyclerView = findViewById(R.id.recyclerview_invitepeople);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        // Add items to the list
        items.add(new InvitePeopleItem("USTH", R.drawable.usth_avatar));
        items.add(new InvitePeopleItem("Group", R.drawable.usth_avatar));
        items.add(new InvitePeopleItem("USTH", R.drawable.usth_avatar));
        items.add(new InvitePeopleItem("Group", R.drawable.usth_avatar));

        // Initially, the filtered list should contain all items
        filteredItems.addAll(items);

        // Set up the adapter with the filtered list
        adapter = new InvitePeopleAdapter(this, filteredItems);
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

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> onBackPressed());
    }

    private void filterList(String text) {
        filteredItems.clear();
        for (InvitePeopleItem item : items) {
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
