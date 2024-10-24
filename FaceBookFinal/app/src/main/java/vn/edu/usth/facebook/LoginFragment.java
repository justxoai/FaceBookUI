
package vn.edu.usth.facebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class LoginFragment extends Fragment {

    private EditText editTextEmail, editTextPassword;

    private Button buttonLogin;

    private ViewPager2 mviewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = view.findViewById(R.id.editText);
        editTextPassword = view.findViewById(R.id.editText2);
        buttonLogin = view.findViewById(R.id.button);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (validateLogin(email, password)) {
                // Lưu trạng thái đăng nhập
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("to_login", getContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                // Chuyển đến FaceBookActivity
                Intent intent = new Intent(getActivity(), FaceBookActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                // Xử lý khi thông tin đăng nhập không hợp lệ
                Toast.makeText(getActivity(), "Error, Please try again!", Toast.LENGTH_SHORT).show();
            }
        });

//        mviewPager = view.findViewById(R.id.view_pager1);
//        Login_Changing adapter = new Login_Changing(getChildFragmentManager(), getLifecycle()); // Use getChildFragmentManager() in Fragment
//        mviewPager.setAdapter(adapter);
//
//        mviewPager.setCurrentItem(0, true);
//
//        mviewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });

        LinearLayout create_account = view.findViewById(R.id.create_account);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewPager.setCurrentItem(1, true);
            }
        });

        return view;
    }

    private boolean validateLogin(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }
}
