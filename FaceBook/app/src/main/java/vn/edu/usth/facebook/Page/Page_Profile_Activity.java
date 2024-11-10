package vn.edu.usth.facebook.Page;

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

import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.Search.SearchActivity;

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
        
        setUpButton();

    }

    private void setUpButton() {
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        LinearLayout create_post = findViewById(R.id.create_post);
        create_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Page_Profile_Activity.this, vn.edu.usth.facebook.More.Create_Post_Activity.class );
                startActivity(i);
                finish();
            }
        });

        ImageButton searchbutton = findViewById(R.id.page_search_button);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Page_Profile_Activity.this, SearchActivity.class );
                startActivity(i);
                finish();
            }
        });

        LinearLayout page_about = findViewById(R.id.page_about);
        page_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Page_Profile_Activity.this, About_Page_Activity.class );
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}