package vn.edu.usth.facebook;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Messenger_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messenger);

        LinearLayout back = findViewById(R.id.back_button);
        back.setOnClickListener(view -> {
            onBackPressed();
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}