package customer.mmpl.tcs.com.clubproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import customer.mmpl.tcs.com.clubproject.comman.Utils;
import customer.mmpl.tcs.com.clubproject.fragment.CartFragment;
import customer.mmpl.tcs.com.clubproject.fragment.GalleryFragment;
import customer.mmpl.tcs.com.clubproject.fragment.GiftsFragment;
import customer.mmpl.tcs.com.clubproject.fragment.StoreFragment;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    startActivity(new Intent(MainActivity.this, Home.class));
//                    return true;
//                case R.id.navigation_gifts:
//                    mTextMessage.setText(R.string.title_home);
//                    startActivity(new Intent(MainActivity.this, Home.class));
//                    return true;
//                case R.id.navigation_cart:
//                    mTextMessage.setText(R.string.title_cart);
//                    return true;
//                case R.id.navigation_profile:
//                    mTextMessage.setText(R.string.title_profile);
//                    return true;
//                case R.id.navigation_user:
//                    mTextMessage.setText(R.string.title_user);
//                    return true;
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        toolbar = getSupportActionBar();
//        toolbar.setTitle("Shop");
        loadFragment(new StoreFragment());

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new StoreFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_gifts:
                    fragment = new GiftsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.gallery:
                    fragment = new GalleryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
//                    toolbar.setTitle("Profile");
//                    fragment = new ProfileFragment();
//                    loadFragment(fragment);
                    logoutFunction();
                    return true;
            }

            return false;
        }
    };

    private void logoutFunction() {
        final Utils dialog = new Utils();
        dialog.showLogoutDialog(MainActivity.this);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
