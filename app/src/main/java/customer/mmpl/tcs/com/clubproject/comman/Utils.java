package customer.mmpl.tcs.com.clubproject.comman;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

import customer.mmpl.tcs.com.clubproject.R;
import customer.mmpl.tcs.com.clubproject.activity.Login_Activity;
import customer.mmpl.tcs.com.clubproject.sharedpreference.Session;

/**
 * Created by AshokKumar on 09/01/2018.
 */

public class Utils {

    static android.app.Dialog localDialog;

    public static boolean isValidPhone(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 10) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    public static String getNetworkClass(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            default:
                return "Unknown";
        }
    }

    public static String getOS() {
        String OS = "";
        try {
            OS = Build.VERSION.RELEASE;
        } catch (Exception e) {
            OS = "";
        }
        return "Android " + OS;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }

    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        String IMEI = "";

        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return TODO;
            }
            IMEI = mTelephonyManager.getDeviceId();
        } catch (Exception e) {
            IMEI = "";
        }
        return IMEI;
    }


    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static void showSimpleDialog(final Context context, final String strTitle,
                                        final String strMessage, final String strPosBut, final String strNegBut, final View.OnClickListener paramOnClickListener) {
        localDialog = new android.app.Dialog(context, R.style.AlertDialogCustom);
        if (!((AppCompatActivity) context).isFinishing()) {

            ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                public void run() {

                    localDialog.setContentView(R.layout.dialog_action);
                    final TextView localTextView1 = (TextView) localDialog.findViewById(R.id.title_text);
                    TextView localTextView2 = (TextView) localDialog.findViewById(R.id.notification_text);
                    Button posButton = ((Button) localDialog.findViewById(R.id.primary_action));
                    Button negButton = ((Button) localDialog.findViewById(R.id.secondary_action));

                    if (!TextUtils.isEmpty(strTitle)) {
                        localTextView1.setText(strTitle);
                    } else {
                        localTextView1.setVisibility(View.GONE);
                    }
                    localTextView2.setText(Html.fromHtml(strMessage));
                    posButton.setText(strPosBut);
                    negButton.setText(strNegBut);
                    negButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            localDialog.dismiss();
                        }
                    });
                    posButton.setOnClickListener(paramOnClickListener);
                    localDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(localTextView1, InputMethodManager.SHOW_IMPLICIT);
                        }
                    });
                }
            });
            localDialog.show();
        }
    }

    public void showLogoutDialog(final Context context) {

        showSimpleDialog(context, "Confirm Logout", "Are you sure want to Logout?", "ok", "Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogoutAndLoginAgain(context);
                dismiss();
            }
        });
    }

    private void onLogoutAndLoginAgain(Context context) {
        Session session = new Session(context);
        session.clear();
        // After logout redirect user to Loing Activity
        // user is not logged in redirect him to Login Activity
        Intent i = new Intent(context, Login_Activity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        // Staring Login Activity
        (context).startActivity(i);
        ((Activity) context).finish();
    }

    public void dismiss() {
        if (localDialog != null && localDialog.isShowing()) {
            localDialog.dismiss();
        }
    }
}
