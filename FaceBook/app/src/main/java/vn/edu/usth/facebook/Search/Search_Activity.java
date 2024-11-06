package vn.edu.usth.facebook.Search;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.facebook.R;

public class Search_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewsearch);
        List<UserSearchItem> items = new ArrayList<UserSearchItem>();
        items.add(new UserSearchItem("Kieu Quoc Viet", "USTH",  R.drawable.kqv));
        items.add(new UserSearchItem("Huynh Vinh Nam", "USTH",  R.drawable.hvn));
        items.add(new UserSearchItem("Le Hiep", "USTH",  R.drawable.lth));
        items.add(new UserSearchItem("Le Pham Hoang Lan", "USTH",  R.drawable.usth_avatar));
        items.add(new UserSearchItem("Nguyen Viet Anh", "USTH",  R.drawable.circle_avatar));
        items.add(new UserSearchItem("Nguyen At", "USTH",  R.drawable.at_avata));
        items.add(new UserSearchItem("Lam Chi Cuong", "USTH",  R.drawable.capybara_usth));
        items.add(new UserSearchItem("Pham Hoang Anh", "USTH",  R.drawable.hhp_avatar));
        items.add(new UserSearchItem("Pham Duc Viet", "USTH",  R.drawable.vdd_avatar));


        // Set up the RecyclerView with a layout manager and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserSearchAdapter(this, items));


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