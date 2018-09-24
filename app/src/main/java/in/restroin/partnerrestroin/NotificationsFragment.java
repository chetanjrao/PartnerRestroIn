package in.restroin.partnerrestroin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.adapters.NotificationsAdapter;
import in.restroin.partnerrestroin.fragments.ActiveFragment;
import in.restroin.partnerrestroin.fragments.CancelledFragment;
import in.restroin.partnerrestroin.fragments.CompletedFragment;
import in.restroin.partnerrestroin.fragments.PendingFragment;
import in.restroin.partnerrestroin.models.NotificationsModel;


public class NotificationsFragment extends Fragment {



    List<NotificationsModel> notifications = new ArrayList<>();

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_notifications, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.bookings_view_pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.bookings_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void setupViewPager(ViewPager viewPager){
        NotificationsFragment.Adapter adapter = new NotificationsFragment.Adapter(getChildFragmentManager());
        adapter.addFragment(new PendingFragment(), "Pending");
        adapter.addFragment(new ActiveFragment(), "Active");
        adapter.addFragment(new CompletedFragment(), "Completed");
        adapter.addFragment(new CancelledFragment(), "Cancelled");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {

        List<Fragment> fragments = new ArrayList<>();
        List<String> fragament_titles =new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragament_titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragament_titles.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
