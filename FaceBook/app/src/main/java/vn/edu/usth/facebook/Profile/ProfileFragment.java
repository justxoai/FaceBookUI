package vn.edu.usth.facebook.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.More.InfoActivity;
import vn.edu.usth.facebook.More.Upload_Activity;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.User.ListAddFriendActivity;

import android.text.format.DateUtils;

public class ProfileFragment extends Fragment {

    private ImageView avatarProfileImageView, coverImageView, avatarImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        avatarImageView = v.findViewById(R.id.avatar_image_post);
        avatarProfileImageView = v.findViewById(R.id.avatar_image_profile);
        coverImageView = v.findViewById(R.id.cover_image_profile);

        RecyclerView recyclerview = v.findViewById(R.id.recyclerviewprofile);

        // Lấy URL ảnh avatar và ảnh cover từ SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String avatarUrl = sharedPreferences.getString("avatarUrl", null);
        String coverUrl = sharedPreferences.getString("coverUrl", null);
        long avatarUpdateTime = sharedPreferences.getLong("avatarUpdateTime", -1);
        long coverUpdateTime = sharedPreferences.getLong("coverUpdateTime", -1);

        String avatarTimeAgo = "";
        String coverTimeAgo = "";

        if (avatarUpdateTime != -1) {
            avatarTimeAgo = DateUtils.getRelativeTimeSpanString(avatarUpdateTime, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
        }

        if (coverUpdateTime != -1) {
            coverTimeAgo = DateUtils.getRelativeTimeSpanString(coverUpdateTime, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
        }

        List<ProfileItem> items = new ArrayList<>();
        items.add(new ProfileItem("JustXoai", avatarTimeAgo, "", avatarUrl, avatarUrl));
        items.add(new ProfileItem("JustXoai", coverTimeAgo, "", avatarUrl, coverUrl));
        items.add(new ProfileItem("JustXoai", "2 days ago", "1 chut dang yeu", avatarUrl, "https://cdn.nguyenkimmall.com/images/companies/_1/stt-yeu-doi-5.jpg"));
        items.add(new ProfileItem("JustXoai", "5 days ago", "Not a bug", avatarUrl, "https://media.makeameme.org/created/its-not-a-2a30a4c44e.jpg"));

        recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerview.setAdapter(new ProfileAdapter(requireContext(), items));

        // Tải hình ảnh từ URL đã lưu
        if (avatarUrl != null) {
            new FetchImage(avatarUrl, avatarProfileImageView).start();
            new FetchImage(avatarUrl, avatarImageView).start();
        }
        if (coverUrl != null) {
            new FetchImage(coverUrl, coverImageView).start();
        }

        setUpButton(v);

        return v;
    }

    private void setUpButton (View v){
        CardView edit_profile = v.findViewById(R.id.edit_public_details);
        edit_profile.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Edit_Profile_Activity.class);
            startActivity(i);
        });

        ImageButton searchButton = v.findViewById(R.id.home_search_button);
        searchButton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.Search.Search_Activity.class);
            startActivity(i);
        });

        ImageButton editProfileButton = v.findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Edit_Profile_Activity.class);
            startActivity(i);
        });

        LinearLayout findFriend = v.findViewById(R.id.find_friend);
        findFriend.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), ListAddFriendActivity.class);
            startActivity(i);
        });

        LinearLayout friends = v.findViewById(R.id.friends);
        friends.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.List_Friend_Activity.class);
            startActivity(i);
        });

        ImageButton imageProfileButton = v.findViewById(R.id.image_profile_button);
        imageProfileButton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), Upload_Activity.class);
            startActivity(i);
        });

        LinearLayout myFriend = v.findViewById(R.id.friend0);
        myFriend.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), vn.edu.usth.facebook.User.Friend_Profile_Activity.class);
            startActivity(i);
        });

        ImageButton imageInfoButton = v.findViewById(R.id.seeInfoButton);
        imageInfoButton.setOnClickListener(view -> {
            Intent i = new Intent(requireContext(), InfoActivity.class);
            startActivity(i);
        });

    }

    class FetchImage extends Thread {
        private String url;
        private ImageView imageView;
        private Bitmap bitmap;

        public FetchImage(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        public void run() {
            try {
                URL imageUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (getActivity() != null) { // Ensure getActivity() is not null
                getActivity().runOnUiThread(() -> {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Toast.makeText(getActivity(), "Failed to load image", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}