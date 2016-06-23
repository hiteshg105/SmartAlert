//package uma.hudss.Activity;
//
//import android.os.Bundle;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBar.Tab;
//import android.support.v7.app.ActionBarActivity;
//import android.util.Log;
//
//import uma.hudss.R;
//import uma.hudss.SmartAlert.CountryUtilities;
//import uma.hudss.SmartAlert.GlobalConstants;
//import uma.hudss.SmartAlert.SmartAlert;
//import uma.hudss.adapter.TabsPagerAdapter;
//
//public class SplashScreenActivity extends ActionBarActivity implements
//		ActionBar.TabListener {
//	private ViewPager viewPager;
//	private TabsPagerAdapter mAdapter;
//	private ActionBar actionBar;
//	private String[] tabs = { "General to silent", "Silent to genera;" };
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.splash);
//		initializeBackgroundData();
//		initializeFragments();
//
//	}
//
//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//		if(GlobalConstants.DEBUGGABLE)
//		Log.d("hudss", "ondestroy");
//	}
//
//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//		if(GlobalConstants.DEBUGGABLE)
//		Log.d("hudss", "ondestroy");
//	}
//
//	private void initializeFragments() {
//		// TODO Auto-generated method stub
//		viewPager = (ViewPager) findViewById(R.id.pager);
//		actionBar = getSupportActionBar();
//		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
//
//		viewPager.setAdapter(mAdapter);
//		actionBar.setHomeButtonEnabled(false);
//
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//		// Adding Tabs
//		for (String tab_name : tabs) {
//			actionBar.addTab(actionBar.newTab().setText(tab_name)
//					.setTabListener(this));
//
//		}
//
//		/**
//		 * on swiping the viewpager make respective tab selected
//		 * */
//		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//			@Override
//			public void onPageSelected(int position) {
//				// on changing the page
//				// make respected tab selected
//				actionBar.setSelectedNavigationItem(position);
//
//			}
//
//			@Override
//			public void onPageScrolled(int arg0, float arg1, int arg2) {
//			}
//
//			@Override
//			public void onPageScrollStateChanged(int arg0) {
//			}
//		});
//	}
//
//	private void initializeBackgroundData() {
//		// TODO Auto-generated method stub
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//				String[] location = CountryUtilities
//						.detectCC_Country(SplashScreenActivity.this);
//				if (location != null) {
//					Long cc = Long.parseLong(location[1]);
//					SmartAlert.getInstance().getPreferences()
//							.setCountry(location[0]);
//					SmartAlert.getInstance().getPreferences()
//							.setCountryCode(cc);
//				}
//			}
//		};
//		thread.start();
//
//	}
//
//	@Override
//	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
//		// TODO Auto-generated method stub
//		viewPager.setCurrentItem(tab.getPosition());
//	}
//
//	@Override
//	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
