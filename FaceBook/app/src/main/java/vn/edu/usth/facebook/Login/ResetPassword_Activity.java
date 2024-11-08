package vn.edu.usth.facebook.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.ResetPasswordRequest;
import vn.edu.usth.facebook.model.response.JwtAuthenticationResponse;
import vn.edu.usth.facebook.retrofit.AuthenticationApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class ResetPassword_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initializeComponents();

        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initializeComponents() {
        EditText inputEditTextPassword = findViewById(R.id.text_password1);
        EditText inputEditTextConfirmPassword = findViewById(R.id.text_password2);

        Button buttonReset = findViewById(R.id.change_password);

        RetrofitService retrofitService = new RetrofitService();
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

        // Retrieve the token from SharedPreferences
        SharedPreferences sharedPreferencesForToken = getSharedPreferences("AuthPreferences", Context.MODE_PRIVATE);
        String token = sharedPreferencesForToken.getString("authToken", null);

        buttonReset.setOnClickListener(view -> {
            String password = String.valueOf(inputEditTextPassword.getText());
            String confirmPassword = String.valueOf(inputEditTextConfirmPassword.getText());

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

            // Create the ResetPasswordRequest object
            ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
            resetPasswordRequest.setToken(token);
            resetPasswordRequest.setNewPassword(password);
            resetPasswordRequest.setConfirmPassword(confirmPassword);

            // Send the request to the server
            authenticationApi.resetPassword(resetPasswordRequest)
                    .enqueue(new Callback<JwtAuthenticationResponse>() {
                        @Override
                        public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                            Toast.makeText(ResetPassword_Activity.this, "Reset Password successful!", Toast.LENGTH_SHORT).show();
                            Fragment loginFragment = new LoginFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(android.R.id.content, loginFragment);
                            transaction.commit();
                        }

                        @Override
                        public void onFailure(Call<JwtAuthenticationResponse> call, Throwable t) {
                            Toast.makeText(ResetPassword_Activity.this, "Reset Password failed!!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Logger.getLogger(Register_Activity.class.getName()).log(Level.SEVERE, "Error occurred, Please enter password again to change", t);
                        }
                    });
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    private boolean validateResetPassword(String text) {
        return !text.isEmpty();
    }
}
