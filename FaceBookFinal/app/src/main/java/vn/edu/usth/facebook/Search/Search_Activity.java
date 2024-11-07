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

import vn.edu.usth.facebook.Page.ListPageAdapter;
import vn.edu.usth.facebook.Page.ListPageItem;
import vn.edu.usth.facebook.R;

public class Search_Activity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private UserSearchAdapter adapter;
    private List<UserSearchItem> items;
    private List<UserSearchItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        recyclerView = findViewById(R.id.recyclerviewsearch);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        items.add(new UserSearchItem("Kieu Quoc Viet", "USTH",  R.drawable.kqv));
        items.add(new UserSearchItem("Huynh Vinh Nam", "USTH",  R.drawable.hvn));
        items.add(new UserSearchItem("Le Hiep", "USTH",  R.drawable.lth));
        items.add(new UserSearchItem("Le Pham Hoang Lan", "USTH",  R.drawable.usth_avatar));
        items.add(new UserSearchItem("Nguyen Viet Anh", "USTH",  R.drawable.circle_avatar));
        items.add(new UserSearchItem("Nguyen At", "USTH",  R.drawable.at_avata));
        items.add(new UserSearchItem("Lam Chi Cuong", "USTH",  R.drawable.capybara_usth));
        items.add(new UserSearchItem("Pham Hoang Anh", "USTH",  R.drawable.hhp_avatar));
        items.add(new UserSearchItem("Pham Duc Viet", "USTH",  R.drawable.vdd_avatar));

        filteredItems.addAll(items);

        adapter = new UserSearchAdapter(this, filteredItems);
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

    }

    private void filterList(String text) {
        filteredItems.clear();
        for (UserSearchItem item : items) {
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