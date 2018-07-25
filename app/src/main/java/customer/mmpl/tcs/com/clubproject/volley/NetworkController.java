package customer.mmpl.tcs.com.clubproject.volley;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by BeAsT on 22-Mar-18.
 */

public class NetworkController {

    private static NetworkController networkController;
    private VolleyRequest volleyRequest;
    private Context context;

    private NetworkController(Context context) {
        this.context = context;
        this.volleyRequest = VolleyRequest.getInstances(context);
    }

    public static NetworkController getNetworkController(Context context) {
        if (networkController == null) {
            networkController = new NetworkController(context);
        }
        return networkController;
    }


    public void MethodPost(int method, final String url, final JSONObject jsonObject, final VolleyInterface volleyInterface) {
        String tag_json_obj = "json_obj_req";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyInterface.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                volleyInterface.onError(error);
            }
        });
        volleyRequest.addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    public void MethodPost1(final String url, final VolleyInterfaceArray volleyInterfaceArray) {
        String tag_json_obj = "json_obj_req";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyInterfaceArray.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                volleyInterfaceArray.onError(error);
            }
        });
        volleyRequest.addToRequestQueue(request, tag_json_obj);
    }


    public void MethodPostWithMethod(int method, String url, JSONObject jsonObject, final String lastOrderid, final VolleyMultipleRequestInterface volleyInterface) {
        String tag_json_obj = "json_obj_req";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                volleyInterface.onSuccess(response,lastOrderid);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                volleyInterface.onError(error,lastOrderid);
            }
        });
        volleyRequest.addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public interface VolleyInterface {
        void onSuccess(JSONObject jsonObject);
        void onError(VolleyError error);
    }

    public interface VolleyInterfaceArray {
        void onSuccess(JSONArray jsonArray);
        void onError(VolleyError error);
    }

    public interface VolleyMultipleRequestInterface {
        void onSuccess(JSONObject jsonObject, String orderid);
        void onError(VolleyError error, String orderid);
    }


}
