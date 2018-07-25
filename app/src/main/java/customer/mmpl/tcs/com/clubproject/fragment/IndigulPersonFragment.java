package customer.mmpl.tcs.com.clubproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.databasehelper.DataBaseHelper;
import customer.mmpl.tcs.com.clubproject.modul.Alluser_Module;

/**
 * Created by BeAsT on 19-Jul-18.
 */

public class IndigulPersonFragment extends Fragment {

    private static final String TAG = "IndigulPersonFragment";
    private DataBaseHelper dataBaseHelper;
    private ImageView userimage;
    private TextView mobile;
    private TextView email;
    private TextView address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_indigual, container, false); // activity_scrolling

        dataBaseHelper = new DataBaseHelper(getContext());
        String id = getArguments().getString("gameThemeID");
        Alluser_Module modules = dataBaseHelper.getUser(id);

        userimage = (ImageView) view.findViewById(R.id.userimage);
        mobile = (TextView) view.findViewById(R.id.usermobile);
        email = (TextView) view.findViewById(R.id.useremail);
        address = (TextView) view.findViewById(R.id.useraddress);

        Picasso.with(getContext()).load(modules.getImage_url()).into(userimage);
        mobile.setText(String.valueOf(modules.getPhone_no()));
        email.setText(modules.getEmail_id());
        address.setText(modules.getAddress());

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            ((MainActivity)getActivity()).setSupportActionBar(toolbar);
//        }

        return view;
    }
}
