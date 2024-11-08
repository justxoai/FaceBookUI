package vn.edu.usth.facebook.Video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.content.SharedPreferences;

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

public class VideoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        RecyclerView recyclerview1 = v.findViewById(R.id.recyclerviewvideo1);
        RecyclerView recyclerview2 = v.findViewById(R.id.recyclerviewvideo2);
        RecyclerView recyclerview3 = v.findViewById(R.id.recyclerviewvideo3);

        List<VideoItem> item1s = new ArrayList<VideoItem>();
        item1s.add(new VideoItem("Review Phim", "7 days ago", "Review phim hot nhất 2019: Nghề siêu khó", R.drawable.vid_ava_1, "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067"));
        item1s.add(new VideoItem("Review Netflix", "8 days ago", "Bộ phim gây chấn động 2018: Ảo ảnh", R.drawable.vid_ava_2, "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067"));

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("PostPrefs", getContext().MODE_PRIVATE);
        String videoUrl = sharedPreferences.getString("videoUrl", null);
        String postContent = sharedPreferences.getString("postContent", null);
        long postTimeMillis = sharedPreferences.getLong("postTime", -1);

        String timeAgo = "Just now";
        if (postTimeMillis != -1) {
            timeAgo = getTimeAgo(postTimeMillis);
        }
        if (videoUrl != null) {
            item1s.add(0, new VideoItem("Just Xoai", timeAgo, postContent, R.drawable.capybara_usth, videoUrl));
        } else {
            item1s.add(0, new VideoItem("Just Xoai", "3 days ago", "Hello mọi người", R.drawable.capybara_usth, "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067"));
        }

        recyclerview1.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerview1.setAdapter(new VideoAdapter(requireContext(), item1s));

        List<VideoItem> item2s = new ArrayList<VideoItem>();
        item2s.add(new VideoItem("Javalorant", "15 days ago", "Valorant hightlight #6", R.drawable.vid_ava_3, "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067"));
        item2s.add(new VideoItem("CSSGO", "19 days ago", "Quan Trọng Là Tốc Độ Nhưng Là CS2", R.drawable.vid_ava_4, "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067"));

        recyclerview2.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerview2.setAdapter(new VideoAdapter(requireContext(), item2s));

        List<VideoItem> item3s = new ArrayList<VideoItem>();
        item3s.add(new VideoItem("500Bros CS2", "1 days ago", "", R.drawable.vid_ava_4, "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067"));

        recyclerview3.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerview3.setAdapter(new VideoAdapter(requireContext(), item3s));

        setUpButton(v);

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

    private void setUpButton(View v) {
        ImageButton searchbutton = v.findViewById(R.id.search_video_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Video_Search_Activity.class);
                startActivity(i);
            }
        });

        ImageButton savebutton = v.findViewById(R.id.save_video_button);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Video_Save_Activity.class);
                startActivity(i);
            }
        });
    }
}