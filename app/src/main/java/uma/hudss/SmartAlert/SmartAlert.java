package uma.hudss.SmartAlert;

import uma.hudss.database.AppPreferences;
import android.app.Application;
import android.database.Cursor;
import android.net.Uri;

public class SmartAlert extends Application {

	AppPreferences prefs;
	static SmartAlert smart;

	@Override
	public void onCreate() {
		super.onCreate();
		smart = this;
		prefs = new AppPreferences(smart);

	}

	public AppPreferences getPreferences() {
		return prefs;
	}

	public static SmartAlert getInstance() {
		return smart;
	}
	
	public String getContactName(final String phoneNumber) 
    {  
        Uri uri;
        String[] projection;

        
            uri = Uri.parse("content://com.android.contacts/phone_lookup");
            projection = new String[] { "display_name" };
        

        uri = Uri.withAppendedPath(uri, Uri.encode(phoneNumber)); 
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null); 

        String contactName = null;

        if (cursor.moveToFirst()) 
        { 
            contactName = cursor.getString(0);
        } 
        

        cursor.close();
        cursor = null;

        return contactName; 
    }

}
