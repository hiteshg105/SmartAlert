package uma.hudss.database;
/*Class to define the Strings To be used in database
 * 
 */

import android.provider.BaseColumns;

public final class ContactsContract {


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ContactsContract() {
        // TODO Auto-generated constructor stub
    }

    public static abstract class SmartContacts implements BaseColumns {


        public static final String SMART_CONTACTS_DATABASE_NAME = "smartcontacts.db";
        public static final String SMART_CONTACTS_TABLE_NAME = "smartcontactstable";
        public static final String _ID = "_id";
        // Silent to Ring - 1
        // Ring to Silent -2
        public static final String SMART_CONTACTS_COLUMN_SILENT_TO_RING = "smartcontactsphonenumbertoring";
        public static final String SMART_CONTACTS_COLUMN_PHONE_NUMBER = "smartcontactsphonenumber";
        public static final String SMART_CONTACTS_COLUMN_PHONE_NUMBER_ID = "smartcontactsphonenumberid";
        public static final String SMART_CONTACTS_COLUMN_CONTACT_NAME = "smartcontactscontactname";
        public static final String SMART_CONTACTS_COLUMN_MAX_NUMBER_OF_CALLS = "smartcontactsmaxnumberofcalls";//Maximum Number of calls by which Sound profile will be activated
        public static final String SMART_CONTACTS_COLUMN_CURR_NUMBER_OF_CALLS = "smartcontactscurrnumberofcalls";//Current number of calls made by a single number
        public static final String SMART_CONTACTS_COLUMN_DURATION_OF_SINGLE_CALL = "smartcontactsdurationofsinglecall";//Duration of single call
        public static final String SMART_CONTACTS_COLUMN_MAX_NUMBERS_ALLOWED = "smartcontactsmaxnumbersallowed";//Total count of numbers availing this feature
        public static final String SMART_CONTACTS_COLUMN_COUNT_AWAKE = "smartcontactscountawake";//TOTAL count of number awaken by smart Alert
        public static final String SMART_CONTACTS_COLUMN_COUNT_AWAKE_IN_DAY = "smartcontactscountawakeinday";//TOTAL count of number awaken by smart Alert in a day

        public static final String SMART_CONTACTS_CREATE_TABLE = "CREATE TABLE " +
                SMART_CONTACTS_TABLE_NAME + "( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SMART_CONTACTS_COLUMN_SILENT_TO_RING + " INTEGER," +
                SMART_CONTACTS_COLUMN_PHONE_NUMBER + " INTEGER, " +
                SMART_CONTACTS_COLUMN_PHONE_NUMBER_ID + " INTEGER, " +
                SMART_CONTACTS_COLUMN_CONTACT_NAME + " TEXT, " +
                SMART_CONTACTS_COLUMN_MAX_NUMBER_OF_CALLS + " INTEGER, " +
                SMART_CONTACTS_COLUMN_CURR_NUMBER_OF_CALLS + " INTEGER, " +
                SMART_CONTACTS_COLUMN_DURATION_OF_SINGLE_CALL + " INTEGER, " +
                SMART_CONTACTS_COLUMN_MAX_NUMBERS_ALLOWED + " INTEGER, " +
                SMART_CONTACTS_COLUMN_COUNT_AWAKE + " INTEGER, " +
                SMART_CONTACTS_COLUMN_COUNT_AWAKE_IN_DAY + " INTEGER " + ")";


        public static final int SMART_CONTACTS_DATABASE_VERSION = 3;


    }


}
