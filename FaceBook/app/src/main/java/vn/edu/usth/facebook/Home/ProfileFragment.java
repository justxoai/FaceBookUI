package vn.edu.usth.facebook.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import vn.edu.usth.facebook.R;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageButton searchbutton = v.findViewById(R.id.home_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Search_Activity.class );
                startActivity(i);
            }
        });

        ImageButton editprofilebutton = v.findViewById(R.id.edit_profile_button);
        editprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Edit_Profile_Activity.class );
                startActivity(i);
            }
        });

        CardView editprofile = v.findViewById(R.id.edit_profile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Edit_Profile_Activity.class );
                startActivity(i);
            }
        });

        CardView edit_profile = v.findViewById(R.id.edit_public_details);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Edit_Profile_Activity.class );
                startActivity(i);
            }
        });

        ImageButton image_profilebutton = v.findViewById(R.id.image_profile_button);
        image_profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Picture_Home_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout find_friend = v.findViewById(R.id.find_friend);
        find_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.List_Add_Friend_Activity.class );
                startActivity(i);
            }
        });


        LinearLayout friend = v.findViewById(R.id.friends);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.List_Friend_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout createpost = v.findViewById(R.id.create_post);
        createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Create_Post_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend = v.findViewById(R.id.friend0);
        my_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend1 = v.findViewById(R.id.friend1);
        my_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend2 = v.findViewById(R.id.friend2);
        my_friend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend3 = v.findViewById(R.id.friend3);
        my_friend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend4 = v.findViewById(R.id.friend4);
        my_friend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend5 = v.findViewById(R.id.friend5);
        my_friend5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        return v;
    }
}