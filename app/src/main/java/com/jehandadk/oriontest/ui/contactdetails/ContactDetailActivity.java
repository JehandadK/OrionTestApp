package com.jehandadk.oriontest.ui.contactdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jehandadk.oriontest.R;
import com.jehandadk.oriontest.data.api.Factory;
import com.jehandadk.oriontest.data.models.Contact;
import com.jehandadk.oriontest.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailActivity extends BaseActivity {

    private static final String KEY_CONTACT = "KEY_CONTACT";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtPhone)
    TextView txtPhone;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtWebsite)
    TextView txtWebsite;
    @BindView(R.id.txtCompany)
    TextView txtCompany;
    private Contact contact;

    public static Intent newIntent(Activity act, Contact contact) {
        Intent intent = new Intent(act, ContactDetailActivity.class);
        intent.putExtra(KEY_CONTACT, Factory.gson().toJson(contact));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApp().getMainComponent().inject(this);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);
        contact = Factory.gson().fromJson(getIntent().getStringExtra(KEY_CONTACT), Contact.class);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtName.setText(contact.getName());
        txtPhone.setText(contact.getName());
        txtAddress.setText(contact.getAddress().getCity());
        txtWebsite.setText(contact.getWebsite());
        txtCompany.setText(contact.getCompany().getName());
    }

}
