package vn.edu.usth.facebook.Video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import vn.edu.usth.facebook.R;

public class VideoFragment extends Fragment {

    private RecyclerView recyclerView1, recyclerView2, recyclerView3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        // Initialize the RecyclerViews
        recyclerView1 = view.findViewById(R.id.recyclerviewvideo1);
        recyclerView2 = view.findViewById(R.id.recyclerviewvideo2);
        recyclerView3 = view.findViewById(R.id.recyclerviewvideo3);

        // Set LayoutManager for each RecyclerView
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));

        // Define video URLs for each RecyclerView
        String videoUrl1 = "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067";
        String videoUrl2 = "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067";
        String videoUrl3 = "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067";

        // Set up each RecyclerView with its own video URL
        VideoAdapter adapter1 = new VideoAdapter(getContext(), Collections.singletonList(videoUrl1));
        VideoAdapter adapter2 = new VideoAdapter(getContext(), Collections.singletonList(videoUrl2));
        VideoAdapter adapter3 = new VideoAdapter(getContext(), Collections.singletonList(videoUrl3));

        recyclerView1.setAdapter(adapter1);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);

        return view;
    }
}
