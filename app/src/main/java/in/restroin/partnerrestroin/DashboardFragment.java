package in.restroin.partnerrestroin;

import android.graphics.Color;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.restroin.partnerrestroin.adapters.ReviewsAdapter;
import in.restroin.partnerrestroin.interfaces.OnLoadMoreListener;
import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.ProfileModel;
import in.restroin.partnerrestroin.models.RestaurantProfileModel;
import in.restroin.partnerrestroin.models.ReviewsModel;
import in.restroin.partnerrestroin.platforms.RestaurantDeserializer;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DashboardFragment extends Fragment {

    private final String API_BASE_URL = "https://www.restroin.in/developers/api/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().client(httpClient.build()).baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    List<ReviewsModel> reviews = new ArrayList<>();

    public DashboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View DashBoardView =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        SwipeRefreshLayout dashboardRefreshLayout = (SwipeRefreshLayout) DashBoardView.findViewById(R.id.dashboard_swipe_refresh_layout);
        dashboardRefreshLayout.setVisibility(View.GONE);
        TextView error_text = (TextView) DashBoardView.findViewById(R.id.error_text_view);
        error_text.setVisibility(View.GONE);
        ProgressBar loadingProgressBar = (ProgressBar) DashBoardView.findViewById(R.id.dashboardProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE);
        getProfileDetails(new SavedPreferences().getApiKey(DashBoardView.getContext()), new SavedPreferences().getPartnerID(DashBoardView.getContext()), DashBoardView);
//        final RecyclerView reviews_recycler = (RecyclerView)DashBoardView.findViewById(R.id.post_and_reviews_recycler);
//        reviews_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        reviews.add(new ReviewsModel("Chethan Jagannatha Kulkarni", "This is truly a good restaurant and I was spent around Rs. 3000. It was surely worth it absolutely. Here was good hospitality and service", R.mipmap.pp, (float) 4.5));
//        reviews.add(new ReviewsModel("Chethan Kulkarni", "This is truly a good restaurant and I was spent around Rs. 200. It was surely worth it absolutely. Here was good hospitality and service", R.mipmap.pp, (float) 3));
//        reviews.add(new ReviewsModel("Chethan Jagannatha Kulkarni", "This is truly a good restaurant and I was spent around Rs. 1000. It was surely worth it absolutely. Here was good hospitality and service", R.mipmap.pp, (float) 4.5));
//        final ReviewsAdapter adapter = new ReviewsAdapter(reviews);
//        reviews_recycler.setAdapter(adapter);
        return DashBoardView;
    }


    private void getProfileDetails(String access_key, String partner_id, final View view){
        PartnerRestroINClient partnerRestroINClient = retrofit.create(PartnerRestroINClient.class);
        Call<RestaurantProfileModel> profileModelCall = partnerRestroINClient.getRestaurantData(access_key, partner_id, new SavedPreferences().getRestaurantID(view.getContext()));
        profileModelCall.enqueue(new Callback<RestaurantProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantProfileModel> call,@NonNull Response<RestaurantProfileModel> response) {
                assert response.body() != null;
                view.findViewById(R.id.dashboard_swipe_refresh_layout).setVisibility(View.VISIBLE);
                view.findViewById(R.id.error_text_view).setVisibility(View.GONE);
                view.findViewById(R.id.dashboardProgressBar).setVisibility(View.GONE);
                String restaurant_rating = response.body().getRestaurant_rating();
                String restaurant_earned = response.body().getTotal_earned();
                String restaurant_bookings = response.body().getTotal_bookings();
                String restaurant_customers = response.body().getTotal_customers();
                TextView total_bookings = (TextView) view.findViewById(R.id.total_dinings);
                TextView total_earnings = (TextView) view.findViewById(R.id.total_earnings);
                TextView total_customers = (TextView) view.findViewById(R.id.total_customers);
                TextView total_rating_text = (TextView) view.findViewById(R.id.overall_rating);
                RatingBar total_rating = (RatingBar) view.findViewById(R.id.overall_rating_bar);
                total_bookings.setText(restaurant_bookings);
                total_customers.setText(restaurant_customers);
                total_rating_text.setText(restaurant_rating);
                total_earnings.setText("\u20B9 " + restaurant_earned);
                if(restaurant_rating == null){
                    total_rating.setRating(0);
                } else {
                    total_rating.setRating(Float.parseFloat(restaurant_rating));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantProfileModel> call,@NonNull Throwable t) {
                view.findViewById(R.id.dashboard_swipe_refresh_layout).setVisibility(View.GONE);
                view.findViewById(R.id.error_text_view).setVisibility(View.VISIBLE);
                view.findViewById(R.id.dashboardProgressBar).setVisibility(View.GONE);
            }
        });
    }

}
