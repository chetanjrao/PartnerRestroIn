package in.restroin.partnerrestroin;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
        GraphView graphView = (GraphView) DashBoardView.findViewById(R.id.weekly_analytics);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>( new DataPoint[]{
                new DataPoint(1, 1),
                new DataPoint(2, 4),
                new DataPoint(3, 2),
                new DataPoint(4, 3)
        });
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(30);
        series.setThickness(1);
        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graphView.addSeries(series);
        final RecyclerView reviews_recycler = (RecyclerView)DashBoardView.findViewById(R.id.post_and_reviews_recycler);
        reviews_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        reviews.add(new ReviewsModel("Chethan Jagannatha Kulkarni", "This is truly a good restaurant and I was spent around Rs. 3000. It was surely worth it absolutely. Here was good hospitality and service", R.mipmap.pp, (float) 4.5));
        reviews.add(new ReviewsModel("Chethan Kulkarni", "This is truly a good restaurant and I was spent around Rs. 200. It was surely worth it absolutely. Here was good hospitality and service", R.mipmap.pp, (float) 3));
        reviews.add(new ReviewsModel("Chethan Jagannatha Kulkarni", "This is truly a good restaurant and I was spent around Rs. 1000. It was surely worth it absolutely. Here was good hospitality and service", R.mipmap.pp, (float) 4.5));
        final ReviewsAdapter adapter = new ReviewsAdapter(reviews);
        reviews_recycler.setAdapter(adapter);
        return DashBoardView;
    }
}
