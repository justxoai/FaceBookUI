package vn.edu.usth.facebook.Group;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.group.GroupRequest;
import vn.edu.usth.facebook.model.response.group.GroupResponse;
import vn.edu.usth.facebook.retrofit.GroupApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class CreateGroup_Activity extends AppCompatActivity {

    private Button buttonCreateGroup;
    private EditText editTextTitle;
    private EditText editTextBackgroundImageUrl;
    private EditText editTextDescription;
    private RetrofitService retrofitService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        editTextTitle = findViewById(R.id.group_name);
        editTextBackgroundImageUrl = findViewById(R.id.groupBackgroundImage);
        editTextDescription = findViewById(R.id.groupDescription);
        buttonCreateGroup = findViewById(R.id.creategroup_button);

        retrofitService = new RetrofitService(this);

        setUpButton();

    }

    private void setUpButton(){
        buttonCreateGroup.setOnClickListener(view -> {
            // Get input from user
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();
            String backgroundImageUrl = editTextBackgroundImageUrl.getText().toString().trim();

            // Validate input
            if (title.isEmpty() || backgroundImageUrl.isEmpty() || description.isEmpty()) {
                Toast.makeText(CreateGroup_Activity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create GroupRequest object
            GroupRequest groupRequest = new GroupRequest();
            groupRequest.setTitle(title);
            groupRequest.setDescription(description);
            groupRequest.setBackgroundImageUrl(backgroundImageUrl);

            // Make API call to create group
            createGroup(groupRequest);
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void createGroup(GroupRequest groupRequest) {
        GroupApi groupApi = retrofitService.getRetrofit().create(GroupApi.class);
        Call<GroupResponse> call = groupApi.createGroup(groupRequest);

        call.enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle success
                    Toast.makeText(CreateGroup_Activity.this, "Group created successfully", Toast.LENGTH_SHORT).show();
                    // Navigate to the next activity or update UI as needed
                    Intent intent = new Intent(CreateGroup_Activity.this, GroupProfile_Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Handle failure
                    Toast.makeText(CreateGroup_Activity.this, "Failed to create group", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
                // Handle error
                Toast.makeText(CreateGroup_Activity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}