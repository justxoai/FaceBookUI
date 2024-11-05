package vn.edu.usth.facebook.Group;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import vn.edu.usth.facebook.Home.HomeAdapter;
import vn.edu.usth.facebook.R;

public class ListGroup_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_group);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewgroup_01);

        List<GroupItem> items = new ArrayList<GroupItem>();

        items.add(new GroupItem("USTH",  "8 posts recently", R.drawable.usth_avatar));
        items.add(new GroupItem("Group",  "10 posts recently", R.drawable.usth_avatar));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GroupAdapter(this,items));

        LinearLayout creategroup = findViewById(R.id.create_group);
        creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListGroup_Activity.this, CreateGroup_Activity.class);
                startActivity(i);
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