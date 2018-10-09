package in.restroin.partnerrestroin.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.library.AnimateCheckBox;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.CouponsActivity;
import in.restroin.partnerrestroin.R;
import in.restroin.partnerrestroin.models.CouponsModel;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder>{
    private List<CouponsModel> couponsModels = new ArrayList<>();
    private List<String> selectedCoupons = new ArrayList<String>();
    public List<String> collectedCouponsWithoutQuot = new ArrayList<String>();
    private int SelectedItem = 0;

    public List<String> getSelectedCoupons() {
        return selectedCoupons;
    }



    public CouponsAdapter(List<CouponsModel> couponsModels) {
        this.couponsModels = couponsModels;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CheckBox coupon_name;
        TextView description, coupon_id;
        ImageView  coupon_image;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardLayoutCoupon);
            coupon_name = (CheckBox) itemView.findViewById(R.id.coupon_name);
            description = (TextView) itemView.findViewById(R.id.coupon_description);
            coupon_id = (TextView) itemView.findViewById(R.id.coupon_id);
            coupon_image = (ImageView) itemView.findViewById(R.id.coupon_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_coupon_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CouponsModel couponsModel = couponsModels.get(position);
        holder.coupon_name.setText(couponsModels.get(position).getCoupon_code());
        holder.description.setText(couponsModels.get(position).getDescription());
        holder.coupon_id.setText(couponsModels.get(position).getCoupon_id());
        Uri coupon_image_path = Uri.parse("https://www.restroin.in/" + couponsModels.get(position).getCoupon_image());
        Picasso.get().load(coupon_image_path).into(holder.coupon_image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(collectedCouponsWithoutQuot.contains(couponsModel.getCoupon_id())){
                    collectedCouponsWithoutQuot.remove(couponsModel.getCoupon_id());
                    selectedCoupons.remove('"' + couponsModel.getCoupon_id() + '"');
                    holder.coupon_name.setChecked(false);
                } else {
                    collectedCouponsWithoutQuot.add(couponsModel.getCoupon_id());
                    selectedCoupons.add('"' + couponsModel.getCoupon_id() + '"');
                    holder.coupon_name.setChecked(true);
                }
            }
        });
        if(collectedCouponsWithoutQuot.contains(couponsModel.getCoupon_id())){
            holder.coupon_name.setChecked(true);
        } else {
            holder.coupon_name.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return couponsModels.size();
    }
}
