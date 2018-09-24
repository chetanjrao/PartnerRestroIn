package in.restroin.partnerrestroin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import in.restroin.partnerrestroin.interfaces.LoginClient;
import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.AuthenModel;
import in.restroin.partnerrestroin.models.LoginModel;
import in.restroin.partnerrestroin.models.MessageModel;
import in.restroin.partnerrestroin.models.ProfileModel;
import in.restroin.partnerrestroin.platforms.ProfileDeserializer;
import in.restroin.partnerrestroin.utils.BasicAuthInterceptor;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import in.restroin.partnerrestroin.utils.ServiceGenerator;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button signin = (Button) findViewById(R.id.sign_in);
        final CardView loginCardView = (CardView) findViewById(R.id.loginCardView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
        progressBar.setVisibility(View.GONE);
        loginCardView.setVisibility(View.VISIBLE);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);
                if(!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){
                    progressBar.setVisibility(View.VISIBLE);
                    loginCardView.setVisibility(View.GONE);
                    ProcessAuthentication(username.getText().toString(), password.getText().toString());
                } else {
                    if(TextUtils.isEmpty(username.getText().toString())){
                       username.setError("Enter Your Username");
                    } else if(TextUtils.isEmpty(password.getText().toString())){
                        password.setError("Enter Your Password");
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        final CardView loginCardView = (CardView) findViewById(R.id.loginCardView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
        progressBar.setVisibility(View.GONE);
        loginCardView.setVisibility(View.VISIBLE);
        String access_token = new SavedPreferences().getApiKey(LoginActivity.this);
        if(access_token != null){
            Intent goToDashBoard = new Intent(LoginActivity.this, AnalyticsActivity.class);
            startActivity(goToDashBoard);
        }
        super.onStart();
    }

    private void ProcessAuthentication(final String username, final String password) {
        LoginClient client = new ServiceGenerator().createService(LoginClient.class, username, password);
        Call<AuthenModel> loginAttempt = client.authentication();
            loginAttempt.enqueue(new Callback<AuthenModel>() {
                @Override
                public void onResponse(@NonNull Call<AuthenModel> call,@NonNull Response<AuthenModel> response) {
                    String access_token = response.body().getAccess_token();
                    String message = response.body().getMessage();
                    String error_code = response.body().getError_code();
                    String partner_id = response.body().getPartner_id();
                    String restaurant_id = response.body().getRestaurant_id();
                    Log.d("AuthLogging", "Access Token: " + access_token + " Message: " + message);
                    if(access_token != null && error_code == null){
                        SavedPreferences preferences = new SavedPreferences();
                        preferences.setSharedPreferences(LoginActivity.this, username, password, access_token, partner_id, restaurant_id);
                        Intent goToDashBoard = new Intent(LoginActivity.this, AnalyticsActivity.class);
                        startActivity(goToDashBoard);
                    } else if (access_token != null && error_code.equals("102")){
                        Toast.makeText(LoginActivity.this, "Unable to assign Token", Toast.LENGTH_SHORT).show();
                    } else if(access_token == null && error_code.equals("401")){
                        final CardView loginCardView = (CardView) findViewById(R.id.loginCardView);
                        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
                        progressBar.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        loginCardView.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Invalid Login Attempt", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AuthenModel> call, Throwable t) {
                    final CardView loginCardView = (CardView) findViewById(R.id.loginCardView);
                    final ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
                    progressBar.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    loginCardView.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "Something went wrong. Check your internet connection :-( ", Toast.LENGTH_SHORT).show();
                    Log.e("AuthLogging", "Error: The authentication call seems invalid . The error is: " + t.getMessage());
                }
            });
    }
}
