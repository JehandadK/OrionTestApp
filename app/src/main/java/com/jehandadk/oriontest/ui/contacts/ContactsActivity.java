package com.jehandadk.oriontest.ui.contacts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jehandadk.oriontest.R;
import com.jehandadk.oriontest.common.DividerItemDecoration;
import com.jehandadk.oriontest.data.api.ErrorHandlingSubscriber;
import com.jehandadk.oriontest.data.api.ErrorListener;
import com.jehandadk.oriontest.data.api.IApiClient;
import com.jehandadk.oriontest.data.api.LoadingListener;
import com.jehandadk.oriontest.data.models.Contact;
import com.jehandadk.oriontest.ui.BaseActivity;
import com.jehandadk.oriontest.ui.contactdetails.ContactDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

public class ContactsActivity extends BaseActivity implements ErrorListener, LoadingListener, ContactDelegate.ContactClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.txtLoading)
    TextView txtLoading;
    @BindView(R.id.btnRetry)
    Button btnRetry;
    @BindView(R.id.txtError)
    TextView txtError;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Inject
    IApiClient api;

    @Inject
    ContactsRepo contactsRepo;

    private CompositeSubscription subscription;
    private ContactsAdapter adapter;
    private List items;
    private Comparator<Contact> descending = new Comparator<Contact>() {
        @Override
        public int compare(Contact o, Contact t1) {
            return t1.getName().compareTo(o.getName());
        }
    };
    private Comparator<Contact> ascending = new Comparator<Contact>() {
        @Override
        public int compare(Contact o, Contact t1) {
            return o.getName().compareTo(t1.getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApp().getMainComponent().inject(this);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        items = new ArrayList<>();
        adapter = new ContactsAdapter(this, items, this);
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onStart() {
        super.onStart();
        subscription = new CompositeSubscription();
        loadData();

    }

    private void loadData() {
        subscription.add(contactsRepo.getContactsObservable().subscribe(new ErrorHandlingSubscriber<List<Contact>>(this, this, this) {
            @Override
            public void onNext(List<Contact> contacts) {
                hideErrorView();
                if (contacts.size() == 0) showEmptyView();
                else {
                    hideEmptyView();
                    items.addAll(contacts);
                    adapter.notifyDataSetChanged();
                }
            }
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        subscription.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort_asc) {
            Collections.sort(items, ascending);
            adapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.action_sort_desc) {
            Collections.sort(items, descending);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String title, String message) {
        txtError.setText(message);
        btnRetry.setVisibility(View.VISIBLE);
        txtError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadingStarted() {
        txtLoading.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadingFinished() {
        txtLoading.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    private void hideErrorView() {
        btnRetry.setVisibility(View.INVISIBLE);
        txtError.setVisibility(View.INVISIBLE);
    }

    private void hideEmptyView() {
        txtEmpty.setVisibility(View.INVISIBLE);
    }

    private void showEmptyView() {
        txtEmpty.setVisibility(View.INVISIBLE);
    }


    @Override
    public void contactClicked(int contactPosition) {
        startActivity(ContactDetailActivity.newIntent(this, (Contact) items.get(contactPosition)));
    }

    @OnClick(R.id.btnRetry)
    public void retry() {
        hideErrorView();
        contactsRepo.reset();
        loadData();
    }
}
