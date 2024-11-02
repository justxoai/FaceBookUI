package vn.edu.usth.facebook.More;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.usth.facebook.R;

public class Not_Friend_Profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_not_friend_profile);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        ImageButton searchbutton = findViewById(R.id.search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.Search_Activity.class );
                startActivity(i);
            }
        });


        LinearLayout friends = findViewById(R.id.friends);
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.List_Friend_Activity.class );
                startActivity(i);
            }
        });


        LinearLayout my_friend = findViewById(R.id.friend0);
        my_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend1 = findViewById(R.id.friend1);
        my_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend2 = findViewById(R.id.friend2);
        my_friend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend3 = findViewById(R.id.friend3);
        my_friend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend4 = findViewById(R.id.friend4);
        my_friend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout my_friend5 = findViewById(R.id.friend5);
        my_friend5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Not_Friend_Profile_Activity.this, vn.edu.usth.facebook.More.Friend_Profile_Activity.class );
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}