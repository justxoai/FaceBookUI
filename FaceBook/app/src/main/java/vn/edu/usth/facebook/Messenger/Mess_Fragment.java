package vn.edu.usth.facebook.Messenger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.Search.UserSearchAdapter;
import vn.edu.usth.facebook.Search.UserSearchItem;

public class Mess_Fragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private BoxChatAdapter adapter;
    private List<BoxChatItem> items;
    private List<BoxChatItem> filteredItems;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mess_, container, false);

        searchView = v.findViewById(R.id.searchView);
        searchView.clearFocus();

        recyclerView = v.findViewById(R.id.recyclerviewboxchat);
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();

        items.add(new BoxChatItem("Vu Duc Duy", "22BI13127",  R.drawable.vdd_avatar));
        items.add(new BoxChatItem("Nguyen Viet Anh", "22BI13032",  R.drawable.circle_avatar));
        items.add(new BoxChatItem("Nguyen At", "22BI13047",  R.drawable.at_avata));
        items.add(new BoxChatItem("Pham Hoang Anh", "22BI13034",  R.drawable.hhp_avatar));
        items.add(new BoxChatItem("Lam Chi Cuong", "22BI13068",  R.drawable.capybara_usth));

        filteredItems.addAll(items);

        adapter = new BoxChatAdapter(requireContext(), filteredItems);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
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

        return v;
    }

    private void filterList(String text) {
        filteredItems.clear();
        for (BoxChatItem item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        if (filteredItems.isEmpty()) {
            Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
    }
}