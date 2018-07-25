package customer.mmpl.tcs.com.clubproject.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.adapter.GiftsAdapter;
import customer.mmpl.tcs.com.clubproject.adapter.StoreAdapter;
import customer.mmpl.tcs.com.clubproject.databasehelper.DataBaseHelper;
import customer.mmpl.tcs.com.clubproject.modul.Alluser_Module;
import customer.mmpl.tcs.com.clubproject.sharedpreference.Session;
import customer.mmpl.tcs.com.clubproject.volley.NetworkController;
import customer.mmpl.tcs.com.clubproject.volley.VolleyRequest;

/**
 * Created by BeAsT on 22-Mar-18.
 */

public class StoreFragment extends Fragment{

    private static final String TAG = "StoreFragment";
    private RecyclerView recyclerView;
    private List<Alluser_Module> alluser_modules;
    private ArrayList<String> movieList1;
    private StoreAdapter storeAdapter;
    private GiftsAdapter mAdapter1;
    public NetworkController networkController;
    public VolleyRequest volleyRequest;
    private Session session;
    private DataBaseHelper dataBaseHelper;

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        alluser_modules = new ArrayList<>();
        storeAdapter = new StoreAdapter(getActivity(), alluser_modules);
        storeAdapter.aMethod(new StoreAdapter.PassMethodInterface() {
            @Override
            public void Method_Interface(String id) {
                GetFun1(id);
            }
        });
        session = new Session(getActivity());
        dataBaseHelper = new DataBaseHelper(getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storeAdapter);
        fetchStoreItems();
        return view;
    }

    public void GetFun1(String a){
        IndigulPersonFragment fragment = new IndigulPersonFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayout, fragment,"fragment");
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("gameThemeID", a);
        fragment.setArguments(bundle);
    }


    private void fetchStoreItems() {

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
//                alluser_modules.clear();
                alluser_modules.addAll(dataBaseHelper.getAllUser());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                storeAdapter.notifyDataSetChanged();
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

}