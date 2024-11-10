package vn.edu.usth.facebook.More;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import vn.edu.usth.facebook.R;

public class Create_Video_Activity extends AppCompatActivity {
    private ImageView avatarVideoPost; //
    private WebView post_video;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_video);

        post_video = findViewById(R.id.cover_web_post);
        avatarVideoPost = findViewById(R.id.avatar_video_post);

        SharedPreferences sharedPreferences = getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String avatarUrl = sharedPreferences.getString("avatarUrl", null);

        // Set up avatar image
        if (avatarUrl != null) {
            Glide.with(this)
                    .load(avatarUrl)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user)
                    .into(avatarVideoPost);
        }

        SharedPreferences videoPreferences = getSharedPreferences("PostPrefs", Context.MODE_PRIVATE);
        String videoUrl = videoPreferences.getString("videoUrl", null);
        if (videoUrl != null) {
            post_video.getSettings().setJavaScriptEnabled(true);
            post_video.setWebViewClient(new WebViewClient());
            post_video.loadUrl(videoUrl);
        } else {
            Toast.makeText(this, "No video URL found", Toast.LENGTH_SHORT).show();
        }

        setUpButton();

    }

    private void setUpButton(){
        Button upload = findViewById(R.id.post_button);
        upload.setOnClickListener(view -> {
            Intent intent = new Intent(Create_Video_Activity.this, vn.edu.usth.facebook.FaceBookActivity.class);
            startActivity(intent);
            finish();
        });

        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(view -> {
            onBackPressed();
        });

        LinearLayout phovid = findViewById(R.id.photo_video_upload);
        phovid.setOnClickListener(view -> {
            Intent i = new Intent(Create_Video_Activity.this, vn.edu.usth.facebook.More.Upload_Activity.class);
            startActivity(i);
            finish();
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}