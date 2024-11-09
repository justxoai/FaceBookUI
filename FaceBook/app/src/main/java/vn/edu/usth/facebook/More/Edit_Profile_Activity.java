package vn.edu.usth.facebook.More;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.user.UserAboutRequest;
import vn.edu.usth.facebook.model.response.user.UserAboutResponse;
import vn.edu.usth.facebook.retrofit.RetrofitService;
import vn.edu.usth.facebook.retrofit.api.UserAboutApi;

public class Edit_Profile_Activity extends AppCompatActivity {

    private EditText etProfileUrl, etAvatarUrl, etCoverUrl;
    private ImageView profileImage, avatarImage, coverImage;
    private Button fetchProfileButton, fetchAvatarButton, fetchCoverButton, saveButton;
    private Handler mainHandler = new Handler();
    private ProgressDialog progressDialog;
    private String education = "";
    private String relation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Set up the education level spinner
        Spinner spinnerEducation = findViewById(R.id.spinnerEducationInProfile);
        ArrayList<String> educationList = new ArrayList<>();
        educationList.add("Primary");
        educationList.add("Secondary");
        educationList.add("High School");
        educationList.add("Bachelor");
        educationList.add("Master");
        educationList.add("Doctorate");
        educationList.add("Other");

        ArrayAdapter<String> educationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, educationList);
        educationAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerEducation.setAdapter(educationAdapter);

        spinnerEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                education = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Edit_Profile_Activity.this, "Selected Education: " + education, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
        Spinner spinnerRelationship = findViewById(R.id.spinnerRelationshipInProfile);
        ArrayList<String> relationshipList = new ArrayList<>();
        relationshipList.add("Single");
        relationshipList.add("In_Relationship");
        relationshipList.add("Married");

        ArrayAdapter<String> relationshipAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, relationshipList);
        relationshipAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerRelationship.setAdapter(relationshipAdapter);

        // Set up item selection listener for relationship status
        spinnerRelationship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                relation = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Edit_Profile_Activity.this, "Selected Relationship: " + relation, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        initializeComponents();

        setUpButton();
    }

    private void initializeComponents() {
        // Setup Image
        etProfileUrl = findViewById(R.id.profileImageUrl);
        etAvatarUrl = findViewById(R.id.avatarImageUrl);
        etCoverUrl = findViewById(R.id.coverPhotoUrl);
        profileImage = findViewById(R.id.profile_image);
        avatarImage = findViewById(R.id.avatar_image);
        coverImage = findViewById(R.id.cover_image_profile);
        fetchProfileButton = findViewById(R.id.confirmProfileImageBtn);
        fetchAvatarButton = findViewById(R.id.confirmAvatarImageBtn);
        fetchCoverButton = findViewById(R.id.confirmCoverPhotoBtn);
        saveButton = findViewById(R.id.save_button);

        // Set up fetch buttons
        fetchProfileButton.setOnClickListener(view -> {
            String url = etProfileUrl.getText().toString();
            if (!url.isEmpty()) {
                new FetchImage(url, profileImage).start();
            }
        });

        fetchAvatarButton.setOnClickListener(view -> {
            String url = etAvatarUrl.getText().toString();
            if (!url.isEmpty()) {
                new FetchImage(url, avatarImage).start();
            }
        });

        fetchCoverButton.setOnClickListener(view -> {
            String url = etCoverUrl.getText().toString();
            if (!url.isEmpty()) {
                new FetchImage(url, coverImage).start();
            }
        });

        // Set up Bio
        EditText inputEditTextFirstName = findViewById(R.id.editFirstNameInProfile);
        EditText inputEditTextLastName = findViewById(R.id.editLastNameInProfile);
        EditText inputEditTextEmail = findViewById(R.id.editEmailInProfile);
        EditText inputEditTextPhone = findViewById(R.id.editPhoneInProfile);
        EditText inputEditTextDateOfBirth = findViewById(R.id.editDobInProfile);
        EditText inputEditTextDateOfJoining = findViewById(R.id.editDojInProfile);
        RadioButton radioButtonMale = findViewById(R.id.radioButtonMaleInProfile);
        RadioButton radioButtonFemale = findViewById(R.id.radioButtonFemaleInProfile);

        // Set up description
        EditText inputEditTextUserName = findViewById(R.id.editUserNameInProfile);
        EditText inputEditTextOccupation = findViewById(R.id.editOccupationInProfile);
        EditText inputEditTextWork = findViewById(R.id.editWorkspaceInProfile);
        EditText inputEditTextSchool = findViewById(R.id.editSchoolInProfile);
        EditText inputEditTextCity = findViewById(R.id.editCityInProfile);
        EditText inputEditTextCountry = findViewById(R.id.editCountryInProfile);
        EditText inputEditTextState = findViewById(R.id.editStateInProfile);
        EditText inputEditTextStreet = findViewById(R.id.editStreetInProfile);
        EditText inputEditTextRegion = findViewById(R.id.editRegionInProfile);

        RetrofitService retrofitService = new RetrofitService(this);
        UserAboutApi authenticationApi = retrofitService.getRetrofit().create(UserAboutApi.class);

        // Save URLs and update Profile
        saveButton.setOnClickListener(view -> {
            // Image
            String profileUrl = etProfileUrl.getText().toString();
            String avatarUrl = etAvatarUrl.getText().toString();
            String coverUrl = etCoverUrl.getText().toString();

            // Bio
            String firstName = String.valueOf(inputEditTextFirstName.getText());
            String lastName = String.valueOf(inputEditTextLastName.getText());
            String phone = String.valueOf(inputEditTextPhone.getText());
            String dateOfBirthInput = String.valueOf(inputEditTextDateOfBirth.getText());
            String email = String.valueOf(inputEditTextEmail.getText());
            String dojInput = String.valueOf(inputEditTextDateOfJoining.getText());
            String gender = "";
            if (radioButtonMale.isChecked()) {
                gender = "Male";
            } else if (radioButtonFemale.isChecked()) {
                gender = "Female";
            }

            // Description
            String userName = String.valueOf(inputEditTextUserName.getText());
            String occupation = String.valueOf(inputEditTextOccupation.getText());
            String work = String.valueOf(inputEditTextWork.getText());
            String school = String.valueOf(inputEditTextSchool.getText());
            String city = String.valueOf(inputEditTextCity.getText());
            String country = String.valueOf(inputEditTextCountry.getText());
            String state = String.valueOf(inputEditTextState.getText());
            String street = String.valueOf(inputEditTextStreet.getText());
            String region = String.valueOf(inputEditTextRegion.getText());

            // Create the object
            UserAboutRequest userAboutRequest = new UserAboutRequest();
            if (!profileUrl.isEmpty() && !avatarUrl.isEmpty() && !coverUrl.isEmpty()) {
                userAboutRequest.setProfilePhotoUrl(profileUrl);
                userAboutRequest.setAvatarPhotoUrl(avatarUrl);
                userAboutRequest.setBackgroundUrl(coverUrl);

                SharedPreferences sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("profileUrl", profileUrl);
                editor.putString("avatarUrl", avatarUrl);
                editor.putString("coverUrl", coverUrl);
                long currentTime = System.currentTimeMillis();
                editor.putLong("avatarUpdateTime", currentTime);
                editor.putLong("coverUpdateTime", currentTime);
                editor.apply();
            }
            if (!firstName.isEmpty()) {
                userAboutRequest.setFirstName(firstName);
            }
            if (!lastName.isEmpty()) {
                userAboutRequest.setLastName(lastName);
            }
            if (!email.isEmpty()) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    return;
                }
                userAboutRequest.setEmail(email);
            }
            if (!phone.isEmpty()) {
                if (!phone.matches("^(0|\\+84)[3|5|7|8|9][0-9]{8}$")) {
                    Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                userAboutRequest.setPhone(phone);
            }
            if (!dateOfBirthInput.isEmpty()) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                dateFormat.setLenient(false);
                Date dateOfBirth = null;
                try {
                    dateOfBirth = dateFormat.parse(dateOfBirthInput);
                } catch (ParseException e) {
                    Toast.makeText(this, "Date of birth or Date of Joining must be in the format MM/dd/yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }
                String formattedDate = dateFormat.format(dateOfBirth);
                userAboutRequest.setDateOfBirth(formattedDate);
            }
            if (!dojInput.isEmpty()) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                dateFormat.setLenient(false);
                Date dateOfJoining = null;
                try {
                    dateOfJoining = dateFormat.parse(dojInput);
                } catch (ParseException e) {
                    Toast.makeText(this, "Date of birth or Date of Joining must be in the format MM/dd/yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }
                String formattedDoj = dateFormat.format(dateOfJoining);
                userAboutRequest.setDateOfJoining(formattedDoj);
            }
            if (!gender.isEmpty()) {
                userAboutRequest.setGender(gender);
            }
            if (!userName.isEmpty()) {
                userAboutRequest.setUserName(userName);
            }
            if (!occupation.isEmpty()) {
                userAboutRequest.setOccupation(occupation);
            }
            if (!work.isEmpty()) {
                userAboutRequest.setWorkPlace(work);
            }
            if (!education.isEmpty()) {
                userAboutRequest.setEducationLevel(education);
            }
            if (!school.isEmpty()) {
                userAboutRequest.setSchool(school);
            }
            if (!relation.isEmpty()) {
                userAboutRequest.setRelationshipStatus(relation);
            }

            if(!city.isEmpty()||!country.isEmpty()||!state.isEmpty()||!street.isEmpty()||!region.isEmpty()){
                userAboutRequest.setLocation(Boolean.TRUE);
            } else if (city.isEmpty() && country.isEmpty() && state.isEmpty() && street.isEmpty() && region.isEmpty()){
                userAboutRequest.setLocation(Boolean.FALSE);
            }

            if (userAboutRequest.getLocation()!= null) {
                if(Boolean.TRUE.equals(userAboutRequest.getLocation())){
                   userAboutRequest.setCity(city);
                   userAboutRequest.setCountry(country);
                   userAboutRequest.setState(state);
                   userAboutRequest.setStreet(street);
                   userAboutRequest.setRegion(region);
                }
            }

            // Send the request to the server
            authenticationApi.createUserAbout(userAboutRequest)
                    .enqueue(new Callback<UserAboutResponse>() {
                        @Override
                        public void onResponse(Call<UserAboutResponse> call, Response<UserAboutResponse> response) {
                            Toast.makeText(Edit_Profile_Activity.this, "Profile updated successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<UserAboutResponse> call, Throwable t) {
                            Toast.makeText(Edit_Profile_Activity.this, "Failed to update profile!!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Logger.getLogger(Edit_Profile_Activity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });

            // Navigate back to ProfileFragment
            Intent intent = new Intent(Edit_Profile_Activity.this, vn.edu.usth.facebook.Profile.ProfileFragment.class);
            startActivity(intent);
            finish();
        });
    }

    // FetchImage class
    class FetchImage extends Thread {
        private String url;
        private ImageView imageView;
        private Bitmap bitmap;

        public FetchImage(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        public void run() {
            mainHandler.post(() -> {
                progressDialog = new ProgressDialog(Edit_Profile_Activity.this);
                progressDialog.setMessage("Loading image...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            });

            try {
                URL imageUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mainHandler.post(() -> {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(Edit_Profile_Activity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
