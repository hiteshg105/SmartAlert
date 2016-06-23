package uma.hudss.database;

/*SmartContactsTable which is a table Holding the details of numbers entered by user to enable SMART ALERT
 * Initialized by SmartContactsDatabaseHelper.java
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uma.hudss.SmartAlert.GlobalConstants;

public class ContactsTable {

	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(ContactsContract.SmartContacts.SMART_CONTACTS_CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		if(GlobalConstants.DEBUGGABLE)
		Log.d(ContactsTable.class.getName(), "Upgrading Database from "
				+ oldVersion + "to " + newVersion);

//		database.execSQL("DROP TABLE IF EXISTS"
//				+ ContactsContract.SmartContacts.SMART_CONTACTS_TABLE_NAME);
//		onCreate(database);
		if(oldVersion<=1){
			ContentValues cv = new ContentValues();
			cv.put(ContactsContract.SmartContacts.SMART_CONTACTS_COLUMN_SILENT_TO_RING, AppPreferences.SILENT_TO_RING);
			database.update(ContactsContract.SmartContacts.SMART_CONTACTS_TABLE_NAME,cv,null,null);
		}

	}

}
