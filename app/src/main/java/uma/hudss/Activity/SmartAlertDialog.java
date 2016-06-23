package uma.hudss.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import uma.hudss.Model.ContactModel;
import uma.hudss.R;
import uma.hudss.SmartAlert.SmartAlert;
import uma.hudss.SmartAlert.SmartAlertUtil;
import uma.hudss.database.ContactsDatabaseWorker;

public class SmartAlertDialog {

	public Dialog getDialogAddFrom(final Activity context,final int silentToRing) {
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_add);

		ArrayAdapter<String> addAdapter = new ArrayAdapter<String>(context,
				R.layout.dialog_add_from, context.getResources()
						.getStringArray(R.array.array_add_from)) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					LayoutInflater inflater = (LayoutInflater) context
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					convertView = inflater.inflate(R.layout.dialog_add_from,
							parent, false);
				}

				TextView heading = (TextView) convertView
						.findViewById(R.id.content);
				heading.setText(context.getResources().getStringArray(
						R.array.array_add_from)[position]);
				return convertView;
			}
		};
		ListView addList = (ListView) dialog.findViewById(R.id.lvAddDialog);
		addList.setAdapter(addAdapter);

		addList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					// Contacts
					Intent intent = new Intent(context,
							ContactsChooserActivity.class);
					intent.putExtra(SmartAlertUtil.SILENT_TO_RING,silentToRing);
					context.startActivity(intent);
					try {
						dialog.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 1:
					// Manually
					Dialog dialogmanual = new SmartAlertDialog()
							.getDialogAddManually(context, silentToRing);
					dialogmanual.show();
					try {
						dialog.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				}

			}
		});

		return dialog;

	}

	public static void getDialogDisableSmartAlert(
			final FragmentActivity context, final FragmentManager fm) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle("Disable Smart Alert");
		alertDialogBuilder
				.setMessage(
						"Click Yes to disable Automatic Ringing Volume On for selected Contact ")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
//								SmartAlert.getInstance().getPreferences()
//										.SetSmartAlertOn(false);
//								String string = TabsPagerAdapter
//										.makeFragmentName(R.id.pager, 0);
//								Fragment currentFragment = fm
//										.findFragmentByTag(string);
//								FragmentTransaction fragTransaction = fm
//										.beginTransaction();
//								fragTransaction.detach(currentFragment);
//								fragTransaction.attach(currentFragment);
//								fragTransaction.commit();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setCancelable(true);
		// show it
		alertDialog.show();

	}

	public static Dialog getDialogAddManually(final Activity context,final int silentToRing) {
		// TODO Auto-generated method stub

		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.diaolg_add_contact);

		final EditText etName = (EditText) dialog.findViewById(R.id.etName);
		etName.requestFocus();
		final EditText etNumber = (EditText) dialog.findViewById(R.id.etNumber);
		Button bSave = (Button) dialog.findViewById(R.id.bSave);
		Button bCancel = (Button) dialog.findViewById(R.id.bCancel);

		bSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = etName.getText().toString();
				String number = etNumber.getText().toString();
				String normalizednumber = NormalizePhoneUtilities
						.normalizePhoneNumber(number);
				ContactModel contactModel = new ContactModel(name,
						normalizednumber, String.valueOf(3));
				ContactsDatabaseWorker sm =  ContactsDatabaseWorker.getInstance();

				sm.AddSmartContact(context,contactModel,silentToRing);
				try {
					dialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		bCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					dialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		return dialog;
	}

	public static Dialog getDialogAddFromContact(final Activity context,
			String contactName, String contactId,final int silentToRing) {
		// TODO Auto-generated method stub

		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.diaolg_add_contact);

		final EditText etName = (EditText) dialog.findViewById(R.id.etName);
		etName.setText(contactName);
		etName.requestFocus();

		final EditText etNumber = (EditText) dialog.findViewById(R.id.etNumber);
		etNumber.setText(getPhoneNumberFromContactId(context, contactId));
		Button bSave = (Button) dialog.findViewById(R.id.bSave);
		Button bCancel = (Button) dialog.findViewById(R.id.bCancel);

		bSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = etName.getText().toString();
				String number = etNumber.getText().toString();
				String normalizednumber = NormalizePhoneUtilities
						.normalizePhoneNumber(number);
				ContactModel contactModel = new ContactModel(name,
						normalizednumber, String.valueOf(3));
				ContactsDatabaseWorker sm = ContactsDatabaseWorker.getInstance();

				sm.AddSmartContact(context,contactModel,silentToRing);

				try {
					dialog.dismiss();
					context.finish();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		bCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					dialog.dismiss();
					context.finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		return dialog;
	}

	/*
	 * Maps the ContactsContract.CommonDataKinds.Phone.CONTACT_ID to
	 * ContactsContract.Contacts._IDto get the phone number stored in
	 */
	private static String getPhoneNumberFromContactId(Activity context,
			String contactId) {
		// TODO Auto-generated method stub
		String phoneNumber = null;
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER };
		String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
				+ " =?";

		String[] selectionArgs = new String[] { contactId };
		try {
			Cursor cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor.moveToFirst()) {

				phoneNumber = cursor.getString(0);
				cursor.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phoneNumber;

	}

	public static Dialog getDialogAddFromEditContact(
			final FragmentActivity activity, String contactName,
			String contactNumber,final int silentToRing) {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(activity);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.diaolg_add_contact);
		TextView title = (TextView) dialog.findViewById((R.id.title));
		title.setText("Edit Contact");
		final EditText etName = (EditText) dialog.findViewById(R.id.etName);
		etName.setText(contactName);
		etName.requestFocus();

		final EditText etNumber = (EditText) dialog.findViewById(R.id.etNumber);
		etNumber.setText(contactNumber);
		Button bSave = (Button) dialog.findViewById(R.id.bSave);
		Button bCancel = (Button) dialog.findViewById(R.id.bCancel);

		final String[] phoneNumber = new String[] { contactNumber };
		bSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String name = etName.getText().toString();
				String number = etNumber.getText().toString();
				String normalizednumber = NormalizePhoneUtilities
						.normalizePhoneNumber(number);
				ContactModel contactModel = new ContactModel(name,
						normalizednumber, String.valueOf(3));
				ContactsDatabaseWorker sm = ContactsDatabaseWorker.getInstance();
				sm.deleteContact(activity,phoneNumber[0], silentToRing);
				sm.AddSmartContact(activity,contactModel,silentToRing);
				try {
					dialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		bCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					dialog.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		return dialog;
	}

	public static void getDialogSpeakerSettings() {

	}

	public static String getDialogSpeakerAddTextBeforSpeak(final Context context, final TextView tvTextBeforeSpeak) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle("Text Before Speak");
		final EditText input = new EditText(context);
		input.setHint("Enter text before speak");

		alertDialogBuilder.setView(input);
		alertDialogBuilder

				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
                             SmartAlert.getInstance().getPreferences().setSpeakerTextBeforeSpeak(input.getText().toString());
                             tvTextBeforeSpeak.setText(input.getText().toString());
                             dialog.dismiss();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setCancelable(true);
		// show it
		alertDialog.show();
		return SmartAlert.getInstance().getPreferences().getSpeakerTextBeforeSpeak();
	}
	public static String getDialogSpeakerAddTextAfterSpeak(final Context context, final TextView tvTextAfterSpeak) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle("Text After Speak");
		final EditText input = new EditText(context);
		input.setHint("Enter text after speak");

		alertDialogBuilder.setView(input);
		alertDialogBuilder

				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
                             SmartAlert.getInstance().getPreferences().setSpeakerTextAfterSpeak(input.getText().toString());
                             tvTextAfterSpeak.setText(input.getText().toString());
                             dialog.dismiss();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setCancelable(true);
		// show it
		alertDialog.show();
		return SmartAlert.getInstance().getPreferences().getSpeakerTextAfterSpeak();
	}
}
