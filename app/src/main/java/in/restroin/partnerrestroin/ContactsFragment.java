package in.restroin.partnerrestroin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends Fragment {

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ContactsView =  inflater.inflate(R.layout.fragment_contact, container, false);
        ViewPager viewPager = (ViewPager) ContactsView.findViewById(R.id.contacts_view_pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) ContactsView.findViewById(R.id.contact_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return ContactsView;
    }

    public void setupViewPager(ViewPager viewPager){
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new CustomersContactFragment(), "Customers");
        adapter.addFragment(new StaffContactFragment(), "Staff");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter{

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
