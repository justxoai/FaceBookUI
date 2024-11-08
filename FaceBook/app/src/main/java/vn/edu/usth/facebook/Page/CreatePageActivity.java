package vn.edu.usth.facebook.Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.facebook.R;

public class CreatePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_page);
        setUpButton();
    }
    private void setUpButton(){
        Button createPage = findViewById(R.id.createpage_button);
        createPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreatePageActivity.this, vn.edu.usth.facebook.Page.Page_Profile_Activity.class);
                startActivity(i);
                finish();
            }
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