package vn.edu.usth.facebook.Group;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class InvitePeople_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invite_people);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_invitepeople);

        List<InvitePeopleItem> items = new ArrayList<InvitePeopleItem>();

        items.add(new InvitePeopleItem("USTH", R.drawable.usth_avatar));
        items.add(new InvitePeopleItem("Group", R.drawable.usth_avatar));
        items.add(new InvitePeopleItem("USTH", R.drawable.usth_avatar));
        items.add(new InvitePeopleItem("Group", R.drawable.usth_avatar));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new InvitePeopleAdapter(this,items));

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