package uma.hudss.Activity;

import uma.hudss.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.TextView;

public class AboutSmartAlert extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.about);
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int screenWidth = (int) (metrics.widthPixels * 0.80);
		int screenHeight = (int) (metrics.heightPixels * 0.60);

		getWindow().setLayout(screenWidth, LayoutParams.WRAP_CONTENT );

		TextView title = (TextView) findViewById(R.id.tvTitle);
		title.setText("SMART ALERT 1.0");
		TextView description = (TextView) findViewById(R.id.tvDescription);
		description
				.setText("Smart Alert never let you miss urgent calls ."
						+ "\n"
						+ "\n"

						+ "You can enter the contacts which are important to you and Smart Alert will ring for those contacts even when it is in Silent mode."

						+ "\n"
						+ "\n"
						+ "Smart Alert will never let you miss important calls even when you are busy and phone is silent and you don't want to receive all calls except few."
						+ "\n");
	}

}
