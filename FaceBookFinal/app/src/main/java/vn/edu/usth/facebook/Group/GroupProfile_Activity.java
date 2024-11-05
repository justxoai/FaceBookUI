package vn.edu.usth.facebook.Group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import vn.edu.usth.facebook.Home.HomeItem;
import vn.edu.usth.facebook.R;

public class GroupProfile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_profile);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewgroup_02);

        List<GroupProfileItem> items = new ArrayList<GroupProfileItem>();

        items.add(new GroupProfileItem("Grace Morgan", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.girl, R.drawable.bridge));
        items.add(new GroupProfileItem("Isabella Lewis", "16h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.girl_mountain, R.drawable.woods));
        items.add(new GroupProfileItem("Evelyn", "2d", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.closegirl, R.drawable.girl_hat));
        items.add(new GroupProfileItem("Rag - Demi Store", "5h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.jean_store, R.drawable.jeans));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GroupProfileAdapter(this,items));

        LinearLayout inviteFriend = findViewById(R.id.invitefriend_button);
        inviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupProfile_Activity.this, InvitePeople_Activity.class);
                startActivity(i);
            }
        });


    }
}