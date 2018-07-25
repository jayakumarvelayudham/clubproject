package customer.mmpl.tcs.com.clubproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.adapter.GiftsAdapter;
import customer.mmpl.tcs.com.clubproject.adapter.StoreAdapter;
import customer.mmpl.tcs.com.clubproject.modul.Movies;
import customer.mmpl.tcs.com.clubproject.volley.Contacts_URL;
import customer.mmpl.tcs.com.clubproject.volley.NetworkController;
import customer.mmpl.tcs.com.clubproject.volley.VolleyRequest;

/**
 * Created by BeAsT on 22-Mar-18.
 */

public class GiftsFragment extends Fragment {

    private static final String TAG = "GiftsFragment";
    private RecyclerView recyclerView;
    private List<Movies> movieList;
    private GiftsAdapter giftsAdapter;
    private StoreAdapter mAdapter1;
    public NetworkController networkController;
    public VolleyRequest volleyRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gift, container, false);

        recyclerView = view.findViewById(R.id.recycler_view1);
        movieList = new ArrayList<>();
        giftsAdapter = new GiftsAdapter(getContext(), movieList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(giftsAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchStoreItems();

        return view;
    }

    private void fetchStoreItems() {
        networkController = NetworkController.getNetworkController(getContext());
        volleyRequest = VolleyRequest.getInstances(getContext());
        networkController.MethodPost1(Contacts_URL.test, new NetworkController.VolleyInterfaceArray() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
//                Log.e(TAG, "onSuccessssssssssssssssssssss: " + jsonArray );
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Movies movies = new Movies();
                        movies.setImage(jsonObject.getString("image"));
                        movies.setTitle(jsonObject.getString("title"));
                        movieList.add(movies);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }finally {
                        giftsAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onError(VolleyError error) {
//                Log.e(TAG, "errorrrrrrrrrrrrrrrrrr: " + error );
            }
        });
    }
}
