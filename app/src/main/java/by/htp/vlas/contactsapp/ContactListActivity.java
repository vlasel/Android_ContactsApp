package by.htp.vlas.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import contacts.vlas.htp.by.contactsapp.R;

/**
 * Created by _guest on 06.02.2015.
 */
public class ContactListActivity extends Activity {

    static List<Contact> mContactList = new ArrayList<>();

    static {
        for (int i = 0; i < 30; i++) {
            mContactList.add(new Contact(
                    String.valueOf((int) ((Math.random() + 1) * 1111111))
                    , "Name" + i
                    , "mail" + i + "@server"
                    , "Street" + i
                    , new Date(1995 + i, 10, 15)
                    , "user" + i
            ));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new ContactAdapter(mContactList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContactListActivity.this, ContactListActivity.class);
            }

        });

    }
}
