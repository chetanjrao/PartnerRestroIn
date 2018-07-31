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

import in.restroin.partnerrestroin.adapters.ReviewsAdapter;
import in.restroin.partnerrestroin.models.ReviewsModel;


public class StaffContactFragment extends Fragment {

    List<ReviewsModel> reviews = new ArrayList<>();

    public StaffContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View StaffContacts =  inflater.inflate(R.layout.layout_contact_staff, container, false);
        return StaffContacts;
    }
}
