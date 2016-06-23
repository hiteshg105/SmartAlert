package uma.hudss.NewActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import uma.hudss.Activity.AboutSmartAlert;
import uma.hudss.Activity.SmartAlertDialog;
import uma.hudss.R;
import uma.hudss.SmartAlert.DatabaseListener;
import uma.hudss.SmartAlert.SmartAlert;
import uma.hudss.SmartAlert.SmartAlertUtil;
import uma.hudss.adapter.CursorLoaders;
import uma.hudss.adapter.CustomCursorAdapter;
import uma.hudss.adapter.TabsPagerAdapter;
import uma.hudss.database.AppPreferences;
import uma.hudss.database.ContactsContract;
import uma.hudss.database.ContactsDatabaseWorker;

/**
 * Created by hiteshgupta on 15/06/16.
 */
public class SilentToRingerFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    public SilentToRingerFragment() {
        // Required empty public constructor
    }


    LoaderManager loadermanager;
    private static final String DIALOG_ALERT = null;
    CustomCursorAdapter customAdapter;
    ListView listview;
    ContactsDatabaseWorker databaseWorker;
    private boolean isModal;
    Runnable run;
    DatabaseListener dbListener;
    FragmentTransaction fragTransaction;
    Fragment currentFragment;
    String string;
    private ListView.OnItemClickListener ListClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub
            Cursor c = (Cursor) parent.getAdapter().getItem(position);
            c.moveToPosition(position);
            String contactName = "", contactNumber = "";

            contactName = c
                    .getString(c
                            .getColumnIndex(ContactsContract.SmartContacts.SMART_CONTACTS_COLUMN_CONTACT_NAME));
            contactNumber = c
                    .getString(c
                            .getColumnIndex(ContactsContract.SmartContacts.SMART_CONTACTS_COLUMN_PHONE_NUMBER));

            Dialog dialog = SmartAlertDialog.getDialogAddFromEditContact(
                    getActivity(), contactName, contactNumber, AppPreferences.SILENT_TO_RING);
            dialog.show();
        }
    };

//    public static RingerToSilentFragment newInstance(int title) {
//        RingerToSilentFragment frag = new RingerToSilentFragment();
//        Bundle args = new Bundle();
//        frag.isModal = true;
//        args.putInt("title", title);
//        frag.setArguments(args);
//        return frag;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        databaseWorker = ContactsDatabaseWorker.getInstance();
        customAdapter = new CustomCursorAdapter(getActivity(),
                databaseWorker.getAllDataFromSilentToRing(getActivity()));
        dbListener = new DatabaseListener() {

            @Override
            public void onChange() {
                // TODO Auto-generated method stub
                refresh();
            }
        };
        databaseWorker.registerListener(dbListener, AppPreferences.SILENT_TO_RING);
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        // listview.setOnItemLongClickListener(new OnItemLongClickListener() {
        //
        // @Override
        // public boolean onItemLongClick(AdapterView<?> parent, View view,
        // int position, long id) {
        // // TODO Auto-generated method stub
        //
        // return true;
        // }
        // });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        loadermanager = getLoaderManager();
        listview.setAdapter(customAdapter);

        loadermanager.initLoader(CursorLoaders.CONTACT_LOADER_SILENT_TO_RING, null, this);

        registerForContextMenu(listview);
        listview.setOnItemClickListener(ListClick);

        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // List<ContactModel> ab = new LinkedList<ContactModel>();
        // ab.add(new ContactModel("Hitesh", "9958918811","2"));
        // ab.add(new ContactModel("Hitesh", "9958918811","2"));
        // View view =inflater.inflate(R.layout.smartalert_fragment,
        // container,false);
        // ArrayAdapter<ContactModel> array = new
        // ArrayAdapter<ContactModel>(inflater.getContext(),R.id.listView1,ab);
        // setListAdapter(array);
        if (isModal) // AVOID REQUEST FEATURE CRASH
        {
            View view = inflater.inflate(R.layout.smartalert_fragment,
                    container, false);
            listview = (ListView) view.findViewById(R.id.lvSmartAlert);
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        string = TabsPagerAdapter.makeFragmentName(R.id.pager, 0);
        currentFragment = getFragmentManager().findFragmentByTag(string);
        fragTransaction = getFragmentManager().beginTransaction();
        View view = inflater.inflate(R.layout.smartalert_fragment, container,
                false);
//        Button bAdd = (Button) view.findViewById(R.id.bAddContact);
//        bAdd.setOnClickListener(this);
        listview = (ListView) view.findViewById(R.id.lvSmartAlert);
//        if (!SmartAlert.getInstance().getPreferences().IsSmartAlertOn()) {
//            bAdd.setText("Click To Enable Smart Alert");
//            bAdd.setBackgroundColor(getResources().getColor(R.color.red));
//        }

        return view;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.bAddContact:

                if (SmartAlert.getInstance().getPreferences().IsSmartAlertOn()) {

                    ShowDialog(AppPreferences.SILENT_TO_RING);
                } else {
                    SmartAlert.getInstance().getPreferences().SetSmartAlertOn(true);
                    reloadSmartAlertFragmentView();
                    SmartAlertUtil
                            .showToast(
                                    getActivity(),
                                    "Smart Alert has been enabled on your Phone. Now You will never miss Urgent Call",
                                    Toast.LENGTH_LONG);
                }

        }
    }

    private void refresh() {
        // TODO Auto-generated method stub
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // load();
                loadermanager.restartLoader(CursorLoaders.CONTACT_LOADER_SILENT_TO_RING, null,
                        SilentToRingerFragment.this);
            }

        });

    }

    private void ShowDialog(int mSilentToRing) {
        // TODO Auto-generated method stub

        // TEST
        Dialog dialog = new SmartAlertDialog().getDialogAddFrom(getActivity(), mSilentToRing);

        dialog.show();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        // TODO Auto-generated method stub

        return new CursorLoaders(arg0, getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        // TODO Auto-generated method stub
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
            customAdapter.swapCursor(cursor);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub
        if (customAdapter != null)
            customAdapter.swapCursor(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_smart_alert, menu);

    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (SmartAlert.getInstance().getPreferences().IsSmartAlertOn()) {
            menu.findItem(R.id.enableSmartAlert).setVisible(false);
            menu.findItem(R.id.disableSmartAlert).setVisible(true);
        } else {
            menu.findItem(R.id.enableSmartAlert).setVisible(true);
            menu.findItem(R.id.disableSmartAlert).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.disableSmartAlert:

                SmartAlertDialog.getDialogDisableSmartAlert(getActivity(),
                        getFragmentManager());

                return true;
            case R.id.enableSmartAlert:
                SmartAlert.getInstance().getPreferences().SetSmartAlertOn(true);

                reloadSmartAlertFragmentView();

                return true;
            case R.id.about:
                Intent intent = new Intent(getActivity(), AboutSmartAlert.class);
                startActivity(intent);

                return true;

            case R.id.add:
                if (SmartAlert.getInstance().getPreferences().IsSmartAlertOn()) {

                    ShowDialog(AppPreferences.SILENT_TO_RING);
                } else {
                    SmartAlert.getInstance().getPreferences().SetSmartAlertOn(true);
                    reloadSmartAlertFragmentView();
                    SmartAlertUtil
                            .showToast(
                                    getActivity(),
                                    "Smart Alert has been enabled on your Phone. Now You will never miss Urgent Call",
                                    Toast.LENGTH_LONG);
                }


            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(
                R.array.array_contact);
        String menuItemName = menuItems[menuItemIndex];
        TextView tvphoneNumber = (TextView) info.targetView
                .findViewById(R.id.tvNumber);
        String phoneNumber = tvphoneNumber.getText().toString();
//		String[] arrayphoneNumber = { phoneNumber };
        customAdapter.getItem(info.position);
        switch (menuItemIndex) {
            case 0:
                ContactsDatabaseWorker.deleteContact(getContext(), phoneNumber, AppPreferences.SILENT_TO_RING);
                customAdapter.notifyDataSetChanged();
                refresh();

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.lvSmartAlert) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("Delete Contact");
            String[] menuItems = getResources().getStringArray(
                    R.array.array_contact);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void reloadSmartAlertFragmentView() {
        fragTransaction.detach(currentFragment);
        fragTransaction.attach(currentFragment);
        fragTransaction.commit();
    }


}
