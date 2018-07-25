package customer.mmpl.tcs.com.clubproject.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;

import customer.mmpl.tcs.com.clubproject.MainActivity;
import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.databasehelper.DataBaseHelper;
import customer.mmpl.tcs.com.clubproject.modul.Alluser_Module;
import customer.mmpl.tcs.com.clubproject.sharedpreference.Session;
import customer.mmpl.tcs.com.clubproject.volley.Contacts_URL;
import customer.mmpl.tcs.com.clubproject.volley.NetworkController;



public class Login_Activity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private NetworkController networkController;
    private Session session;
    private ProgressDialog progressDialog;
    private DataBaseHelper dataBaseHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        networkController = NetworkController.getNetworkController(this);
        session = new Session(this);
        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        dataBaseHelper = new DataBaseHelper(this);

    }

    @NonNull
    private SpannableStringBuilder setStarToLabel(String text) {
        String simple = text;
        String colored = "*";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();
        builder.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }


    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        progressDialog = new ProgressDialog(Login_Activity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email1 = textInputEditTextEmail.getText().toString();
        final String password1 = textInputEditTextPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess(email1, password1);

                    }
                }, 500);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(final String email, final String password) {

        HashMap<Object, Object> parms = new HashMap<>();
//        parms.put("IMEI", Utils.getIMEI(getApplicationContext()));
        parms.put("userName", email);
        parms.put("passWord", password);

        networkController.MethodPost(Request.Method.POST, Contacts_URL.login, new JSONObject(parms), new NetworkController.VolleyInterface() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    String status = jsonObject.getString("status");
                    if (status.equals("admin")) {
                        session.saveLoginDetails(email, password);
                        GetAllusermember();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GetAllusermember() {

        networkController.MethodPost(Request.Method.POST, Contacts_URL.allUsers, null, new NetworkController.VolleyInterface() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                    dataBaseHelper.deleteAll();
                try {
                    String s = jsonObject.getString("status");
                    if (s.equals("success")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("postData");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            progressDialog.dismiss();
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Log.e(TAG, "jsonObject11: " + jsonObject1 );
                            Alluser_Module alluser_module = new Alluser_Module();
                            alluser_module.setUser_id(jsonObject1.getString("user_id"));
                            alluser_module.setF_name(jsonObject1.getString("f_name"));
                            alluser_module.setPassword(jsonObject1.getInt("password"));
                            alluser_module.setEmail_id(jsonObject1.getString("email_id"));
                            alluser_module.setPhone_no(jsonObject1.getInt("Phone_no"));
                            alluser_module.setImage_url(jsonObject1.getString("image_url"));
                            alluser_module.setAddress(jsonObject1.getString("address"));
                            alluser_module.setProfession(jsonObject1.getString("profession"));
                            alluser_module.setGender(jsonObject1.getString("gender"));
                            alluser_module.setUser_dob(jsonObject1.getString("user_dob"));
                            alluser_module.setBlood_group(jsonObject1.getString("blood_group"));
                            alluser_module.setRole_id(jsonObject1.getString("role_id"));
                            alluser_module.setPosition(jsonObject1.getString("position"));
//                            alluser_module.setUpdate_date_time(jsonObject1.getString("update_date_time"));
                            dataBaseHelper.addUser_Module(alluser_module);
                            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    public void onLoginFailed() {
        Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_SHORT).show();
//        appCompatButtonLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Pattern eight = Pattern.compile(".{8}");


        String email = textInputEditTextEmail.getText().toString();
        String password = textInputEditTextPassword.getText().toString();


//        boolean a = ok(password,letter,digit,special,eight);
//
//        Log.e(TAG, "validateeeeeeeeeeeeeeeeeeeee: " + Boolean.toString(a) );

        if (email.isEmpty()) {
            textInputEditTextEmail.setError("enter a valid email address");
            valid = false;
        } else {
            textInputEditTextEmail.setError(null);
        }


//        if (!a) {
//            Log.e(TAG, "111111111111: " );
//            Toast.makeText(this, "Failerrrrrr", Toast.LENGTH_SHORT).show();
//
//        }else{
//            Toast.makeText(this, "Successssss", Toast.LENGTH_SHORT).show();
//            Log.e(TAG, "222222222: " );
//        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            textInputEditTextPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            textInputEditTextPassword.setError(null);
        }

        return valid;
    }

    public final boolean ok(String password, Pattern letter, Pattern digit, Pattern eight, Pattern special) {

        letter = Pattern.compile("[a-zA-z]");
        digit = Pattern.compile("[0-9]");
        special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        eight = Pattern.compile(".{8}");

        return eight.matcher(password).matches()
                && special.matcher(password).find()
                && digit.matcher(password).find()
                && letter.matcher(password).find();
    }


}
