package uma.hudss.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int item) {
		// TODO Auto-generated method stub
//		switch (item) {
//		case 0:
//			// SmartAlert
//
//			return new SmartAlertFragment();
//		case 1:
//			// Games fragment activity
//			return new RingerToSilentFragment();
//
//		}
//		return null;
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	// To reference the fragment
	public static String makeFragmentName(int viewId, int index) {
		return "android:switcher:" + viewId + ":" + index;
	}
}
