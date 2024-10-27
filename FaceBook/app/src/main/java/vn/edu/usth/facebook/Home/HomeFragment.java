package vn.edu.usth.facebook;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton chatbutton = v.findViewById(R.id.chat_button);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), Messenger_Activity.class );
                startActivity(i);
            }
        });

        ImageButton searchbutton = v.findViewById(R.id.home_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), Search_Activity.class );
                startActivity(i);
            }
        });

        ImageButton image_homebutton = v.findViewById(R.id.image_home_button);
        image_homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), Picture_Home_Activity.class );
                startActivity(i);
            }
        });

        return v;
    }
}