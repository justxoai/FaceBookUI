package vn.edu.usth.facebookuiproject.profile.fragment.subs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProfileSubsAdapter extends FragmentStateAdapter {

    public ProfileSubsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ProfileSubsPhotosFragment();
            case 2:
                return new ProfileSubsReelsFragment();
            default:
                return new ProfileSubsPostsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
