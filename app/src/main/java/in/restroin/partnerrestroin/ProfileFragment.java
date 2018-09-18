package in.restroin.partnerrestroin;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.ProfileModel;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import in.restroin.partnerrestroin.utils.ServiceGenerator;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {
    private final String API_BASE_URL = "https://www.restroin.in/developers/api/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().client(httpClient.build()).baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //TODO: Call the object for the profile model using the partners id and the access key given in the shared preferences
        getProfileDetails(new SavedPreferences().getApiKey(view.getContext()), new SavedPreferences().getPartnerID(view.getContext()), view);
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.status_restaurant_switch);
        if(switchCompat.isChecked()){
            switchCompat.setThumbTintList(ColorStateList.valueOf(Color.GREEN));
            switchCompat.setTrackResource(R.color.colorPrimary);
        }
        return view;

    }

    private void getProfileDetails(String access_key, String partner_id, final View view){
        PartnerRestroINClient partnerRestroINClient = retrofit.create(PartnerRestroINClient.class);
        Call <ProfileModel> profileModelCall = partnerRestroINClient.getProfile(access_key, partner_id);
        profileModelCall.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<ProfileModel> call,@NonNull Response<ProfileModel> response) {
                String profile_username = response.body().getUsername();
                String profile_mobile = response.body().getMobile();
                String profile_name = response.body().getName();
                ImageView profile_image_view = (ImageView) view.findViewById(R.id.profile_image);
                TextView profile_name_text_view = (TextView) view.findViewById(R.id.profile_name__);
                profile_name_text_view.setText(profile_name);
                TextView profile_email_text_view = (TextView) view.findViewById(R.id.profile_email);
                profile_email_text_view.setText(profile_username);
                TextView profile_mobile_text_view = (TextView) view.findViewById(R.id.profile_mobile_no);
                profile_mobile_text_view.setText(profile_mobile);
                String image_path = response.body().getImage();
                Uri profile_image_url = Uri.parse(image_path);
                Picasso.get().load(profile_image_url).into(profile_image_view);
            }

            @Override
            public void onFailure(@NonNull Call<ProfileModel> call,@NonNull Throwable t) {

            }
        });
    }
}
