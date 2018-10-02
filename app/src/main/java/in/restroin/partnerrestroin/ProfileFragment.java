package in.restroin.partnerrestroin;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    private final String API_BASE_URL = "https://www.restroin.in/developers/api/v2/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().client(httpClient.build()).baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //TODO: Call the object for the profile model using the partners id and the access key given in the shared preferences
        SwipeRefreshLayout dashboardRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.profile_swipe_layout);
        dashboardRefreshLayout.setVisibility(View.GONE);
        TextView error_text = (TextView) view.findViewById(R.id.error_text_view);
        error_text.setVisibility(View.GONE);
        RelativeLayout need_help = (RelativeLayout) view.findViewById(R.id.need_help);
        need_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "9110466718"));
                if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 200);
                    return;
                }
                startActivity(intent);
            }
        });
        ProgressBar loadingProgressBar = (ProgressBar) view.findViewById(R.id.profileProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE);
        getProfileDetails(new SavedPreferences().getApiKey(view.getContext()), new SavedPreferences().getPartnerID(view.getContext()), view);
        //SignOut(view);
        RelativeLayout logout = (RelativeLayout) view.findViewById(R.id.log_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedPreferences savedPreferences = new SavedPreferences();
                savedPreferences.removeSharedPreferences(v.getContext());
                getActivity().finish();
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.report_issue);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CouponsActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private void getProfileDetails(String access_key, String partner_id, final View view){
        PartnerRestroINClient partnerRestroINClient = retrofit.create(PartnerRestroINClient.class);
        Call <ProfileModel> profileModelCall = partnerRestroINClient.getProfile(access_key, partner_id);
        profileModelCall.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<ProfileModel> call,@NonNull Response<ProfileModel> response) {
                view.findViewById(R.id.profile_swipe_layout).setVisibility(View.VISIBLE);
                view.findViewById(R.id.profileProgressBar).setVisibility(View.GONE);
                view.findViewById(R.id.error_text_view).setVisibility(View.GONE);
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
                view.findViewById(R.id.profile_swipe_layout).setVisibility(View.GONE);
                view.findViewById(R.id.profileProgressBar).setVisibility(View.GONE);
                view.findViewById(R.id.error_text_view).setVisibility(View.VISIBLE);
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
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
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
        new AlertDialog.Builder(getActivity().getApplicationContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
