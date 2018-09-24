package in.restroin.partnerrestroin.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import in.restroin.partnerrestroin.R;
import in.restroin.partnerrestroin.models.NotificationsModel;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    List<NotificationsModel> notifications;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView notification_phase, notification, notification_id;
        public ViewHolder(View itemView){
            super(itemView);
            notification = (TextView) itemView.findViewById(R.id.notification_text);
            notification_phase = (TextView) itemView.findViewById(R.id.booking_phase);
            notification_id = (TextView) itemView.findViewById(R.id.booking_id);
        }
    }

    public NotificationsAdapter(List<NotificationsModel> notifications){
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_booking_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        holder.notification_id.setText("Booking ID: #" + notifications.get(position).getBooking_id());
        holder.notification.setText(notifications.get(position).getNotification_text());
        holder.notification_phase.setText(notifications.get(position).getNotification_type());
        if(notifications.get(position).getNotification_type().equals("A") || notifications.get(position).getNotification_type().equals("C")){
            holder.notification_phase.setBackgroundResource(R.drawable.layout_circular_background);
        }else if(notifications.get(position).getNotification_type().equals("P")){
            holder.notification_phase.setBackgroundResource(R.drawable.layout_circular_background_yellow);
        } else {
            holder.notification_phase.setBackgroundResource(R.drawable.layout_circular_background_red);
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
