package vn.edu.usth.facebook.Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class List_Page_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_page);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewlistpage);

        List<ListPageItem> items = new ArrayList<ListPageItem>();
        items.add(new ListPageItem("Nature Exploring",  R.drawable.bridge));
        items.add(new ListPageItem("Love from Nature",  R.drawable.woods));
        items.add(new ListPageItem("Beauty of the Sea",  R.drawable.girl_hat));
        items.add(new ListPageItem("Rag - Demi Store",  R.drawable.jeans));
        items.add(new ListPageItem("Capybara",  R.drawable.capybara_usth));
        items.add(new ListPageItem("USTH",  R.drawable.usth_avatar));
        items.add(new ListPageItem("12A1.1",  R.drawable.a1_1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListPageAdapter(this, items));

        ImageButton back_button = findViewById(R.id.page_back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });

        LinearLayout page_profile = findViewById(R.id.page_profile);
        page_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(vn.edu.usth.facebook.Page.List_Page_Activity.this, vn.edu.usth.facebook.Page.Page_Profile_Activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}