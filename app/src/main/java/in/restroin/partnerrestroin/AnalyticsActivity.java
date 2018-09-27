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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.IOException;

import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.MessageModel;
import in.restroin.partnerrestroin.utils.PartnerMessagingService;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import in.restroin.partnerrestroin.utils.ServiceGenerator;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnalyticsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String token;
    private LayoutInflater inflater;

    private final String API_BASE_URL = "https://www.restroin.in/developers/api/v2/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).client(httpClient.build()).addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        mTextMessage = (TextView) findViewById(R.id.message);
        loadfragment(new DashboardFragment());
        token = new PartnerMessagingService().getToken();
        if(new SavedPreferences().device_uid_get(AnalyticsActivity.this) == null){
            setDeviceUID(new SavedPreferences().getApiKey(AnalyticsActivity.this), FirebaseInstanceId.getInstance().getToken() );
            Toast.makeText(this, "Device ID: " + FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    private void setDeviceUID(String access_key, final String device_uid){
        PartnerRestroINClient client = retrofit.create(PartnerRestroINClient.class);
        Call<MessageModel> messageModelCall = client.updateUid(access_key, device_uid);
        messageModelCall.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                String message = response.body().getMessage();
                new SavedPreferences().device_uid_set(AnalyticsActivity.this, device_uid);
                String error = response.body().getError_code();
                Log.d("ProfileDetails", "Message: " + message + " Error: " + error);
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.e("DetailsError", "Error in updating details: " + t.getMessage());
            }
        });
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
                    top_bar.setElevation(0f);
                    fragment = new NotificationsFragment();
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
