package com.kerimovalex.test.errors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


public class InternetConnectionUtils {
    public static boolean isInternetConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isInternetConnectionError(Throwable error) {
        return isInternetConnectionErrorInternal(error) ||
                isInternetConnectionErrorInternal(error.getCause());
    }

    private static boolean isInternetConnectionErrorInternal(Throwable error) {
        return (error instanceof UnknownHostException ||
                error instanceof ConnectException ||
                error instanceof SocketTimeoutException);
    }
}
