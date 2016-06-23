package uma.hudss.database;

/*Takes the  ContactModel which is the tuple of contact details and
 *  put in database ContactsTable through ContactsDatabaseHelper
 * 
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import uma.hudss.Model.ContactModel;
import uma.hudss.SmartAlert.DatabaseListener;
import uma.hudss.database.ContactsContract.SmartContacts;

public class ContactsDatabaseWorker {

    static ContactsDatabaseWorker mInstance = null;
    static ContactsDatabaseHelper mdbHelper;
    SQLiteDatabase mdB;
    DatabaseObserver contentObserver = new DatabaseObserver();
    static DatabaseListener dblistenerFromRingToSilent;

    static DatabaseListener dblistenerFromSilentToRing;

    private ContactsDatabaseWorker() {
        // TODO Auto-generated constructor stub


    }

    public static ContactsDatabaseWorker getInstance() {
        if (mInstance == null) {
            mInstance = new ContactsDatabaseWorker();
        }
        return mInstance;
    }

    public void registerListener(DatabaseListener dbListener, int ringToSilent) {
        if (ringToSilent == AppPreferences.RING_TO_SILENT)
            this.dblistenerFromRingToSilent = dbListener;
        else
            this.dblistenerFromSilentToRing = dbListener;

    }

    public static void AddSmartContact(Context mContext, ContactModel contactModel, int silentToRing) {
        // if(mdbHelper==null)
        mdbHelper = new ContactsDatabaseHelper(mContext);

        SQLiteDatabase db = mdbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SmartContacts.SMART_CONTACTS_COLUMN_CONTACT_NAME,
                contactModel.GetContactName());
        cv.put(SmartContacts.SMART_CONTACTS_COLUMN_MAX_NUMBER_OF_CALLS,
                contactModel.GetMaximumNumberOfCalls());
        cv.put(SmartContacts.SMART_CONTACTS_COLUMN_PHONE_NUMBER,
                contactModel.GetPhoneNumber());
        cv.put(SmartContacts.SMART_CONTACTS_COLUMN_SILENT_TO_RING, silentToRing);
        db.insert(SmartContacts.SMART_CONTACTS_TABLE_NAME, null, cv);
        //Update the database when contact is added
        if(silentToRing==AppPreferences.RING_TO_SILENT) {
            if (dblistenerFromRingToSilent != null)
                dblistenerFromRingToSilent.onChange();
        }
        else{
            if (dblistenerFromSilentToRing != null)
                dblistenerFromSilentToRing.onChange();
        }
        db.close();
    }

    public static ContactModel getContact(Context mContext, String PhoneNumber, int silentToRing) {
        // if(mdbHelper==null)
        mdbHelper = new ContactsDatabaseHelper(mContext);
        ContactModel contactModel = null;

        String selection = SmartContacts.SMART_CONTACTS_COLUMN_PHONE_NUMBER
                + " =? AND " + SmartContacts.SMART_CONTACTS_COLUMN_SILENT_TO_RING + " =?";
        String[] selectionArgs = new String[]{PhoneNumber, String.valueOf(silentToRing)};
        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        Cursor cursor = db.query(SmartContacts.SMART_CONTACTS_TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            String a = cursor.getString(0);
            contactModel = new ContactModel(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2));
        }
        return contactModel;

    }

    public static int deleteContact(Context mContext, String phoneNumber, int silentToRing) {
        // if(mdbHelper==null)
        mdbHelper = new ContactsDatabaseHelper(mContext);
        SQLiteDatabase db = mdbHelper.getWritableDatabase();
        String whereClause = SmartContacts.SMART_CONTACTS_COLUMN_PHONE_NUMBER
                + " =? AND" + SmartContacts.SMART_CONTACTS_COLUMN_SILENT_TO_RING + " =?";
        String[] whereArgs = new String[]{phoneNumber, String.valueOf(silentToRing)};
        return db.delete(SmartContacts.SMART_CONTACTS_TABLE_NAME, whereClause,
                whereArgs);

    }

    public static int deleteAll(Context mContext) {
        // if(mdbHelper==null)
        mdbHelper = new ContactsDatabaseHelper(mContext);
        SQLiteDatabase db = mdbHelper.getWritableDatabase();

        return db.delete(SmartContacts.SMART_CONTACTS_TABLE_NAME, null, null);

    }

    public Cursor getAllDataFromRIngToSilent(Context mContext) {
        mdbHelper = new ContactsDatabaseHelper(mContext);

        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        String SQLQuery = " SELECT  " +
                // + ContactsContract.SmartContacts.SMART_CONTACTS_COLUMN_ID
                "* FROM " + SmartContacts.SMART_CONTACTS_TABLE_NAME + " WHERE "
                + SmartContacts.SMART_CONTACTS_COLUMN_SILENT_TO_RING + " = " + AppPreferences.RING_TO_SILENT;
        Cursor c = db.rawQuery(SQLQuery, null);

        return db.rawQuery(SQLQuery, null);

    }

    public Cursor getAllDataFromSilentToRing(Context mContext) {
        mdbHelper = new ContactsDatabaseHelper(mContext);

        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        String SQLQuery = " SELECT  " +
                // + ContactsContract.SmartContacts.SMART_CONTACTS_COLUMN_ID
                "* FROM " + SmartContacts.SMART_CONTACTS_TABLE_NAME + " WHERE "
                + SmartContacts.SMART_CONTACTS_COLUMN_SILENT_TO_RING + " = " + AppPreferences.SILENT_TO_RING;
        Cursor c = db.rawQuery(SQLQuery, null);

        return db.rawQuery(SQLQuery, null);

    }

    private class DatabaseObserver extends ContentObserver {

        public DatabaseObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }

    }

}
