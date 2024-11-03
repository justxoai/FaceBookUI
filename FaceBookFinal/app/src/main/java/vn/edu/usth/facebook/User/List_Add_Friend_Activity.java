package vn.edu.usth.facebook.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class List_Add_Friend_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_add_friend);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewaddfriend1);

        List<NotFriendItem> items = new ArrayList<NotFriendItem>();

        items.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));
        items.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));
        items.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));
        items.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NotFriendAdapter(this, items));



        RecyclerView recyclerView2 = findViewById(R.id.recyclerviewaddfriend2);

        List<NotFriendItem> item2s = new ArrayList<NotFriendItem>();

        item2s.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));
        item2s.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));
        item2s.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));
        item2s.add(new NotFriendItem("User", "x_mutal_friend", R.drawable.user, R.drawable.user));

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(new NotFriendAdapter(this, item2s));














        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        ImageButton searchbutton = findViewById(R.id.search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(List_Add_Friend_Activity.this, vn.edu.usth.facebook.More.Search_Activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}