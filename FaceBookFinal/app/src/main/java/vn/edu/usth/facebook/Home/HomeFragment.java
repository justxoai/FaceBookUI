package vn.edu.usth.facebook.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
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

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview1);

        // Create and populate the list of items
        List<HomeItem> items = new ArrayList<HomeItem>();
        items.add(new HomeItem("VietAnh", "13h50", R.drawable.vid_1));
        items.add(new HomeItem("XoaiLol", "12h36", R.drawable.vid_2));

        // Set up the RecyclerView with a layout manager and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new MyAdapter(requireContext(), items));

        // Set up the other buttons and their click listeners
        ImageButton chatbutton = v.findViewById(R.id.chat_button);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Messenger.Messenger_Activity.class);
                startActivity(i);
            }
        });

        ImageButton searchbutton = v.findViewById(R.id.home_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Search_Activity.class);
                startActivity(i);
            }
        });

        ImageButton image_homebutton = v.findViewById(R.id.image_home_button);
        image_homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Picture_Home_Activity.class);
                startActivity(i);
            }
        });

        LinearLayout createpost = v.findViewById(R.id.createPost);
        createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Create_Post_Activity.class);
                startActivity(i);
            }
        });

        return v;
    }
}
