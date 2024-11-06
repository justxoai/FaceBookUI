package vn.edu.usth.facebook.Group;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.facebook.R;

public class CreateGroup_Activity extends AppCompatActivity {

    private Spinner privacySpinner;
    private Button buttonCreateGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        buttonCreateGroup = findViewById(R.id.creategroup_button);

        // Set the click listener to open GroupProfileActivity
        buttonCreateGroup.setOnClickListener(view -> {
            Intent i = new Intent(CreateGroup_Activity.this, GroupProfile_Activity.class);
            startActivity(i);
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        privacySpinner = findViewById(R.id.privacy);

        // Log to verify spinner initialization
        if (privacySpinner == null) {
            Log.e("CreateGroup_Activity", "Spinner is null!");
        } else {
            Log.d("CreateGroup_Activity", "Spinner found and initialized");
        }

        privacySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = parent.getItemAtPosition(position).toString();
                Toast.makeText(CreateGroup_Activity.this,
                        "Selected: " + selectedType,
                        Toast.LENGTH_SHORT).show();
                Log.d("CreateGroup_Activity", "Selected type: " + selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("CreateGroup_Activity", "Nothing selected in spinner");
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}