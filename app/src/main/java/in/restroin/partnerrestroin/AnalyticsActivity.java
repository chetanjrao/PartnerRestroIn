package in.restroin.partnerrestroin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnalyticsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        mTextMessage = (TextView) findViewById(R.id.message);
        loadfragment(new DashboardFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        overridePendingTransition(0, 0);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            RelativeLayout top_bar = (RelativeLayout) findViewById(R.id.top_bar);
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    top_bar.setElevation(14f);
                    fragment = new DashboardFragment();
                    break;
                case R.id.navigation_notifications:
                    top_bar.setElevation(14f);
                    fragment = new NotificationsFragment();
                    break;
                case R.id.navigation_payment:
                    top_bar.setElevation(14f);
                    fragment = new PaymentsFragment();
                    break;
                case R.id.navigation_contacts:
                    top_bar.setElevation(0f);
                    fragment = new ContactsFragment();
                    break;
                case R.id.navigation_profile:
                    top_bar.setElevation(14f);
                    fragment = new ProfileFragment();
                    break;
            }
            return loadfragment(fragment);
        }
    };

    private boolean loadfragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_container, fragment).commit();
            return true;
        }
        return false;
    }

}
