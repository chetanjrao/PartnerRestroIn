package in.restroin.partnerrestroin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.BookingActivity;
import in.restroin.partnerrestroin.R;
import in.restroin.partnerrestroin.adapters.NotificationsAdapter;
import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.BookingModel;
import in.restroin.partnerrestroin.models.NotificationsModel;
import in.restroin.partnerrestroin.utils.SavedPreferences;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompletedFragment extends Fragment {
    private final String API_BASE_URL = "https://www.restroin.in/developers/api/v2/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().client(httpClient.build()).baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private Retrofit retrofit = builder.build();
    public CompletedFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed_bookings, container, false);
        RecyclerView NotificationRecycler = (RecyclerView) view.findViewById(R.id.bookings_recyclerView);
        addBookingData(NotificationRecycler, new SavedPreferences().getApiKey(view.getContext()), new SavedPreferences().getPartnerID(view.getContext()), new SavedPreferences().getRestaurantID(view.getContext()));
        return view;
    }

    private void addBookingData(final RecyclerView recyclerView, String access_key, String partner_id, String restaurant){
        PartnerRestroINClient bookingClient = retrofit.create(PartnerRestroINClient.class);
        Call<List<BookingModel>> call = bookingClient.getBookingData(access_key, partner_id, restaurant, "Completed");
        call.enqueue(new Callback<List<BookingModel>>() {
            @Override
            public void onResponse(Call<List<BookingModel>> call, Response<List<BookingModel>> response) {
                List<NotificationsModel> pendingBookings = new ArrayList<>();
                for (int i=0; i < response.body().size(); i++){
                    String body = "<b>" + response.body().get(i).getGuest_name() + "</b> 's Dining has been successfully completed. Thank you for your cooperation";
                    pendingBookings.add(new NotificationsModel(response.body().get(i).getBooking_id(), Html.fromHtml(body).toString(), "C"));
                }
                NotificationsAdapter adapter = new NotificationsAdapter(pendingBookings);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
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

                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View childView = rv.findChildViewUnder(e.getX(), e.getY());
                        if(childView != null && gestureDetector.onTouchEvent(e)) {
                            TextView phase = childView.findViewById(R.id.booking_phase);
                            String phase_ = phase.getText().toString();
                                Toast.makeText(getContext(), "This booking has been confirmed.", Toast.LENGTH_SHORT).show();

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
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BookingModel>> call, Throwable t) {

            }
        });
    }
}
