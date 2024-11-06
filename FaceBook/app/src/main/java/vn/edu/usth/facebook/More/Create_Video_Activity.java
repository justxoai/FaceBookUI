package vn.edu.usth.facebook.More;

import android.os.Bundle;

import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


import vn.edu.usth.facebook.R;

public class Create_Video_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_video);


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