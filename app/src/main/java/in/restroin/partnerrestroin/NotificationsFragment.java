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
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.contacts_view_pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.contact_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
//        RecyclerView NotificationRecycler = (RecyclerView) view.findViewById(R.id.notifications_recyclerView);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
//        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
//        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
//        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
//        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
//        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
//        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
//        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
//        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
//        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
//        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
//        notifications.add(new NotificationsModel(134, "Payment Pending with booking id #123. Click to complete payment", "P"));
//        NotificationsAdapter adapter = new NotificationsAdapter(notifications);
//        NotificationRecycler.setLayoutManager(layoutManager);
//        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
//            @Override
//            public boolean onDown(MotionEvent motionEvent) {
//                return false;
//            }
//
//            @Override
//            public void onShowPress(MotionEvent motionEvent) {
//
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent motionEvent) {
//                return true;
//            }
//
//            @Override
//            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//                return false;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent motionEvent) {
//
//            }
//
//            @Override
//            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//                return false;
//            }
//        });
//
//        NotificationRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                View childView = rv.findChildViewUnder(e.getX(), e.getY());
//                if(childView != null && gestureDetector.onTouchEvent(e)) {
//                    TextView phase = childView.findViewById(R.id.booking_phase);
//                    String phase_ = phase.getText().toString();
//                    if(phase_.equals("B")){
//                        Intent go_to_confirm = new Intent(getActivity(), BookingActivity.class);
//                        startActivity(go_to_confirm);
//                    } else {
//                        Toast.makeText(getContext(), "Phase id " + phase_, Toast.LENGTH_SHORT).show();
//                    }
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
//        NotificationRecycler.setAdapter(adapter);
        return view;
    }

    public void setupViewPager(ViewPager viewPager){
        ContactsFragment.Adapter adapter = new ContactsFragment.Adapter(getChildFragmentManager());
        adapter.addFragment(new CustomersContactFragment(), "Customers");
        adapter.addFragment(new StaffContactFragment(), "Staff");
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
