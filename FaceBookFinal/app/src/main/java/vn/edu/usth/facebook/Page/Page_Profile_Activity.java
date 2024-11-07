package vn.edu.usth.facebook.Page;

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

public class Page_Profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page_profile);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewpagepost);

        // Create list of items
        List<PagePostItem> items = new ArrayList<PagePostItem>();
        items.add(new PagePostItem("Page User", "12h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.user, R.color.light_grey));
        items.add(new PagePostItem("Page User", "16h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.user, R.color.light_grey));
        items.add(new PagePostItem("Page User", "2d", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.user, R.color.light_grey));
        items.add(new PagePostItem("Page User", "5h", "Lorem ipsum text praesent tincidunt ipsum lipsum.", R.drawable.user, R.color.light_grey));

        // Set up the RecyclerView with a layout manager and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PagePostAdapter(this, items));


        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });


        ImageButton searchbutton = findViewById(R.id.page_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(vn.edu.usth.facebook.Page.Page_Profile_Activity.this, vn.edu.usth.facebook.Search.Search_Activity.class );
                startActivity(i);
            }
        });
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}