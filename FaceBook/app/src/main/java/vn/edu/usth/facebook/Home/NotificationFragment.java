package vn.edu.usth.facebook.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import vn.edu.usth.facebook.R;

public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        ImageButton searchbutton = v.findViewById(R.id.home_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Search_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout morefriend = v.findViewById(R.id.more_friend);
        morefriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.List_Add_Friend_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend1 = v.findViewById(R.id.friend1);
        my_friend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Not_Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend2 = v.findViewById(R.id.friend2);
        my_friend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Not_Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend3 = v.findViewById(R.id.friend3);
        my_friend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Not_Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        return v;
    }
}