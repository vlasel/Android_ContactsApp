package by.htp.vlas.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import contacts.vlas.htp.by.contactsapp.R;

/**
 * Created by _guest on 02.02.2015.
 */
public class ContactActivity extends Activity {

//    private static Contact contact = new Contact();
////    private static final DateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//    static {
//        Calendar sCalendar = Calendar.getInstance();
//        sCalendar.set(1990, 10, 1);
//        contact.setName("Ivan Ivanov");
//        contact.setPhone("7775533");
//        contact.setAddress("Skoriny 58");
//        contact.setEmail("ivan@gmail.com");
//        contact.setBirthDate(sCalendar.getTime());
//        contact.setOccupation("Android Developer");
//    }

    @InjectView(R.id.name)
    TextView mNameView;

    @InjectView(R.id.phone)
    TextView mPhoneView;

    @InjectView(R.id.email)
    TextView mEmailView;

    @InjectView(R.id.address)
    TextView mAddressView;

    @InjectView(R.id.birthdate)
    TextView mBirthDateView;

    @InjectView(R.id.occupation)
    TextView mOccupationView;

    @InjectView(R.id.btn_save)
    Button mBtnSave;

    public static final String EXTRA_CONTACT_ID = "contact_id";
    public static final String EXTRA_CONTACT_EDITABLE = "contact_editable";
    private final int ACTIVITY_EDIT_MODE_REQUEST = Math.abs("contact_edit_mode".hashCode());
    private final String ACTIVITY_EDIT_MODE_IS_CHANGED_KEY = "is_changed";

    private boolean mEditable;

    ContactStorage mContactStorage = new ContactStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Contact contact = mContactStorage.getByPosition(intent.getIntExtra(EXTRA_CONTACT_ID, 0));
        boolean contactEditable = intent.getBooleanExtra(EXTRA_CONTACT_EDITABLE, Contact.NOT_EDITABLE);

        setEditable(contactEditable);
        setData(contact);
    }

    private void setData(Contact contact) {
        if (contact == null) {
            return;
        }
        mNameView.setText(contact.getName());
        mPhoneView.setText(contact.getPhone());
        mEmailView.setText(contact.getEmail());
        mAddressView.setText(contact.getAddress());
        mBirthDateView.setText(DateFormat.getDateFormat(this).format(contact.getBirthDate()));
        mOccupationView.setText(contact.getOccupation());
    }

    //------------------------------ activity in edit mode -----------------------------------
    private void invokeInEditMode() {
        Intent intent = new Intent(this, getClass());
        this.startActivityForResult(intent, ACTIVITY_EDIT_MODE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == ACTIVITY_EDIT_MODE_REQUEST && resultCode == Activity.RESULT_OK) {
            boolean isChanged = data.getBooleanExtra(ACTIVITY_EDIT_MODE_IS_CHANGED_KEY, false);
        }
    }

    @OnClick(R.id.btn_save)
    private void btnSaveAction() {

    }

    private void setEditable(boolean pEditable) {
        if (!pEditable) {
            mNameView.setKeyListener(null);
            mPhoneView.setKeyListener(null);
            mEmailView.setKeyListener(null);
            mAddressView.setKeyListener(null);
            mBirthDateView.setKeyListener(null);
            mOccupationView.setKeyListener(null);
            mBtnSave.setVisibility(View.GONE);
        }
    }
    //------------------------------ activity in edit mode -----------------------------------
}
