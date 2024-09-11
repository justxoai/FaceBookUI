package vn.edu.usth.facebookuiproject.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.facebookuiproject.R;
import vn.edu.usth.facebookuiproject.profile.fragment.ProfileAvatarAndBackgroundFragment;
import vn.edu.usth.facebookuiproject.profile.fragment.ProfileBioFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        LinearLayout linearLayout = findViewById(R.id.profile_fragment_container);
        addFragmentToLinearLayout(linearLayout, new ProfileAvatarAndBackgroundFragment());
        addFragmentToLinearLayout(linearLayout, new ProfileBioFragment());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addFragmentToLinearLayout(LinearLayout linearLayout, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(linearLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Profile", "onStart here");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Profile", "onResume here");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Profile", "onPause here");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Profile", "onStop here");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Profile", "onDestroy here");
    }
}