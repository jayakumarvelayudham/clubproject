package customer.mmpl.tcs.com.clubproject.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.databasehelper.DataBaseHelper;
import customer.mmpl.tcs.com.clubproject.modul.Alluser_Module;
import customer.mmpl.tcs.com.clubproject.sharedpreference.Session;
import customer.mmpl.tcs.com.clubproject.volley.Contacts_URL;
import customer.mmpl.tcs.com.clubproject.volley.NetworkController;

import static android.app.Activity.RESULT_OK;

/**
 * Created by BeAsT on 22-Mar-18.
 */

public class CartFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CartFragment";
    private int SELECT_IMAGE = 0;
    private Session session;
    private DataBaseHelper dataBaseHelper;
    private ImageView profile,edit;
    private Button Savebutton;
    private TextView name,location,blood_group,education,occupation,mobileNumber,gender,marriage,dob,email;
    private String ImageProfile;
    private NetworkController networkController;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        
        session = new Session(getContext());
        dataBaseHelper = new DataBaseHelper(getContext());
        networkController = NetworkController.getNetworkController(getContext());
        String id = session.getsaveLoginDetails();
        Alluser_Module modules = dataBaseHelper.getUser(id);
//        Log.e(TAG, "MMMMMMMMMMMMMMMMMMMMMM: " + modules.toString() );

        profile = (ImageView) view.findViewById(R.id.imageView);
        Savebutton = (Button) view.findViewById(R.id.save);
        edit = (ImageView) view.findViewById(R.id.edit);
        profile.setOnClickListener(this);
        edit.setOnClickListener(this);
        Savebutton.setOnClickListener(this);
        name = (TextView) view.findViewById(R.id.name);
        location = (TextView) view.findViewById(R.id.location);
        blood_group = (TextView) view.findViewById(R.id.blood_group);
        education = (TextView) view.findViewById(R.id.education);
        occupation = (TextView) view.findViewById(R.id.occupation);
        mobileNumber = (TextView) view.findViewById(R.id.mobileNumber);
        gender = (TextView) view.findViewById(R.id.gender);
        marriage = (TextView) view.findViewById(R.id.marriage);
        dob = (TextView) view.findViewById(R.id.dob);
        email = (TextView) view.findViewById(R.id.email);

//        Picasso.with(getContext()).load(modules.getImage_url()).into(profile);
        name.setText(modules.getF_name());
        location.setText(modules.getAddress());
        blood_group.setText(modules.getBlood_group());
//        education.setText(modules.getProfession());
        occupation.setText(modules.getProfession());
        mobileNumber.setText(String.valueOf(modules.getPhone_no()));
        gender.setText(modules.getGender());
//        marriage.setText(modules.getGender());
        dob.setText(modules.getUser_dob());
        email.setText(modules.getEmail_id());

        name.setEnabled(false);
        location.setEnabled(false);
        blood_group.setEnabled(false);
        occupation.setEnabled(false);
        mobileNumber.setEnabled(false);
        gender.setEnabled(false);
        dob.setEnabled(false);
        email.setEnabled(false);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit:
                EditOnClickMethod();
                break;
            case R.id.imageView:
                ProfileImage_Click();
                break;
            case R.id.save:
                SaveButtonClick();
                break;
        }
    }

    private void EditOnClickMethod() {
        name.setEnabled(true);
        location.setEnabled(true);
        blood_group.setEnabled(true);
        occupation.setEnabled(true);
        mobileNumber.setEnabled(true);
        gender.setEnabled(true);
        dob.setEnabled(true);
        email.setEnabled(true);
    }

    private void ProfileImage_Click() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"select image"),SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_IMAGE){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                    profile.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                    ImageProfile = Base64.encodeToString(stream.toByteArray(),Base64.DEFAULT);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void SaveButtonClick() {

        progressDialog = new ProgressDialog(getContext(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        HashMap<String,String> parm = new HashMap<>();
        parm.put("name",name.getText().toString());

        networkController.MethodPost(Request.Method.POST, Contacts_URL.allUsers, new JSONObject(parm), new NetworkController.VolleyInterface() {
            @Override
            public void onSuccess(JSONObject jsonObject) {

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
}
