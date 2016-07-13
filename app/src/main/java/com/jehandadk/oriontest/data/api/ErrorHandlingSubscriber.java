package com.jehandadk.oriontest.data.api;

import android.content.Context;

import com.jehandadk.oriontest.R;
import com.jehandadk.oriontest.data.models.ErrorResponse;

import java.io.IOException;

/**
 * To be used with {@link RxErrorHandlingCallAdapterFactory} which returns and instance of
 * {@link RetrofitException}
 * <p>
 * Should not be initialized with predifined string resources.
 * <p>
 * Created by jehandad.kamal on 6/28/2016.
 */
public abstract class ErrorHandlingSubscriber<T> extends LoadingSubscriber<T> {

    protected ErrorListener errorListener;
    private Context context;

    public ErrorHandlingSubscriber(LoadingListener listener, Context context, ErrorListener errorListener) {
        super(listener);
        this.context = context;
        this.errorListener = errorListener;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        RetrofitException err = null;
        try {
            err = (RetrofitException) e;
        } catch (Exception e1) {
            onUnknownError();
            return;
        }
        if (err.getKind() == RetrofitException.Kind.HTTP) {
            try {
                onHttpError(err);
            } catch (IOException e1) {
                e1.printStackTrace();
                onUnknownError();
            }
        } else if (err.getKind() == RetrofitException.Kind.NETWORK) {
            onNetworkOrParseException();
        } else {
            onUnknownError();
        }
    }

    protected void onUnknownError() {
        if (errorListener != null)
            errorListener.showError(context.getString(R.string.error), context.getString(R.string.msg_err_unknown));
    }

    protected void onNetworkOrParseException() {
        if (errorListener != null)
            errorListener.showError(context.getString(R.string.error), context.getString(R.string.msg_err_internet));
    }

    protected void onHttpError(RetrofitException err) throws IOException {
        ErrorResponse errors = err.getErrorBodyAs(ErrorResponse.class);
        if (errors == null || errors.getResponse() == null || errors.getResponse().size() == 0) {
            onUnknownError();
            return;
        }
        if (errorListener != null)
            errorListener.showError(context.getString(R.string.error), errors.getResponse().get(0).getErrorMessage());
    }

}
