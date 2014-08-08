package com.example.android.contacts;
import android.content.Context; import android.view.LayoutInflater;
import android.view.View; import android.view.ViewGroup; import android.widget.ArrayAdapter;
import android.widget.TextView; import java.util.List;
public class ContactListAdapter extends ArrayAdapter<Contact> {
    private List<Contact> contacts; private LayoutInflater inflater;
    public ContactListAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.contact_list_item, contacts);
        this.contacts = contacts; this.inflater = LayoutInflater.from(context);
    }
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) view = inflater.inflate(R.layout.contact_list_item, parent, false);
        Contact contact = contacts.get(position);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView email = (TextView) view.findViewById(R.id.email);
        name.setText(contact.getName()); email.setText(contact.getEmail());
        return view;
    }
}


// Cursor adapter