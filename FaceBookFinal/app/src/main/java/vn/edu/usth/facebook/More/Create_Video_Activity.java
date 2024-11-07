package vn.edu.usth.facebook.More;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.facebook.R;

public class Create_Video_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_video);

        LinearLayout upload = findViewById(R.id.video_upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Create_Video_Activity.this, Upload_Activity.class);
                startActivity(i);
            }
        });

        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}