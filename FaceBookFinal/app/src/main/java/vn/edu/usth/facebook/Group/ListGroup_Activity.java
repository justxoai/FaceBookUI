package vn.edu.usth.facebook.Group;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.Notification.NotificationItem;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.User.NotFriendItem;

public class ListGroup_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewgroup01);

        List<GroupItem> items = new ArrayList<GroupItem>();


        items.add(new GroupItem("Group",  "12h53", R.drawable.usth_avatar));
        items.add(new GroupItem("Group",  "12h53", R.drawable.usth_avatar));
    }
}