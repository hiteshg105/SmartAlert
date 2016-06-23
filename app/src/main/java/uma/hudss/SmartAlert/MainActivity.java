package uma.hudss.SmartAlert;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import uma.hudss.Activity.NormalizePhoneUtilities;
import uma.hudss.Model.ContactModel;
import uma.hudss.R;
import uma.hudss.database.AppPreferences;
import uma.hudss.database.ContactsDatabaseWorker;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        Thread thread = new Thread() {
            @Override
            public void run() {
                String[] location = CountryUtilities
                        .detectCC_Country(MainActivity.this);
                if (location != null) {
                    Long cc = Long.parseLong(location[1]);
                    SmartAlert.getInstance().getPreferences()
                            .setCountry(location[0]);
                    SmartAlert.getInstance().getPreferences().setCountryCode(cc);
                }
            }
        };
        thread.start();
        String normalizednumber = NormalizePhoneUtilities.normalizePhoneNumber("+919958899225");
        ContactModel contactModel = new ContactModel("Hitesh", normalizednumber, String.valueOf(3));
        ContactsDatabaseWorker sm = ContactsDatabaseWorker.getInstance();

        sm.AddSmartContact(this,contactModel, AppPreferences.SILENT_TO_RING);
//       int deleted = sm.deleteAll();
//        LinearLayout l = (LinearLayout)findViewById(R.id.container);
//        String t =sm.getContact(String.valueOf(995891)).toString();
//        TextView tv = new TextView(this);
//        tv.setText(t);
//        l.addView(tv);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */


}
