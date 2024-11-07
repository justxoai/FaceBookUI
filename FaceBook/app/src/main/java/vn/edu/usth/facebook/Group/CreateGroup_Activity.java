package vn.edu.usth.facebook.Group;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.facebook.R;

public class CreateGroup_Activity extends AppCompatActivity {

    private Button buttonCreateGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        buttonCreateGroup = findViewById(R.id.creategroup_button);

        buttonCreateGroup.setOnClickListener(view -> {
            Intent i = new Intent(CreateGroup_Activity.this, GroupProfile_Activity.class);
            startActivity(i);
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}