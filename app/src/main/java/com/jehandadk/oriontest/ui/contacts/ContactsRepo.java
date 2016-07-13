package com.jehandadk.oriontest.ui.contacts;

import com.jehandadk.oriontest.data.api.IApiClient;
import com.jehandadk.oriontest.data.models.Contact;

import java.util.List;

import rx.Observable;

/**
 * Allows reusing and refreshing the underlying observable.
 * <p>
 * Created by jehandad.kamal on 7/13/2016.
 */
public class ContactsRepo {

    private final IApiClient api;
    private Observable<List<Contact>> contactsObservable;

    public ContactsRepo(IApiClient api) {
        this.api = api;
    }

    private void buildObservable() {
        contactsObservable = api.contacts().cache();
    }

    public Observable<List<Contact>> getContactsObservable() {
        if (contactsObservable == null) buildObservable();
        return contactsObservable;
    }

    public void reset() {
        buildObservable();
    }
}
