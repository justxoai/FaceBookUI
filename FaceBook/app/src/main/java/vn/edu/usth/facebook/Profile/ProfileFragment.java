package vn.edu.usth.facebook.Profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView recyclerview = v.findViewById(R.id.recyclerviewprofile);

        // Create list of items
        List<ProfileItem> items = new ArrayList<ProfileItem>();
        items.add(new ProfileItem("JustXoai updated his profile picture", "12h", "", R.drawable.avatar_profile, R.drawable.avatar_profile));
        items.add(new ProfileItem("JustXoai updated his cover picture", "16h", "", R.drawable.avatar_profile, R.drawable.background_profile));
        items.add(new ProfileItem("JustXoai", "2d", "1 chut dang yeu", R.drawable.avatar_profile, R.drawable.a1_1));
        items.add(new ProfileItem("JustXoai", "5h", "Not a bug",R.drawable.avatar_profile, R.drawable.meme));

        // Set up the RecyclerView with a layout manager and adapter
        recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerview.setAdapter(new ProfileAdapter(requireContext(), items));

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
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Picture.Picture_Home_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout find_friend = v.findViewById(R.id.find_friend);
        find_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.List_Add_Friend_Activity.class );
                startActivity(i);
            }
        });


        LinearLayout friend = v.findViewById(R.id.friends);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.List_Friend_Activity.class );
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
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend1 = v.findViewById(R.id.friend1);
        my_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend2 = v.findViewById(R.id.friend2);
        my_friend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend3 = v.findViewById(R.id.friend3);
        my_friend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend4 = v.findViewById(R.id.friend4);
        my_friend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend5 = v.findViewById(R.id.friend5);
        my_friend5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        return v;
    }
}