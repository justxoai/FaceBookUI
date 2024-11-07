package vn.edu.usth.facebook.Video;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class VideoFragment extends Fragment {

    private static final String PREFS_NAME = "PostPrefs";
    private static final String VIDEO_URL_KEY = "videoUrl";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        RecyclerView recyclerview1 = v.findViewById(R.id.recyclerviewvideo1);
        List<VideoItem> item1s = new ArrayList<>();

        item1s.add(new VideoItem("Review Phim", "7h", "Review phim hot nhất 2019: Nghề siêu khó", R.drawable.vid_ava_1, "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067"));

        // Lấy video URL từ SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        String videoUrl = sharedPreferences.getString(VIDEO_URL_KEY, null);

        if (videoUrl != null) {
            item1s.add(0, new VideoItem("User Uploaded Video", "Now", "User's recent upload", R.drawable.vid_ava_1, videoUrl));
        }

        recyclerview1.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerview1.setAdapter(new VideoAdapter(requireContext(), item1s));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(VIDEO_URL_KEY);
        editor.apply();

        return v;
    }
}
