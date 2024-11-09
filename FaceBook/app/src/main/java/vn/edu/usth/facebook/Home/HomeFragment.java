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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.Search.SearchActivity;

public class HomeFragment extends Fragment {

    private List<HomeItem> items;
    private HomeAdapter adapter;
    private ImageView postAvatar, storyAvatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerviewhome);
        postAvatar = v.findViewById(R.id.create_post_avatar);
        storyAvatar = v.findViewById(R.id.create_story_avatar);

        // Get avatar and cover URLs from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String avatarUrl = sharedPreferences.getString("avatarUrl", null);

        // Initialize list of items
        items = new ArrayList<>();
        items.add(new HomeItem("Grace Morgan", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.girl, R.drawable.bridge));
        items.add(new HomeItem("Isabella Lewis", "16h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.girl_mountain, R.drawable.woods));
        items.add(new HomeItem("Evelyn", "2d", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.closegirl, R.drawable.girl_hat));
        items.add(new HomeItem("Rag - Demi Store", "5h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.jean_store, R.drawable.jeans));

        // Retrieve uploaded image and video URLs from SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String uploadedImageUrl = prefs.getString("imageUrl", null);
        SharedPreferences videoPreferences = requireContext().getSharedPreferences("PostPrefs", Context.MODE_PRIVATE);
        String uploadedVideoUrl = videoPreferences.getString("videoUrl", null);

        SharedPreferences postPrefs = requireContext().getSharedPreferences("PostPrefs", Context.MODE_PRIVATE);
        String postContent = postPrefs.getString("postContent", null);
        long postTimeMillis = postPrefs.getLong("postTime", -1);

        String timeAgo = "Just now";
        if (postTimeMillis != -1) {
            timeAgo = getTimeAgo(postTimeMillis);
        }
        if (postContent != null) {
            if (uploadedImageUrl != null) {
                if (uploadedVideoUrl != null) {
                    // Add new post with content, image, and video URLs if available
                    items.add(0, new HomeItem("Just Xoai", timeAgo, postContent, avatarUrl, uploadedImageUrl, uploadedVideoUrl));
                } else {
                    // Add new post with content and only image URL if no video
                    items.add(0, new HomeItem("Just Xoai", timeAgo, postContent, avatarUrl, uploadedImageUrl));
                }
            } else {
                // Add new post with content but no image or video
                items.add(0, new HomeItem("Just Xoai", timeAgo, postContent, avatarUrl));
            }
        }

        adapter = new HomeAdapter(requireContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        // Load images using Glide
        if (avatarUrl != null) {
            Glide.with(this)
                    .load(avatarUrl)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user)
                    .into(postAvatar);

            Glide.with(this)
                    .load(avatarUrl)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user)
                    .into(storyAvatar);
        }

        setupButtons(v);

        return v;
    }

    private String getTimeAgo(long postTimeMillis) {
        long currentTimeMillis = System.currentTimeMillis();
        long timeDifference = currentTimeMillis - postTimeMillis;

        // Convert time difference to seconds, minutes, hours, etc.
        long seconds = timeDifference / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (minutes < 60) {
            return minutes + " minutes ago";
        } else if (hours < 24) {
            return hours + " hours ago";
        } else {
            return days + " days ago";
        }
    }

    private void setupButtons(View v) {
        ImageButton chatButton = v.findViewById(R.id.chat_button);
        chatButton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Messenger.Messenger_Activity.class);
            startActivity(i);
        });

        ImageButton searchButton = v.findViewById(R.id.home_search_button);
        searchButton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), SearchActivity.class);
            startActivity(i);
        });

        ImageButton imageHomeButton = v.findViewById(R.id.image_home_button);
        imageHomeButton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Upload_Activity.class);
            startActivity(i);
        });

        LinearLayout createPost = v.findViewById(R.id.createPost);
        createPost.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Create_Post_Activity.class);
            startActivity(i);
        });
    }
}

