package vn.edu.usth.facebook.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.retrofit.AuthenticationApi;
import vn.edu.usth.facebook.retrofit.RetrofitService;

public class MenuFragment extends Fragment {
    private AuthenticationApi authenticationApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        // Khởi tạo Retrofit và AuthenticationApi
        RetrofitService retrofitService = new RetrofitService();
        authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

        ImageButton searchButton = v.findViewById(R.id.home_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Search_Activity.class );
                startActivity(i);
            }
        });

        LinearLayout logoutButton = v.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogout();
            }
        });

        return v;
    }

    private void performLogout() {
        authenticationApi.logout().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Đăng xuất thành công, điều hướng về màn hình đăng nhập
                    Fragment loginFragment = new vn.edu.usth.facebook.Login.LoginFragment();
                    FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(android.R.id.content, loginFragment);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getContext(), "Logout failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Logout error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
