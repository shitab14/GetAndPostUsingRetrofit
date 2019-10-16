package com.example.getandpostusingretrofit.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class CommonUtilities {

    public static boolean checkInternetStatus(Context context){
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        /** to get info of WIFI N/W : */
        final android.net.NetworkInfo wifi = conMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        /** to get info of mobile N/W : */
        final android.net.NetworkInfo mobile = conMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() && wifi.isConnected()) {
            Log.e("Is Net work? 1", "isNetWork:in 'isNetWork_if' is N/W Connected:"
                    + NetworkInfo.State.CONNECTED);
            return true;
        } else if (mobile != null && mobile.isAvailable() && mobile.isConnected()) {
            Log.e("Is Net work? 2", "isNetWork:in 'isNetWork_if' is N/W Connected:"
                    + NetworkInfo.State.CONNECTED);
            return true;
        } else if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            Log.e("Is Net work? 3", "isNetWork:in 'isNetWork_if' is N/W Connected:"
                    + NetworkInfo.State.CONNECTED);
            return true;
        }
        return false;
    }

}
