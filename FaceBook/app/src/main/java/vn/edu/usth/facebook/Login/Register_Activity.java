package vn.edu.usth.facebook.Login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.SignUpRequest;

import vn.edu.usth.facebook.model.response.JwtAuthenticationResponse;
import vn.edu.usth.facebook.retrofit.api.AuthenticationApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class Register_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeComponents();
        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTextFirstName = findViewById(R.id.editFirstName);
        TextInputEditText inputEditTextLastName = findViewById(R.id.editLastName);
        TextInputEditText inputEditTextPhone = findViewById(R.id.editPhone);
        TextInputEditText inputEditTextDateOfBirth = findViewById(R.id.editDateOfBirth);
        TextInputEditText inputEditTextAddress = findViewById(R.id.editAddress);
        TextInputEditText inputEditTextEmail = findViewById(R.id.editTextEmail);
        TextInputEditText inputEditTextPassword = findViewById(R.id.editTextPassword);
        TextInputEditText inputEditTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        RadioButton radioButtonMale = findViewById(R.id.radioButtonMale);
        RadioButton radioButtonFemale = findViewById(R.id.radioButtonFemale);

        Button buttonRegister = findViewById(R.id.RegisterForAccount);

        RetrofitService retrofitService = new RetrofitService(this);
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

        buttonRegister.setOnClickListener(view -> {
            String firstName = String.valueOf(inputEditTextFirstName.getText());
            String lastName = String.valueOf(inputEditTextLastName.getText());
            String phone = String.valueOf(inputEditTextPhone.getText());
            String dateOfBirthInput = String.valueOf(inputEditTextDateOfBirth.getText());
            String address = String.valueOf(inputEditTextAddress.getText());
            String email = String.valueOf(inputEditTextEmail.getText());
            String password = String.valueOf(inputEditTextPassword.getText());
            String confirmPassword = String.valueOf(inputEditTextConfirmPassword.getText());

            // Validate date of birth format (MM/dd/yyyy)
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);
            Date dateOfBirth = null;
            try {
                dateOfBirth = dateFormat.parse(dateOfBirthInput);
            } catch (ParseException e) {
                Toast.makeText(this, "Date of birth must be in the format MM/dd/yyyy", Toast.LENGTH_SHORT).show();
                return;
            }
            String formattedDate = dateFormat.format(dateOfBirth);

            // Validate password length
            if (password.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate if passwords match
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate phone number (Vietnam format)
            if (!phone.matches("^(0|\\+84)[3|5|7|8|9][0-9]{8}$")) {
                Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate email format
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }

            String gender = "";
            if (radioButtonMale.isChecked()) {
                gender = "Male";
            } else if (radioButtonFemale.isChecked()) {
                gender = "Female";
            }

            String userType = "USER";

            // Create the SignUpRequest object
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setFirstName(firstName);
            signUpRequest.setLastName(lastName);
            signUpRequest.setPhone(phone);
            signUpRequest.setDateOfBirth(formattedDate);
            signUpRequest.setAddress(address);
            signUpRequest.setEmail(email);
            signUpRequest.setPassword(password);
            signUpRequest.setConfirmPassword(confirmPassword);
            signUpRequest.setGender(gender);
            signUpRequest.setType(userType);

            // Send the request to the server
            authenticationApi.signUp(signUpRequest)
                    .enqueue(new Callback<JwtAuthenticationResponse>() {
                        @Override
                        public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                            Toast.makeText(Register_Activity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<JwtAuthenticationResponse> call, Throwable t) {
                            Toast.makeText(Register_Activity.this, "Registration failed!!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Logger.getLogger(Register_Activity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });

            Fragment loginFragment = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, loginFragment);
            transaction.commit();
        });
    }
}