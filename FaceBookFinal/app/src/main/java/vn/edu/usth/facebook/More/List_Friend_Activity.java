package vn.edu.usth.facebook.More;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.facebook.R;

public class List_Friend_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_friend);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        ImageButton addfriend = findViewById(R.id.add_friend);
        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(List_Friend_Activity.this, List_Add_Friend_Activity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}