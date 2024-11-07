package vn.edu.usth.facebook.Group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.FaceBookActivity;
import vn.edu.usth.facebook.R;

public class GroupProfile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_profile);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewgroup_02);

        List<GroupProfileItem> items = new ArrayList<GroupProfileItem>();

        items.add(new GroupProfileItem("Group Page", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.color.light_grey, R.color.light_grey));
        items.add(new GroupProfileItem("Group Page", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.color.light_grey, R.color.light_grey));
        items.add(new GroupProfileItem("Group Page", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.color.light_grey, R.color.light_grey));
        items.add(new GroupProfileItem("Group Page", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.color.light_grey, R.color.light_grey));
        items.add(new GroupProfileItem("Group Page", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.color.light_grey, R.color.light_grey));
        items.add(new GroupProfileItem("Group Page", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.color.light_grey, R.color.light_grey));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GroupProfileAdapter(this, items));

        LinearLayout invitefriend = findViewById(R.id.invitefriend_button);
        invitefriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupProfile_Activity.this, InvitePeople_Activity.class);
                startActivity(i);
            }
        });

        ImageButton closeButton = findViewById(R.id.close_button); // Fix the variable v -> use this correctly

        // Set an OnClickListener for the close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to go to FacebookActivity
                Intent intent = new Intent(GroupProfile_Activity.this, FaceBookActivity.class);
                startActivity(intent); // Start the FacebookActivity
                finish(); // Optionally close the current activity after navigating
            }
        });
    }
}