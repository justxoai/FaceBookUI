package vn.edu.usth.facebook.Menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import vn.edu.usth.facebook.FaceBookActivity;
import vn.edu.usth.facebook.R;

public class MenuFragment extends Fragment {

    private ImageView avatarMenu; //

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        avatarMenu = v.findViewById(R.id.menu_avatar);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String avatarUrl = sharedPreferences.getString("avatarUrl", null);

        // Set up avatar image
        if (avatarUrl != null) {
            Glide.with(this)
                    .load(avatarUrl)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user)
                    .into(avatarMenu);
        }

        setUpButton(v);

        return v;
    }

    private void setUpButton(View v){
        ImageButton searchbutton = v.findViewById(R.id.home_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Search.Search_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout logout_button = v.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment loginFragment = new vn.edu.usth.facebook.Login.LoginFragment();
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(android.R.id.content, loginFragment);
                fragmentTransaction.commit();

            }
        });

        LinearLayout to_video_page = v.findViewById(R.id.menu_to_video_page);
        to_video_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity() instanceof FaceBookActivity){
                    ((FaceBookActivity) getActivity()).to_video_page();
                }
            }
        });

        LinearLayout to_list_add_friend = v.findViewById(R.id.menu_to_friend_page);
        to_list_add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.List_Add_Friend_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout to_pages_list = v.findViewById(R.id.menu_to_listpage);
        to_pages_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Page.List_Page_Activity.class);
                startActivity(i);
            }
        });

        LinearLayout to_group_list = v.findViewById(R.id.menu_to_group_page);
        to_group_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Group.ListGroup_Activity.class );
                startActivity(i);
            }
        });
    }
}