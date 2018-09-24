package in.restroin.partnerrestroin.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.R;
import in.restroin.partnerrestroin.models.CouponsModel;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder>{
    private List<CouponsModel> couponsModels = new ArrayList<>();

    public CouponsAdapter(List<CouponsModel> couponsModels) {
        this.couponsModels = couponsModels;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkedTextView;
        TextView description, coupon_id;
        public ViewHolder(View itemView) {
            super(itemView);
            checkedTextView = (CheckBox) itemView.findViewById(R.id.coupon_check_box__);
            description = (TextView) itemView.findViewById(R.id.coupon_description);
            coupon_id = (TextView) itemView.findViewById(R.id.coupon_id__);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_check_box_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.checkedTextView.setText(couponsModels.get(position).getCoupon_code());
        holder.description.setText("Description: " + couponsModels.get(position).getDescription());
        holder.coupon_id.setText(couponsModels.get(position).getCoupon_id());
    }

    @Override
    public int getItemCount() {
        return couponsModels.size();
    }
}
