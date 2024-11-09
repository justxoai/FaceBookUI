package vn.edu.usth.facebook.More;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.FaceBookActivity;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.user.PostRequest;
import vn.edu.usth.facebook.model.response.user.PostResponse;
import vn.edu.usth.facebook.retrofit.PostApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class Create_Post_Activity extends AppCompatActivity {

    private EditText contentEditText;  // The EditText to input post content
    private ImageView post_image, createPostAvatar; //
    private WebView post_video;
    private Handler mainHandler = new Handler();
    private ProgressDialog progressDialog;
    private ImageView avatarImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // Initialize views
        contentEditText = findViewById(R.id.content_post);
        post_image = findViewById(R.id.cover_image_post);
        createPostAvatar = findViewById(R.id.avatar_create_post);
        post_video = findViewById(R.id.cover_web_post);
        avatarImageView = findViewById(R.id.cover_image_post);

        SharedPreferences sharedPreferences = getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String avatarUrl = sharedPreferences.getString("avatarUrl", null);

        // Set up avatar image
        if (avatarUrl != null) {
            Glide.with(this)
                    .load(avatarUrl)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.user)
                    .into(createPostAvatar);
        }

        // Load image and video URLs from SharedPreferences (already in place)
        loadMediaFromSharedPreferences();

        // Set up post button
        setUpButton();
    }

    private void loadMediaFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
        String imageUrl = sharedPreferences.getString("imageUrl", null);
        if (imageUrl != null) {
            new FetchImage(imageUrl).start();
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

    private void setUpButton() {
        // Post button listener
        Button postButton = findViewById(R.id.post_button);
        postButton.setOnClickListener(view -> {
            String content = contentEditText.getText().toString().trim();

            if (!content.isEmpty()) {
                // Create PostRequest object
                PostRequest postRequest = new PostRequest();
                postRequest.setContent(content);

                // Get the image URL from SharedPreferences
                SharedPreferences imagePreferences = getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
                String imageUrl = imagePreferences.getString("imageUrl", null);
                List<String> imageUrls = new ArrayList<String>();
                if(imageUrl!= null) imageUrls.add(imageUrl);
                postRequest.setImageUrl(imageUrls);

                // Get the video URL from SharedPreferences
                SharedPreferences videoPreferences = getSharedPreferences("PostPrefs", Context.MODE_PRIVATE);
                String videoUrl = videoPreferences.getString("videoUrl", null);
                List<String> videoUrls = new ArrayList<String>();
                if(videoUrl != null) videoUrls.add(videoUrl);
                postRequest.setVideoUrl(videoUrls);

                postRequest.setPostStatus("Published");

                // Create the post using Retrofit
                createPost(postRequest);
            } else {
                // Handle case where content is empty
                Toast.makeText(Create_Post_Activity.this, "Please enter content for the post", Toast.LENGTH_SHORT).show();
            }
        });

        // Close button listener
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(view -> onBackPressed());

        // Navigate to Upload_Activity
        LinearLayout photoVideoUpload = findViewById(R.id.photo_video_upload);
        photoVideoUpload.setOnClickListener(view -> {
            Intent intent = new Intent(Create_Post_Activity.this, Upload_Activity.class);
            startActivity(intent);
        });
    }


    private void createPost(PostRequest postRequest) {
        RetrofitService retrofitService = new RetrofitService(this);
        PostApi postApi = retrofitService.getRetrofit().create(PostApi.class);

        postApi.createPost(postRequest).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PostResponse postResponse = response.body();
                    Toast.makeText(Create_Post_Activity.this, "Post published", Toast.LENGTH_SHORT).show();

                    // Save the current time (timestamp) when the post is created
                    long currentTimeMillis = System.currentTimeMillis();
                    SharedPreferences sharedPreferences = getSharedPreferences("PostPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putLong("postTime", currentTimeMillis);  // Save timestamp
                    editor.apply();

                    // Save content to SharedPreferences
                    editor.putString("postContent", postRequest.getContent());
                    editor.apply();

                    Intent intent = new Intent(Create_Post_Activity.this, FaceBookActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Create_Post_Activity.this, "Failed to publish post", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Toast.makeText(Create_Post_Activity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        String content = contentEditText.getText().toString().trim();

        if (!content.isEmpty()) {
            PostRequest postRequest = new PostRequest();
            postRequest.setContent(content);

            SharedPreferences imagePreferences = getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE);
            String imageUrl = imagePreferences.getString("imageUrl", null);
            List<String> imageUrls = new ArrayList<String>();
            if(imageUrl!= null) imageUrls.add(imageUrl);
            postRequest.setImageUrl(imageUrls);

            SharedPreferences videoPreferences = getSharedPreferences("PostPrefs", Context.MODE_PRIVATE);
            String videoUrl = videoPreferences.getString("videoUrl", null);
            List<String> videoUrls = new ArrayList<String>();
            if(videoUrl != null) videoUrls.add(videoUrl);
            postRequest.setVideoUrl(videoUrls);

            postRequest.setPostStatus("Draft");

            createPost(postRequest);
        }
    }
}

