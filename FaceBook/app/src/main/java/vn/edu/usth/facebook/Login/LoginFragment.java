package vn.edu.usth.facebook.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.FaceBookActivity;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.SignInRequest;
import vn.edu.usth.facebook.model.response.JwtAuthenticationResponse;
import vn.edu.usth.facebook.retrofit.AuthenticationApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class LoginFragment extends Fragment {
    private AuthenticationApi authenticationApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
//        checkTokenExpiration();
        initializeComponents(view);

        LinearLayout createAccount = view.findViewById(R.id.create_account);
        createAccount.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), Register_Activity.class);
            startActivity(i);
        });

        LinearLayout forgotPassword = view.findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), ForgotPassword_Activity.class);
            startActivity(i);
        });

        return view;
    }

    private void initializeComponents(View view) {
        TextInputEditText inputEditTextEmail = view.findViewById(R.id.emailLogin);
        TextInputEditText inputEditTextPassword = view.findViewById(R.id.passwordLogin);

        Button buttonLogin = view.findViewById(R.id.buttonLogin);

        RetrofitService retrofitService = new RetrofitService(requireActivity());
        authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

        buttonLogin.setOnClickListener(v -> {
            String email = String.valueOf(inputEditTextEmail.getText());
            String password = String.valueOf(inputEditTextPassword.getText());
            // Validate email format
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(getContext(), "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }
            // Validate password length
            if (password.length() < 6) {
                Toast.makeText(getContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                return;
            }
            // Create the SignInRequest object
            SignInRequest signInRequest = new SignInRequest();
            signInRequest.setEmail(email);
            signInRequest.setPassword(password);
            // Send the request to the server
            authenticationApi.signIn(signInRequest).enqueue(new Callback<JwtAuthenticationResponse>() {
                @Override
                public void onResponse(Call<JwtAuthenticationResponse> call, Response<JwtAuthenticationResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        String token = response.body().getToken();
                        // Assuming this is where you receive the token after successful login
                        SharedPreferences sharedTokenPreferences = requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorToken = sharedTokenPreferences.edit();
                        editorToken.putString("auth_token", token);
                        editorToken.apply();
                        // Lưu trạng thái đăng nhập và thời gian hết hạn trong SharedPreferences
                        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("TOLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        // Tính thời gian hết hạn từ thời điểm hiện tại
                        long expirationTime = System.currentTimeMillis() + 1440000; // 1440000ms = 24 phút
                        editor.putLong("expirationTime", expirationTime);
                        editor.apply();
                        // Điều hướng đến FacebookActivity
                        Intent intent = new Intent(getActivity(), vn.edu.usth.facebook.FaceBookActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "Login failed! Incorrect email or password.", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<JwtAuthenticationResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Logger.getLogger(LoginFragment.class.getName()).log(Level.SEVERE, "Error occurred", t);
                }
            });
        });
    }
    private void checkTokenExpiration() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("TOLogin", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // User is not logged in, delete the token
            clearToken();
        } else {
            // User is logged in, check for token expiration
            long expirationTime = sharedPreferences.getLong("expirationTime", 0);
            long currentTime = System.currentTimeMillis();

            if (expirationTime <= currentTime) {
                // Token has expired, clear it
                clearToken();
            }
        }
    }

    private void clearToken() {
        // Clear the authentication token from SharedPreferences
        SharedPreferences sharedTokenPreferences = requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorToken = sharedTokenPreferences.edit();
        editorToken.remove("auth_token");
        editorToken.apply();

        // Reset login state
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("TOLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.remove("expirationTime");
        editor.apply();

        // Redirect to login screen
        Intent loginIntent = new Intent(getActivity(), FaceBookActivity.class);
        startActivity(loginIntent);
        requireActivity().finish();
    }

}
