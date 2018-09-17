package in.restroin.partnerrestroin;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.ProfileModel;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import in.restroin.partnerrestroin.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

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
        PartnerRestroINClient partnerRestroINClient = new ServiceGenerator().createService(PartnerRestroINClient.class);
        Call <ProfileModel> profileModelCall = partnerRestroINClient.getProfile(access_key, partner_id);
        profileModelCall.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<ProfileModel> call,@NonNull Response<ProfileModel> response) {
                    String profile_username = response.body().getUsername();
                    String profile_mobile = response.body().getMobile();
                    String profile_name = response.body().getName();
                    TextView profile_name_text_view = (TextView) view.findViewById(R.id.profile_name);
                    profile_name_text_view.setText(profile_name);
                    TextView profile_email_text_view = (TextView) view.findViewById(R.id.profile_email);
                    profile_email_text_view.setText(profile_username);
                    TextView profile_mobile_text_view = (TextView) view.findViewById(R.id.profile_mobile_no);
                    profile_mobile_text_view.setText(profile_mobile);
            }

            @Override
            public void onFailure(@NonNull Call<ProfileModel> call,@NonNull Throwable t) {

            }
        });
    }
}
