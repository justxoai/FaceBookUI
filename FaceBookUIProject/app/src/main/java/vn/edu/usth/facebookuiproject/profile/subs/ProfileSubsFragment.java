package vn.edu.usth.facebookuiproject.profile.subs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.usth.facebookuiproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileSubsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileSubsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ProfileSubsAdapter profileSubsAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileSubsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileSubsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileSubsFragment newInstance(String param1, String param2) {
        ProfileSubsFragment fragment = new ProfileSubsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_subs, container, false);
        profileSubsAdapter = new ProfileSubsAdapter(requireActivity());

        viewPager2 = view.findViewById(R.id.profile_subs_viewer);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setUserInputEnabled(false);
        viewPager2.setAdapter(profileSubsAdapter);

        tabLayout = view.findViewById(R.id.profile_subs_selection);
        boolean autoRefresh = true;
        boolean smoothScroll = false;
        new TabLayoutMediator(tabLayout, viewPager2, autoRefresh, smoothScroll,
                (tab, position) -> tab.setText(profileSubsAdapter.getPageTitle(position))).attach();
        return view;
    }
}