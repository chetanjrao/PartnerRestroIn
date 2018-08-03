package in.restroin.partnerrestroin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.restroin.partnerrestroin.adapters.ContactsAdapter;
import in.restroin.partnerrestroin.adapters.ReviewsAdapter;
import in.restroin.partnerrestroin.models.ContactsModel;
import in.restroin.partnerrestroin.models.ReviewsModel;


public class CustomersContactFragment extends Fragment {

    List<ContactsModel> contacts = new ArrayList<>();

    public CustomersContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View CustomersContacts =  inflater.inflate(R.layout.layout_contact_customers, container, false);
        RecyclerView recyclerView = (RecyclerView) CustomersContacts.findViewById(R.id.customers_contact_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CustomersContacts.getContext());
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Jagannatha Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        contacts.add(new ContactsModel("Chethan Kulkarni", "9110466718", "chethanjkulkarni@gmail.com", R.mipmap.pp));
        recyclerView.setLayoutManager(layoutManager);
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        recyclerView.setAdapter(adapter);
        return CustomersContacts;
    }

}
