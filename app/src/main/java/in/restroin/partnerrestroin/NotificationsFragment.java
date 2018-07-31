package in.restroin.partnerrestroin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.adapters.NotificationsAdapter;
import in.restroin.partnerrestroin.models.NotificationsModel;


public class NotificationsFragment extends Fragment {

    List<NotificationsModel> notifications = new ArrayList<>();

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_notifications, container, false);
        RecyclerView NotificationRecycler = (RecyclerView) view.findViewById(R.id.notifications_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
        notifications.add(new NotificationsModel(1234, "Booking Pending with booking id #1234. Click to confirm booking", "B"));
        notifications.add(new NotificationsModel(1234, "Payment Pending with booking id #1234. Click to complete payment", "P"));
        NotificationsAdapter adapter = new NotificationsAdapter(notifications);
        NotificationRecycler.setLayoutManager(layoutManager);
        NotificationRecycler.setAdapter(adapter);
        return view;
    }
}
