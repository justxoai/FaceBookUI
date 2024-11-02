package vn.edu.usth.facebook.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.facebook.R;

public class ForgotPassword_Activity extends AppCompatActivity {

    private EditText editTextEmail;

    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);

        editTextEmail = findViewById(R.id.text_email);
        resetButton = findViewById(R.id.reset_password);

        resetButton.setOnClickListener(view -> {
            String TextEmail = editTextEmail.getText().toString();

            if(validateForgotPassword(TextEmail)){

                SharedPreferences sharedPreferences = getSharedPreferences("ToforgotPassword", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isForgotPassword", true);
                editor.apply();

                Intent intent = new Intent(this, ResetPassword_Activity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(this, "Please enter email to change password", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    private boolean validateForgotPassword(String text) {
        return !text.isEmpty();
    }
}