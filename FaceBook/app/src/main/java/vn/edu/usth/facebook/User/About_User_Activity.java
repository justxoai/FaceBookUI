package vn.edu.usth.facebook.User;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.usth.facebook.R;

public class About_User_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_user);

        setUpButton();
    }

    private void setUpButton() {
        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}