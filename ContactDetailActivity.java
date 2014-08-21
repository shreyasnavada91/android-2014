package com.example.android.contacts;
import android.app.Activity; import android.widget.Toast;
public class ContactDetailActivity extends Activity {
    @Override protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
    }
}


// onBackPressed