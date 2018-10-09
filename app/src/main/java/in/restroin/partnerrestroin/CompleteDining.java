package in.restroin.partnerrestroin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.MessageModel;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompleteDining extends AppCompatActivity {

    private final String API_BASE_URL = "https://www.restroin.in/developers/api/v2/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().client(httpClient.build()).baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_dining);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.billAmountProgressBar);
        final RelativeLayout billAmountLayout = (RelativeLayout) findViewById(R.id.billAmountLayout);
        progressBar.setVisibility(View.GONE);
        billAmountLayout.setVisibility(View.VISIBLE);
        Button button = (Button) findViewById(R.id.complete_the_booking_button);
        final EditText bill = (EditText) findViewById(R.id.bill_amount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getStringExtra("booking_step").equals("Completed")){
                    progressBar.setVisibility(View.VISIBLE);
                    billAmountLayout.setVisibility(View.GONE);
                    CompleteBooking(getIntent().getStringExtra("booking_id"), bill.getText().toString());
                }
            }
        });

    }

    private void CompleteBooking(String booking_id, String billAmount){
        PartnerRestroINClient client = retrofit.create(PartnerRestroINClient.class);
        SavedPreferences savedPreferences = new SavedPreferences();
        Call<MessageModel> call = client.updateBooking(savedPreferences.getApiKey(CompleteDining.this), savedPreferences.getPartnerID(CompleteDining.this), booking_id, savedPreferences.getRestaurantID(CompleteDining.this), "Completed", billAmount);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(@NonNull Call<MessageModel> call,@NonNull Response<MessageModel> response) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.billAmountProgressBar);
                RelativeLayout billAmountLayout = (RelativeLayout) findViewById(R.id.billAmountLayout);
                progressBar.setVisibility(View.GONE);
                billAmountLayout.setVisibility(View.GONE);
                String message = response.body().getMessage();
                Toast.makeText(CompleteDining.this, "Message:" + message, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(@NonNull Call<MessageModel> call,@NonNull Throwable t) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.billAmountProgressBar);
                RelativeLayout billAmountLayout = (RelativeLayout) findViewById(R.id.billAmountLayout);
                progressBar.setVisibility(View.GONE);
                billAmountLayout.setVisibility(View.VISIBLE);
                Toast.makeText(CompleteDining.this, "Something went wrong. Try Again :-(", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
