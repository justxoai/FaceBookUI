package vn.edu.usth.facebookuiproject.profile.subs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.facebookuiproject.profile.subs.photos.ProfileSubsPhotosFragment;
import vn.edu.usth.facebookuiproject.profile.subs.posts.ProfileSubsPostsFragment;
import vn.edu.usth.facebookuiproject.profile.subs.reels.ProfileSubsReelsFragment;

public class ProfileSubsAdapter extends FragmentStateAdapter {
    private final int PAGE_COUNT = 3;
    private final String[] titles = new String[] {"Posts", "Photos", "Reels"};

    public ProfileSubsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new ProfileSubsPostsFragment();
            case 1: return new ProfileSubsPhotosFragment();
            case 2: return new ProfileSubsReelsFragment();
            default:return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
