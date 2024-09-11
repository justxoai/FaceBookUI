package vn.edu.usth.facebookuiproject.profile.subs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.facebookuiproject.profile.subs.photos.ProfileSubsPhotosFragment;
import vn.edu.usth.facebookuiproject.profile.subs.posts.ProfileSubsPostsFragment;
import vn.edu.usth.facebookuiproject.profile.subs.reels.ProfileSubsReelsFragment;

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
