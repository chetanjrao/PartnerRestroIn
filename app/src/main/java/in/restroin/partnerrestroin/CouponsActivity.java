package in.restroin.partnerrestroin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.library.AnimateCheckBox;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.adapters.CouponsAdapter;
import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.CouponsModel;
import in.restroin.partnerrestroin.models.MessageModel;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponsActivity extends AppCompatActivity {

    private final String API_BASE_URL = "https://www.restroin.in/developers/api/v2/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).client(httpClient.build()).addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();
    List<CouponsModel> coupons = new ArrayList<>();
    List<String> selectedCoupons = new ArrayList<String>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.checkboxes_recyclerView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView textView = (TextView) findViewById(R.id.error_text_view);
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        showCoupons(recyclerView, selectedCoupons);
        Button button = (Button) findViewById(R.id.updateCoupons);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                PartnerRestroINClient client = retrofit.create(PartnerRestroINClient.class);
                SavedPreferences savedPreferences = new SavedPreferences();
                Call<MessageModel> call = client.updateCoupons(savedPreferences.getApiKey(CouponsActivity.this), selectedCoupons.toString(), savedPreferences.getRestaurantID(CouponsActivity.this), savedPreferences.getPartnerID(CouponsActivity.this));
                call.enqueue(new Callback<MessageModel>() {
                    @Override
                    public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                        Toast.makeText(CouponsActivity.this, "Coupons Updated Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<MessageModel> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        Toast.makeText(CouponsActivity.this, "Something Went Wrong. Please try again :-( ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void showCoupons(final RecyclerView recyclerView, final List<String> selectedCoupons){
        final GestureDetector gestureDetector = new GestureDetector(CouponsActivity.this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
        PartnerRestroINClient client = retrofit.create(PartnerRestroINClient.class);
        Call<List<CouponsModel>> call = client.getCoupons();
        call.enqueue(new Callback<List<CouponsModel>>() {
            @Override
            public void onResponse(@NonNull final Call<List<CouponsModel>> call,@NonNull Response<List<CouponsModel>> response) {
                for(int i=0; i < response.body().size(); i++){
                    coupons.add(new CouponsModel(response.body().get(i).getCoupon_id(), response.body().get(i).getCoupon_code(), response.body().get(i).getDescription(), response.body().get(i).getCoupon_image()));
                }
                CouponsAdapter adapter = new CouponsAdapter(coupons);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                TextView textView = (TextView) findViewById(R.id.error_text_view);
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(adapter);
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        final View childView = rv.findChildViewUnder(e.getX(), e.getY());
                        if(childView != null && gestureDetector.onTouchEvent(e)) {
                            int position = rv.getChildAdapterPosition(childView);
                            final AnimateCheckBox checkBox = (AnimateCheckBox) rv.getChildAt(position).findViewById(R.id.coupon_check_box);
                            final TextView coupon_id = (TextView) rv.getChildAt(position).findViewById(R.id.coupon_id);
                            checkBox.setOnCheckedChangeListener(new AnimateCheckBox.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(View buttonView, boolean isChecked) {
                                    if(isChecked){
                                        selectedCoupons.add('"' + coupon_id.getText().toString() + '"');
                                        Toast.makeText(CouponsActivity.this, "" + selectedCoupons.toString(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        selectedCoupons.remove('"' + coupon_id.getText().toString() + '"');
                                        Toast.makeText(CouponsActivity.this, "" + selectedCoupons.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<CouponsModel>> call,@NonNull Throwable t) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                TextView textView = (TextView) findViewById(R.id.error_text_view);
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }
}
