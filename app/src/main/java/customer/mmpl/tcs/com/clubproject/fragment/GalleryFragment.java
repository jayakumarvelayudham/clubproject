package customer.mmpl.tcs.com.clubproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.adapter.GalleryFragmentAdapter;
import customer.mmpl.tcs.com.clubproject.volley.Contacts_URL;

/**
 * Created by BeAsT on 20-Jul-18.
 */

public class GalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";
    private RecyclerView recyclerView;
    private GalleryFragmentAdapter galleryFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_gallery, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        GetMethods();

        return view;
    }

    private void GetMethods() {
        String[] item = null;
        item = Contacts_URL.getOffersUrls();
//        Log.e(TAG, "GetMethodsssssssssssssssssssssssss: " + item.toString() );
        galleryFragmentAdapter = new GalleryFragmentAdapter(getContext(),item);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(galleryFragmentAdapter);
        galleryFragmentAdapter.Update(item);
    }
}
