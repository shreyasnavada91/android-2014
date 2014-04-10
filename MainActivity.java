package com.example.android.contacts;
import android.app.Activity; import android.os.Bundle; import android.widget.ListView;
public class MainActivity extends Activity {
    private ListView listView; private ContactListAdapter adapter;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ContactListAdapter(this, getContacts()); listView.setAdapter(adapter);
    }
    private java.util.List<Contact> getContacts() {
        ContactDB db = new ContactDB(this); db.open();
        java.util.List<Contact> contacts = db.getAllContacts(); db.close(); return contacts;
    }
}


// Context menu added