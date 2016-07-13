package com.jehandadk.oriontest.data.api;


import rx.Subscriber;

/**
 * Created by jehandad.kamal on 6/24/2016.
 */
public abstract class LoadingSubscriber<T> extends Subscriber<T> {

    protected LoadingListener listener;

    public LoadingSubscriber(LoadingListener listener) {
        this.listener = listener;
    }

    public LoadingSubscriber(Subscriber<?> subscriber, LoadingListener listener) {
        super(subscriber);
        this.listener = listener;
    }

    public LoadingSubscriber(Subscriber<?> subscriber, boolean shareSubscriptions, LoadingListener listener) {
        super(subscriber, shareSubscriptions);
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (listener != null)
            listener.onLoadingStarted();
    }

    @Override
    public void onCompleted() {
        if (listener != null)
            listener.onLoadingFinished();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (listener != null)
            listener.onLoadingFinished();
    }

}
