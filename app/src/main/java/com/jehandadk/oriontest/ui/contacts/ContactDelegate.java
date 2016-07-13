package com.jehandadk.oriontest.ui.contacts;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;
import com.jehandadk.oriontest.R;
import com.jehandadk.oriontest.data.models.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jehandad.kamal on 7/13/2016.
 */
public class ContactDelegate implements AdapterDelegate<List<?>> {


    private final Activity activity;
    private final LayoutInflater inflater;
    ContactClickListener listener;

    public ContactDelegate(Activity activity, ContactDelegate.ContactClickListener listener) {
        this.listener = listener;
        this.activity = activity;
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return (items.get(position) instanceof Contact);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ContactItemViewHolder(inflater.inflate(R.layout.view_item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<?> items, int position, @NonNull RecyclerView.ViewHolder holder) {
        Contact contact = (Contact) items.get(position);
        ContactItemViewHolder vh = (ContactItemViewHolder) holder;

        vh.title.setText(contact.getName());
        vh.subtitle.setText(contact.getEmail());
    }

    public interface ContactClickListener {
        void contactClicked(int position);
    }

    public class ContactItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.subtitle)
        public TextView subtitle;

        public ContactItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.contactClicked(getAdapterPosition());
                }
            });
        }
    }
}
