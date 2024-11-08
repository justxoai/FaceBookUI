package vn.edu.usth.facebook.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class HomeFragment extends Fragment {

    private List<HomeItem> items;
    private HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerviewhome);

        // Initialize list of items
        items = new ArrayList<>();
        items.add(new HomeItem("Grace Morgan", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.girl, R.drawable.bridge));
        items.add(new HomeItem("Isabella Lewis", "16h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.girl_mountain, R.drawable.woods));
        items.add(new HomeItem("Evelyn", "2d", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.closegirl, R.drawable.girl_hat));
        items.add(new HomeItem("Rag - Demi Store", "5h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.jean_store, R.drawable.jeans));

        // Retrieve uploaded image URL from SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String uploadedImageUrl = prefs.getString("imageUrl", null);

        if (uploadedImageUrl != null) {
            // Add new post with uploaded image URL if available
            items.add(0, new HomeItem("Just Xoai", "Just now", "Uploaded a new image", R.drawable.avatar_profile, uploadedImageUrl));
        }

        adapter = new HomeAdapter(requireContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        setupButtons(v);

        return v;
    }

    private void setupButtons(View v) {
        ImageButton chatbutton = v.findViewById(R.id.chat_button);
        chatbutton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Messenger.Messenger_Activity.class);
            startActivity(i);
        });

        ImageButton searchbutton = v.findViewById(R.id.home_search_button);
        searchbutton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Search.Search_Activity.class);
            startActivity(i);
        });

        ImageButton image_homebutton = v.findViewById(R.id.image_home_button);
        image_homebutton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Upload_Activity.class);
            startActivity(i);
        });

        LinearLayout createpost = v.findViewById(R.id.createPost);
        createpost.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Create_Post_Activity.class);
            startActivity(i);
        });
    }
}