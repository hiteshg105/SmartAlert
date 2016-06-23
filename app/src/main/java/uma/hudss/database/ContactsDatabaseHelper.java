package uma.hudss.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

/*
 * Helper class To Initialize the table(s)
 */
public class ContactsDatabaseHelper extends SQLiteOpenHelper {


    public ContactsDatabaseHelper(Context context) {
        super(context, ContactsContract.SmartContacts.SMART_CONTACTS_DATABASE_NAME, null, ContactsContract.SmartContacts.SMART_CONTACTS_DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Create the SMartContactsTable
        ContactsTable.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        ContactsTable.onUpgrade(db, oldVersion, newVersion);
    }

}
