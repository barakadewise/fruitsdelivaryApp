package com.example.fruitsapp.NetworkCheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.fruitsapp.Activities.Login;

public class Networkcheck {
    private static Networkcheck instance;
    private Context context;
    //* Private constructor to create a new instance of NetworkManager.
    private NetworkChangeReceiver networkChangeReceiver;

    private Networkcheck(Context context) {
        this.context = context.getApplicationContext();
        registerNetworkChangeReceiver();
    }

    public static synchronized Networkcheck getInstance(Login context) {
        if (instance == null) {
            instance = new Networkcheck(context);
        }
        return instance;
    }

    public void onDestroy() {
        unregisterNetworkChangeReceiver();
        instance = null;
    }

    private void registerNetworkChangeReceiver() {
        if (networkChangeReceiver == null) {
            networkChangeReceiver = new NetworkChangeReceiver();
            context.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    private void unregisterNetworkChangeReceiver() {
        if (networkChangeReceiver != null) {
            context.unregisterReceiver(networkChangeReceiver);
            networkChangeReceiver = null;
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (isNetworkConnected()) {
                // Network connection restored
                Toast.makeText(context, "Your connection is restored", Toast.LENGTH_SHORT).show();
            } else {
                // Network connection lost
                Toast.makeText(context, "Please connect to the internet!", Toast.LENGTH_SHORT).show();

            }
        }
    }
}



