package vn.edu.usth.facebook.More;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.response.user.UserAboutResponse;
import vn.edu.usth.facebook.retrofit.RetrofitService;
import vn.edu.usth.facebook.retrofit.UserAboutApi;

public class InfoActivity extends AppCompatActivity {
    private EditText firstName, lastName, email, phone, dateOfBirth, gender, userName, occupation,
            workPlace, educationLevel, school, dateOfJoining, relationshipStatus, locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);

        // Initialize the EditText fields
        firstName = findViewById(R.id.textFirstName);
        lastName = findViewById(R.id.textLastName);
        email = findViewById(R.id.textEmail);
        phone = findViewById(R.id.textPhone);
        dateOfBirth = findViewById(R.id.textDateOfBirth);
        gender = findViewById(R.id.textGender);
        userName = findViewById(R.id.textUserName);
        occupation = findViewById(R.id.textOccupation);
        workPlace = findViewById(R.id.textWorkPlace);
        educationLevel = findViewById(R.id.textEducationLevel);
        school = findViewById(R.id.textSchool);
        dateOfJoining = findViewById(R.id.textDateOfJoining);
        relationshipStatus = findViewById(R.id.textRelationshipStatus);
        locationName = findViewById(R.id.textLocation);

        fetchUserAboutInfo();

        setUpButton();
    }

    private void fetchUserAboutInfo() {
        RetrofitService retrofitService = new RetrofitService(this);
        UserAboutApi userAboutApi = retrofitService.getRetrofit().create(UserAboutApi.class);

        userAboutApi.getUserAboutByUser()
                .enqueue(new Callback<UserAboutResponse>() {
                    @Override
                    public void onResponse(Call<UserAboutResponse> call, Response<UserAboutResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            UserAboutResponse user = response.body();
                            firstName.setText(user.getFirstName());
                            lastName.setText(user.getLastName());
                            email.setText(user.getEmail());
                            phone.setText(user.getPhone());
                            dateOfBirth.setText(user.getDateOfBirth().toString());
                            gender.setText(user.getGender());
                            userName.setText(user.getUserName());
                            occupation.setText(user.getOccupation());
                            workPlace.setText(user.getWorkPlace());
                            educationLevel.setText(user.getEducationLevel());
                            school.setText(user.getSchool());
                            dateOfJoining.setText(user.getDateOfJoining().toString());
                            relationshipStatus.setText(user.getRelationshipStatus());
                            locationName.setText(user.getLocationName());
                        } else {
                            Toast.makeText(InfoActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAboutResponse> call, Throwable t) {
                        Toast.makeText(InfoActivity.this, "Failed to fetch data!!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InfoActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                });
    }

    private void setUpButton() {
        ImageButton closeButton = findViewById(R.id.close_button_edit);
        closeButton.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
