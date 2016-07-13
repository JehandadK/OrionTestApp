package com.jehandadk.oriontest.modules;

import com.jehandadk.oriontest.ui.contactdetails.ContactDetailActivity;
import com.jehandadk.oriontest.ui.contacts.ContactsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {AppModule.class, ApiModule.class}
)
public interface MainComponent {

    void inject(ContactsActivity activity);

    void inject(ContactDetailActivity contactDetailActivity);
}