package uma.hudss.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import uma.hudss.SmartAlert.SmartAlertUtil;

public class ContactsChooserActivity extends Activity {
	String contactName, contactPhoneNumber, contactId, hasphoneNumber;
	final int PICK_CONTACT = 0;
	int mSilentToRing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		try {

			Intent intent = new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI);
			mSilentToRing= intent.getIntExtra(SmartAlertUtil.SILENT_TO_RING,0);
			startActivityForResult(intent, PICK_CONTACT);
		} catch (ActivityNotFoundException e) {
			// TODO Auto-generated catch block

		}
		// finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_CONTACT) {
			if (resultCode == Activity.RESULT_OK) {
				if (data != null && data.getData() != null) {
					try {
						Uri contactData = data.getData();
						Cursor c = getContentResolver().query(contactData,
								null, null, null, null);
						if (c != null) {
							c.moveToFirst();
							contactName = c
									.getString(c
											.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
							contactId = c
									.getString(c
											.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
							hasphoneNumber = c
									.getString(c
											.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));

							Dialog dialog = SmartAlertDialog
									.getDialogAddFromContact(
											ContactsChooserActivity.this,
											contactName, contactId,mSilentToRing);
							
							dialog.show();

							c.close();

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(resultCode==Activity.RESULT_CANCELED){
				finish();
			}
			
		}
	}
}
