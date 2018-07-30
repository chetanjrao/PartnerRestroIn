package in.restroin.partnerrestroin.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.restroin.partnerrestroin.R;
import in.restroin.partnerrestroin.interfaces.OnLoadMoreListener;
import in.restroin.partnerrestroin.models.ReviewsModel;

public class ReviewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ReviewsModel> reviewsModelList;
    private int VIEW_MORE = R.layout.reviews_view_more;
    private int VIEW_CELL = R.layout.layout_customer_reviews;


    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView reviewer_image;
        private TextView reviewer, review;
        private RatingBar review_rating;
        public ReviewsViewHolder(View itemView) {
            super(itemView);
            reviewer = (TextView)itemView.findViewById(R.id.review_profile_name);
            review = (TextView) itemView.findViewById(R.id.reviews_given);
            reviewer_image = (CircleImageView) itemView.findViewById(R.id.reviews_profile_image);
            review_rating = (RatingBar) itemView.findViewById(R.id.reviews_rating);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout view_all_layout;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            view_all_layout = (RelativeLayout) itemView.findViewById(R.id.view_all_reviews_layout);
        }
    }

    public ReviewsAdapter(List<ReviewsModel> list){
        this.reviewsModelList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == reviewsModelList.size()) ? VIEW_MORE : VIEW_CELL;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_CELL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_customer_reviews, parent, false);
            return new ReviewsViewHolder(view);
        } else {
            View Itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_view_more, parent, false);
            return new LoadingViewHolder(Itemview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ReviewsViewHolder){
            ReviewsModel reviews = reviewsModelList.get(position);
            ReviewsViewHolder reviewsViewHolder = (ReviewsViewHolder) holder;
            reviewsViewHolder.review.setText("\u201C " + reviews.getReview() +" \u201D");
            reviewsViewHolder.review_rating.setRating(reviews.getReview_rating());
            reviewsViewHolder.reviewer_image.setImageResource(reviews.getReviewer_image());
            reviewsViewHolder.reviewer.setText(reviews.reviewer_name);
        }
        if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.view_all_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Recycler Adapter", "You just clcicked View more");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return reviewsModelList.size() + 1;
    }

}
