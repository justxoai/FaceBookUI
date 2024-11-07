package vn.edu.usth.facebook.Login;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.ForgotPasswordRequest;
import vn.edu.usth.facebook.model.response.JwtAuthenticationResponse;
import vn.edu.usth.facebook.retrofit.AuthenticationApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class ForgotPassword_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initializeComponents();

        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    private void initializeComponents() {
        EditText inputEditTextEmail = findViewById(R.id.text_email);

        Button buttonForgot = findViewById(R.id.reset_password);

        RetrofitService retrofitService = new RetrofitService();
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

        buttonForgot.setOnClickListener(view -> {
            String email = String.valueOf(inputEditTextEmail.getText());;

            // Validate email format
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create the ForgotRequest object
            ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
            forgotPasswordRequest.setEmail(email);

            // Send the request to the server
            authenticationApi.forgotPassword(forgotPasswordRequest)
                    .enqueue(new Callback<JwtAuthenticationResponse>() {
                        @Override
                        public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                            Toast.makeText(ForgotPassword_Activity.this, "Send request to reset password successfully!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<JwtAuthenticationResponse> call, Throwable t) {
                            Toast.makeText(ForgotPassword_Activity.this, "Send request to reset password failed!!!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Logger.getLogger(Register_Activity.class.getName()).log(Level.SEVERE, "Error occurred, Please enter email again to send request", t);
                        }
                    });

            Intent intent = new Intent(this, ResetPassword_Activity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}