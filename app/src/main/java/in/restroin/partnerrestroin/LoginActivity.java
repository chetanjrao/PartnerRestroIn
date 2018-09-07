package in.restroin.partnerrestroin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import in.restroin.partnerrestroin.interfaces.LoginClient;
import in.restroin.partnerrestroin.models.LoginModel;
import in.restroin.partnerrestroin.models.ProfileModel;
import in.restroin.partnerrestroin.platforms.ProfileDeserializer;
import in.restroin.partnerrestroin.platforms.ServiceGenerator;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Gson profileGson = new GsonBuilder().registerTypeAdapter(ProfileModel.class, new ProfileDeserializer()).create();

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://developers.restroin.in/")
            .addConverterFactory(GsonConverterFactory.create(profileGson));
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    Retrofit retrofit = builder.client(client.build()).build();
    LoginClient loginClient = retrofit.create(LoginClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private String ProcessAuthentication(String username, String password) {

        return null;
    }
}
