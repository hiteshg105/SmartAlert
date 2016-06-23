package uma.hudss.SpeakerActivity;

import java.util.HashMap;

import uma.hudss.R;
import uma.hudss.SmartAlert.SmartAlert;
import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SpeakerPitchActivity extends Activity {

	RadioGroup rgVoicePitch, rgVoiceSpeed;
	int voicePitch, voiceSpeed;

	// HashMap<Integer, String> pitch = new HashMap<>();
	// HashMap<Integer, String> speed = new HashMap<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speaker_pitch_activity);
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int screenWidth = (int) (metrics.widthPixels * 0.80);
		int screenHeight = (int) (metrics.heightPixels * 0.60);
		getWindow().setLayout(screenWidth, LayoutParams.WRAP_CONTENT );
		rgVoicePitch = (RadioGroup) findViewById(R.id.rgPitch);
		rgVoiceSpeed = (RadioGroup) findViewById(R.id.rgSpeed);
       
		Intent intent = getIntent();
		if (intent.hasExtra("SpeakerChange")) {
			int changePitchOrSpeed = intent.getIntExtra("SpeakerChange", 0);
			if (changePitchOrSpeed == 1) {
				// pitch Change
				rgVoicePitch.setVisibility(View.VISIBLE);

				rgVoicePitch.check(getResourceId(SmartAlert.getInstance()
						.getPreferences().getSpeakerPitch(), true));
				rgVoicePitch.check(rgVoicePitch.getCheckedRadioButtonId());
				rgVoiceSpeed.setVisibility(View.GONE);
			} else if (changePitchOrSpeed == 2) {
				// Speed Change

				rgVoicePitch.setVisibility(View.GONE);
				rgVoiceSpeed.setVisibility(View.VISIBLE);
				rgVoiceSpeed.check(getResourceId(SmartAlert.getInstance()
						.getPreferences().getSpeakerRate(), false));
				rgVoiceSpeed.check(rgVoiceSpeed.getCheckedRadioButtonId());
			}

		}
		rgVoicePitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				voicePitch = rgVoicePitch
						.indexOfChild(findViewById(rgVoicePitch
								.getCheckedRadioButtonId()));
				Intent intent = new Intent();
				intent.putExtra("Pitch", voicePitch);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		rgVoiceSpeed.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				voiceSpeed = rgVoiceSpeed
						.indexOfChild(findViewById(rgVoiceSpeed
								.getCheckedRadioButtonId()));
				Intent intent = new Intent();
				intent.putExtra("Speed", voiceSpeed);
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}

	int getResourceId(int checkedState, boolean pitch) {
		switch (checkedState) {
		case 0:
			if (pitch)
				return R.id.rVeryLow;
			else
				return R.id.rVerySlow;

		case 1:
			if (pitch)
				return R.id.rLow;
			else
				return R.id.rSlow;

		case 2:
			if (pitch)
				return R.id.rNormal;
			else
				return R.id.rSpeedNormal;
		case 3:
			if (pitch)
				return R.id.rHigh;
			else
				return R.id.rFast;
		case 4:
			if (pitch)
				return R.id.rVeryHigh;
			else
				return R.id.rVeryFast;
		default:
			return 0;
		}
	}

}
