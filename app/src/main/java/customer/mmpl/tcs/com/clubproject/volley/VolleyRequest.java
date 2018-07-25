package customer.mmpl.tcs.com.clubproject.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by BeAsT on 22-Mar-18.
 */

public class VolleyRequest {

    private static final String TAG = "VolleyRequest";
    private RequestQueue mRequestQueue;
    private static VolleyRequest volleyRequest;
    private Context context;

    public VolleyRequest(Context context){
        this.context = context;
    }

    public static VolleyRequest getInstances(Context context) {
        if (volleyRequest == null){
            volleyRequest = new VolleyRequest(context);
        }
        return volleyRequest;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
