package vn.edu.usth.facebook.More;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.facebook.R;

public class Create_Video_Activity extends AppCompatActivity {

    private WebView post_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_video);

        post_video = findViewById(R.id.cover_web_post);

        Button upload = findViewById(R.id.post_button);

        SharedPreferences videoPreferences = getSharedPreferences("PostPrefs", Context.MODE_PRIVATE);
        String videoUrl = videoPreferences.getString("videoUrl", null);
        if (videoUrl != null) {
            post_video.getSettings().setJavaScriptEnabled(true);
            post_video.setWebViewClient(new WebViewClient());
            post_video.loadUrl(videoUrl);
        } else {
            Toast.makeText(this, "No video URL found", Toast.LENGTH_SHORT).show();
        }

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
            Intent intent = new Intent(Create_Video_Activity.this, vn.edu.usth.facebook.More.Upload_Activity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}