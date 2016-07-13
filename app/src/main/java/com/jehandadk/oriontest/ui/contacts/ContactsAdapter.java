package com.jehandadk.oriontest.ui.contacts;

import android.app.Activity;

import com.hannesdorfmann.adapterdelegates2.ListDelegationAdapter;

import java.util.List;

/**
 * Created by jehandad.kamal on 7/13/2016.
 */
public class ContactsAdapter extends ListDelegationAdapter<List<?>> {

    /**
     * @param activity because we need a context that can inflate and has a theme attached to it.
     * @param items
     * @param listener
     */
    public ContactsAdapter(Activity activity, List<?> items, ContactDelegate.ContactClickListener listener) {
        delegatesManager.addDelegate(new ContactDelegate(activity, listener));
        setItems(items);
    }
}
