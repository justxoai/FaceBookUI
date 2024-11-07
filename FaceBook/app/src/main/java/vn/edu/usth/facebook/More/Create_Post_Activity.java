package vn.edu.usth.facebook.More;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.edu.usth.facebook.R;

public class Create_Post_Activity extends AppCompatActivity {

    private ImageView post_image;
    private WebView post_video;
    private Handler mainHandler = new Handler();
    private ProgressDialog progressDialog;

    private ImageView avatarImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // Image and video views
        post_image = findViewById(R.id.cover_image_post);
        post_video = findViewById(R.id.cover_web);

        avatarImageView = findViewById(R.id.cover_image_post);

        Button upload = findViewById(R.id.post_button);

        // Load image URL from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String imageUrl = sharedPreferences.getString("imageUrl", null);
        if (imageUrl != null) {
            new FetchImage(imageUrl).start();
        }

        // Load video URL from SharedPreferences and display in WebView
        SharedPreferences videoPreferences = getSharedPreferences("PostPrefs", Context.MODE_PRIVATE);
        String videoUrl = videoPreferences.getString("videoUrl", null);
        if (videoUrl != null) {
            post_video.getSettings().setJavaScriptEnabled(true);
            post_video.setWebViewClient(new WebViewClient());
            post_video.loadUrl(videoUrl);
        } else {
            Toast.makeText(this, "No video URL found", Toast.LENGTH_SHORT).show();
        }

        // Upload button listener
        upload.setOnClickListener(view -> {
            Intent intent = new Intent(Create_Post_Activity.this, vn.edu.usth.facebook.FaceBookActivity.class);
            startActivity(intent);
            finish();
        });

        // Close button listener
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(view -> onBackPressed());

        // Navigate to Upload_Activity
        LinearLayout phovid = findViewById(R.id.photo_video_upload);
        phovid.setOnClickListener(view -> {
            Intent intent = new Intent(Create_Post_Activity.this, vn.edu.usth.facebook.More.Upload_Activity.class);
            startActivity(intent);
        });
    }

    // Fetch image in a separate thread
    class FetchImage extends Thread {
        private String url;
        private Bitmap bitmap;

        public FetchImage(String url) {
            this.url = url;
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

            runOnUiThread(() -> {
                if (bitmap != null) {
                    avatarImageView.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(Create_Post_Activity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
