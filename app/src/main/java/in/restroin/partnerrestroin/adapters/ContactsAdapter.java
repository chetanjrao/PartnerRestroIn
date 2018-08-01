/*
 * Copyright (c) 2018. An change in this code must be done under my supervision and any misuse my lead to legal actions
 */

package in.restroin.partnerrestroin.adapters;

import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.restroin.partnerrestroin.R;
import in.restroin.partnerrestroin.models.ContactsModel;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<ContactsModel> contacts;

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView contactsimage;
        ImageView call;
        ImageView email;
        TextView customer_name;
        public ViewHolder(View itemView) {
            super(itemView);
            contactsimage = (CircleImageView) itemView.findViewById(R.id.contats_list_image);
            call = (ImageView) itemView.findViewById(R.id.call);
            email = (ImageView) itemView.findViewById(R.id.email);
            customer_name = (TextView) itemView.findViewById(R.id.contacts_list_customer_name);
        }
    }

    public ContactsAdapter(List<ContactsModel> contacts){
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts_list, parent, false);
        return new ViewHolder(contactsView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.customer_name.setText(contacts.get(position).getCustomer_name());
        holder.call.setContentDescription(contacts.get(position).getCustomer_number());
        holder.email.setContentDescription(contacts.get(position).getCustomer_email());
        holder.contactsimage.setImageResource(contacts.get(position).getCustomer_image());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), holder.call.getContentDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), holder.email.getContentDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
