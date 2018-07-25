package customer.mmpl.tcs.com.clubproject.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by BeAsT on 04-Apr-18.
 */

public class Session {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    public String KEY_IS_LOGIN = "isLogin";
    public String KEY_USERNAME = "username";
    public String KEY_PASSWORD = "password";
    public String ORGID = "orgid";

    public Session(Context context){
        this.context = context;
        mSharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public void saveLoginDetails(String username,String password){
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_IS_LOGIN, true);
        editor.apply();
    }

    public String getsaveLoginDetails(){
        return mSharedPreferences.getString(KEY_USERNAME,"");
    }

    public boolean isLoggedIn() {
        return mSharedPreferences.getBoolean(KEY_IS_LOGIN, false);
    }

    public String getOrgid(){
        return mSharedPreferences.getString(ORGID,null);
    }

    public void clear(){
        SharedPreferences.Editor editor= mSharedPreferences.edit();
        editor.putString(KEY_PASSWORD, "");
        editor.putString(KEY_USERNAME, "");
        editor.putBoolean(KEY_IS_LOGIN, false);
        editor.clear();
        editor.apply();
        editor.commit();
    }
}
