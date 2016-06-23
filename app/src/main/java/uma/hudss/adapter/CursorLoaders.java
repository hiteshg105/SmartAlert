package uma.hudss.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import uma.hudss.database.ContactsDatabaseWorker;

public class CursorLoaders extends CursorLoader {

    ContactsDatabaseWorker databaseWorker;
    Context context;
    int mLoader;
    public static final int CONTACT_LOADER_RINGER_TO_SILENT = 1;
    public static final int CONTACT_LOADER_SILENT_TO_RING = 2;


    public CursorLoaders(int loaderId, Context context) {

        super(context);
        mLoader = loaderId;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Cursor loadInBackground() {
        // TODO Auto-generated method stub
        databaseWorker = ContactsDatabaseWorker.getInstance();
        switch (mLoader) {
            case CONTACT_LOADER_RINGER_TO_SILENT:
                return databaseWorker.getAllDataFromRIngToSilent(getContext());

            case CONTACT_LOADER_SILENT_TO_RING:
                return databaseWorker.getAllDataFromSilentToRing(getContext());

            default:
                return null;
        }
    }

}
