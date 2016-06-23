package uma.hudss.adapter;

import uma.hudss.R;
import android.content.Context;
import android.database.Cursor;
import uma.hudss.database.*;

import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {

	public CustomCursorAdapter(Context context, Cursor c) {
	
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView Name = (TextView) view.findViewById(R.id.tvName);
		Name.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.SmartContacts.SMART_CONTACTS_COLUMN_CONTACT_NAME)));
		
		TextView Number =(TextView) view.findViewById(R.id.tvNumber);
		Number.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.SmartContacts.SMART_CONTACTS_COLUMN_PHONE_NUMBER)));
		int position = cursor.getPosition();
		if (position % 2 == 0) {
			view.setBackgroundColor(context.getResources().getColor(
					R.color.list_item_even_position));
		} else {
			view.setBackgroundColor(context.getResources().getColor(
					R.color.list_item_odd_position));
		}
		
		
		notifyDataSetChanged();
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = LayoutInflater
				.from(parent.getContext());
		View retView = layoutInflater.inflate(R.layout.custom_list_item,
				parent, false);
		return retView;
	}

}
