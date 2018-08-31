package com.kerimovalex.test.errors;

import android.content.Context;
import android.content.Intent;

import com.kerimovalex.test.R;

import retrofit2.HttpException;


public class Error {
    private Exception exception;
    private String message;
    private HttpException httpException;
    private Context context;

    public Error(Context context) {
        this.context = context;
    }

    public String handleError(Throwable throwable) {
        exception = (Exception) throwable;
        if (exception instanceof HttpException) {
            httpException = (HttpException) exception;
            switch (httpException.code()) {
                default:
                    message = context.getString(R.string.server_error);
                    break;
            }
        } else if (InternetConnectionUtils.isInternetConnectionError(exception)) {
            if (!InternetConnectionUtils.isInternetConnectionAvailable(context)) {
                message = context.getString(R.string.no_internet_connection);
            } else {
                message = context.getString(R.string.internet_connection_error);
            }
        }
        return message;
    }

}
