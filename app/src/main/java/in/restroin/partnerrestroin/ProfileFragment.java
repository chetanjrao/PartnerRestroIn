package in.restroin.partnerrestroin;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);;
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.status_restaurant_switch);
        if(switchCompat.isChecked()){
            switchCompat.setThumbTintList(ColorStateList.valueOf(Color.GREEN));
            switchCompat.setTrackResource(R.color.colorPrimary);
        }
        return view;

    }
}
