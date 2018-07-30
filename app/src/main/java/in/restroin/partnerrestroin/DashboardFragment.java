package in.restroin.partnerrestroin;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.adapters.ReviewsAdapter;
import in.restroin.partnerrestroin.interfaces.OnLoadMoreListener;
import in.restroin.partnerrestroin.models.ReviewsModel;


public class DashboardFragment extends Fragment {

    List<ReviewsModel> reviews = new ArrayList<>();

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View DashBoardView =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        final RecyclerView reviews_recycler = (RecyclerView)DashBoardView.findViewById(R.id.post_and_reviews_recycler);
        reviews_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        for(int i=0; i<10; i++) {
            reviews.add(new ReviewsModel("Chethan Jagannatha Kulkarni", "This is truly a good restaurant and I was spent around Rs. 3000. It was surely worth it absolutely. Here was good hospitality and service", R.mipmap.pp, (float) 4));
        }
        final ReviewsAdapter adapter = new ReviewsAdapter(reviews);
        reviews_recycler.setAdapter(adapter);
        return DashBoardView;
    }
}
