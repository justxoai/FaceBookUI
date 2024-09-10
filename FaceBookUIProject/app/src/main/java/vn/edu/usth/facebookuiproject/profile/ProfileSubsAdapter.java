package vn.edu.usth.facebookuiproject.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.facebookuiproject.profile.subs.ProfileSubsPhotosFragment;
import vn.edu.usth.facebookuiproject.profile.subs.ProfileSubsPostsFragment;
import vn.edu.usth.facebookuiproject.profile.subs.ProfileSubsReelsFragment;

public class ProfileSubsAdapter extends FragmentStateAdapter {

    public ProfileSubsAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new ProfileSubsPostsFragment();
        if (position == 1) return new ProfileSubsPhotosFragment();
        return new ProfileSubsReelsFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
