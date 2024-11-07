package vn.edu.usth.facebook.More;

import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.edu.usth.facebook.R;

public class Upload_Activity extends AppCompatActivity {

    private EditText image_etURL, video_etURL;
    private ImageView post_image;
    private WebView post_video;
    private Button show_image, show_video, upload;
    private Handler mainHandler= new Handler();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload);

        // Image URL
        image_etURL = findViewById(R.id.image_etURL);
        post_image = findViewById(R.id.cover_image);
        show_image = findViewById(R.id.image_show_button);

        // Video URL
        video_etURL = findViewById(R.id.video_etURL);
        post_video = findViewById(R.id.cover_web);
        show_video = findViewById(R.id.video_show_button);

        upload = findViewById(R.id.save_button);

        post_video.getSettings().setJavaScriptEnabled(true);
        post_video.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(Upload_Activity.this, "Failed to load URL", Toast.LENGTH_SHORT).show();
            }
        });

        // Image Show Button
        show_image.setOnClickListener(view -> {
            String url = image_etURL.getText().toString();
            if (!url.isEmpty()) {
                new LoadImage(url).start();
            } else {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
            }
        });

        // Video Show Button
        show_video.setOnClickListener(view -> {
            String url = video_etURL.getText().toString().trim();
            if (!url.isEmpty()) {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                post_video.loadUrl(url);
            } else {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
            }
        });

        // upload
        // Testing URL: image: https://i.imgur.com/sbDkwwu.jpeg and video: https://www.facebook.com/plugins/video.php?href=https%3A%2F%2Fwww.facebook.com%2Fvideo.php%3Fv%3D1531448284152067
        upload.setOnClickListener(view -> {
            String imageUrl = image_etURL.getText().toString();
            String videoUrl = video_etURL.getText().toString().trim();
            if(!imageUrl.isEmpty() && !videoUrl.isEmpty()){

                SharedPreferences image_sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE);
                SharedPreferences.Editor image_editor = image_sharedPreferences.edit();
                image_editor.putString("imageUrl", imageUrl);
                image_editor.apply();

                SharedPreferences video_sharedPreferences = getSharedPreferences("PostPrefs", MODE_PRIVATE);
                SharedPreferences.Editor video_editor = video_sharedPreferences.edit();
                video_editor.putString("videoUrl", videoUrl);
                video_editor.apply();

                Toast.makeText(this, "Image and Video URL saved", Toast.LENGTH_SHORT).show();

                // Navigate back to HomeFragment
                Intent intent = new Intent(Upload_Activity.this, vn.edu.usth.facebook.More.Create_Post_Activity.class);
                startActivity(intent);
                finish();
            }
            else if (!imageUrl.isEmpty()) {

                SharedPreferences sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("imageUrl", imageUrl);
                editor.apply();

                Toast.makeText(this, "Image URL saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Upload_Activity.this, vn.edu.usth.facebook.More.Create_Post_Activity.class);
                startActivity(intent);
                finish();
            }
            else if (!videoUrl.isEmpty()){

                SharedPreferences sharedPreferences = getSharedPreferences("PostPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("videoUrl", videoUrl);
                editor.apply();

                Toast.makeText(this, "Video URL saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Upload_Activity.this, vn.edu.usth.facebook.More.Create_Post_Activity.class);
                startActivity(intent);
                finish();
            }

            else {
                onBackPressed();
            }
        });


        ImageButton backButton = findViewById(R.id.close_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

//        Button save_button = findViewById(R.id.save_button);
//        save_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Upload_Activity.this, vn.edu.usth.facebook.More.Create_Post_Activity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    class LoadImage extends Thread {
        private String url;
        private Bitmap bitmap;

        public LoadImage(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            mainHandler.post(() -> {
                progressDialog = new ProgressDialog(Upload_Activity.this);
                progressDialog.setMessage("Loading image...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            });

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

            mainHandler.post(() -> {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (bitmap != null) {
                    post_image.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(Upload_Activity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}