/*
 * Copyright (c) 2018. An change in this code must be done under my supervision and any misuse my lead to legal actions
 */

package in.restroin.partnerrestroin;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.BookingDataModel;
import in.restroin.partnerrestroin.models.MessageModel;
import in.restroin.partnerrestroin.models.RestaurantProfileModel;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class BookingActivity extends AppCompatActivity {

    private final String API_BASE_URL = "https://www.restroin.in/developers/api/v2/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().client(httpClient.build()).baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private Retrofit retrofit = builder.build();
    private String customer_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TextView textView = (TextView) findViewById(R.id.error_text_view);
        RelativeLayout BookingLayout = (RelativeLayout) findViewById(R.id.booking_relative_layout);
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
        BookingLayout.setVisibility(View.GONE);
        overridePendingTransition(R.anim.right_slide_in, R.anim.right_silde_out);
        setDetails(getIntent().getStringExtra("booking_id"));
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.call_layout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + customer_number));
                if (ActivityCompat.checkSelfPermission(BookingActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(BookingActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 200);
                    return;
                }
                startActivity(intent);
            }
        });
        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.confirm_booking_full_layout);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    TextView textView = (TextView) findViewById(R.id.error_text_view);
                    RelativeLayout BookingLayout = (RelativeLayout) findViewById(R.id.booking_relative_layout);
                    progressBar.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    BookingLayout.setVisibility(View.GONE);
                    UpdateBooking("Confirm", getIntent().getStringExtra("booking_id"));
            }
        });

        RelativeLayout cancel_layout = (RelativeLayout) findViewById(R.id.cancel_booking_full_layout);
        cancel_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                TextView textView = (TextView) findViewById(R.id.error_text_view);
                RelativeLayout BookingLayout = (RelativeLayout) findViewById(R.id.booking_relative_layout);
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                BookingLayout.setVisibility(View.GONE);
                UpdateBooking("Cancel", getIntent().getStringExtra("booking_id"));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String permissions[],@NonNull int[] grantResults) {
        switch (requestCode) {
            case 200:
                if (grantResults.length > 0) {
                    boolean CallPermissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (!CallPermissionAccepted){
                            if (ActivityCompat.shouldShowRequestPermissionRationale(BookingActivity.this, Manifest.permission.CALL_PHONE)) {
                                showMessageOKCancel("PartnerRestroIN requests you to allow access",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{"android.Manifest.permission.CALL_PHONE"},
                                                            200);
                                                }
                                            }
                                        });
                                return;
                            }
                    }
                }
            break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(BookingActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void UpdateBooking(String process_next_step, String booking_id){
        PartnerRestroINClient client = retrofit.create(PartnerRestroINClient.class);
        SavedPreferences savedPreferences = new SavedPreferences();
        Call<MessageModel> call = client.updateBooking(savedPreferences.getApiKey(BookingActivity.this), savedPreferences.getPartnerID(BookingActivity.this), booking_id, savedPreferences.getRestaurantID(BookingActivity.this), process_next_step, "0");
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                String message = response.body().getMessage();
                Toast.makeText(BookingActivity.this, "Message: " + message, Toast.LENGTH_SHORT).show();
                finish();
//                Intent intent = new Intent(BookingActivity.this, AnalyticsActivity.class);
//                intent.putExtra("Restart", "yes");
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                TextView textView = (TextView) findViewById(R.id.error_text_view);
                RelativeLayout BookingLayout = (RelativeLayout) findViewById(R.id.booking_relative_layout);
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                BookingLayout.setVisibility(View.VISIBLE);
                Toast.makeText(BookingActivity.this, "SomeThing went wrong. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDetails(String booking_id){
        PartnerRestroINClient client = retrofit.create(PartnerRestroINClient.class);
        SavedPreferences savedPreferences = new SavedPreferences();
        Call<BookingDataModel> call = client.getSingleBookingData(savedPreferences.getApiKey(BookingActivity.this), savedPreferences.getPartnerID(BookingActivity.this), savedPreferences.getRestaurantID(BookingActivity.this), booking_id);
        call.enqueue(new Callback<BookingDataModel>() {
            @Override
            public void onResponse(Call<BookingDataModel> call, Response<BookingDataModel> response) {
                String booking_id = response.body().getBooking_id();
                String booking_guest = response.body().getGuest_name();
                String booking_phone = response.body().getGuest_phone();
                String booking_email = response.body().getGuest_email();
                String booking_male = response.body().getNumber_of_male();
                String booking_female = response.body().getNumber_of_female();
                String booking_coupoun = response.body().getCouponSelected();
                String arrival_time = response.body().getVisiting_date_time();
                ImageView profile_image = (ImageView) findViewById(R.id.guest_profile_image);
                String uri__ = "https://www.restroin.in/developers/api/images/blank.png";
                Uri blank = Uri.parse(uri__);
                Picasso.get().load(blank).into(profile_image);
                TextView booking_id_ = (TextView) findViewById(R.id.booking_id);
                booking_id_.setText("# " + booking_id);
                Toolbar booking_id_text_view = (Toolbar) findViewById(R.id.toolbar);
                booking_id_text_view.setSubtitle("Booking ID: #" + booking_id);
                TextView guest_name = (TextView) findViewById(R.id.guest_name);
                guest_name.setText(booking_guest);
                TextView booking_coupon = (TextView) findViewById(R.id.booking_coupoun);
                if(booking_coupoun == null || booking_coupoun.equals("")){
                    booking_coupon.setText("No Coupons Chosen");
                } else {
                    booking_coupon.setText(booking_coupoun);
                }
                TextView people = (TextView) findViewById(R.id.booking_people);
                people.setText(booking_male + " M, " + booking_female + " F");
                TextView slot = (TextView) findViewById(R.id.booking_slot);
                slot.setText(arrival_time);
                customer_number = booking_phone;
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                TextView textView = (TextView) findViewById(R.id.error_text_view);
                RelativeLayout BookingLayout = (RelativeLayout) findViewById(R.id.booking_relative_layout);
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                BookingLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<BookingDataModel> call, Throwable t) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                TextView textView = (TextView) findViewById(R.id.error_text_view);
                RelativeLayout BookingLayout = (RelativeLayout) findViewById(R.id.booking_relative_layout);
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                BookingLayout.setVisibility(View.GONE);
            }
        });
    }

}
