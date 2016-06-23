package uma.hudss.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import uma.hudss.R;
import uma.hudss.SmartAlert.GlobalConstants;
import uma.hudss.SmartAlert.SmartAlert;
import uma.hudss.SpeakerActivity.SpeakerPitchActivity;

public class SpeakerFragment extends android.support.v4.app.Fragment implements
		TextToSpeech.OnInitListener {
	private static final int MY_DATA_CHECK_CODE = 0, SET_PITCH = 1,
			SET_SPEED = 2;

	private Button bPitch, bSpeed, bVoiceTest, bDisable, bTextBeforeSpeak,
			bTextAfterSpeak;
	TextView tvTextBeforeSpeak, tvTextAfterSpeak;
	private EditText etTest;
	private TextToSpeech tts;
	String[] pitchValues = { "Very Low ", "Low", "Normal", "High", "Very High" };
	String[] speedValues = { "Very Slow ", "Slow", "Normal", "Fast",
			"Very Fast" };
	public static HashMap<Integer, Float> normalized = new HashMap<Integer,Float>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		normalized.put(0, (float) 0.3);
		normalized.put(1, (float) 0.6);
		normalized.put(2, (float) 1.0);
		normalized.put(3, (float) 1.8);
		normalized.put(4, (float) 2.6);
		View view = inflater.inflate(R.layout.speaker_fragment, container,
				false);

		bDisable = (Button) view.findViewById(R.id.bDisableSpeaker);
		bPitch = (Button) view.findViewById(R.id.bVoicePitch);
		bPitch.setText(pitchValues[SmartAlert.getInstance().getPreferences()
				.getSpeakerPitch()]);
		bSpeed = (Button) view.findViewById(R.id.bVoiceSpeed);
		bSpeed.setText(speedValues[SmartAlert.getInstance().getPreferences()
				.getSpeakerRate()]);
		bTextBeforeSpeak = (Button) view.findViewById(R.id.bTextBeforeSpeak);

		tvTextBeforeSpeak = (TextView) view
				.findViewById(R.id.tvTextBeforeSpeak);
		tvTextAfterSpeak = (TextView) view.findViewById(R.id.tvTextAfterSpeak);

		etTest = (EditText) view.findViewById(R.id.etVoiceTest);
		bVoiceTest = (Button) view.findViewById(R.id.bVoiceTest);
		tts = new TextToSpeech(getActivity(), this);
		bTextAfterSpeak = (Button) view.findViewById(R.id.bTextAfterspeak);
		tvTextBeforeSpeak.setText(SmartAlert.getInstance().getPreferences()
				.getSpeakerTextBeforeSpeak());
		tvTextAfterSpeak.setText(SmartAlert.getInstance().getPreferences()
				.getSpeakerTextAfterSpeak());
		bVoiceTest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SpeakOut(etTest.getText().toString());
			}

		});
		bPitch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						SpeakerPitchActivity.class);
				intent.putExtra("SpeakerChange", SET_PITCH);
				startActivityForResult(intent, SET_PITCH);
			}
		});
		bSpeed.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						SpeakerPitchActivity.class);
				intent.putExtra("SpeakerChange", SET_SPEED);
				startActivityForResult(intent, SET_SPEED);
			}
		});
		bTextBeforeSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				tvTextBeforeSpeak.setText(SmartAlertDialog
						.getDialogSpeakerAddTextBeforSpeak(getActivity(),
								tvTextBeforeSpeak));
			}
		});
		bTextAfterSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvTextAfterSpeak.setText(SmartAlertDialog
						.getDialogSpeakerAddTextAfterSpeak(getActivity(),
								tvTextAfterSpeak));
			}
		});

		if (!SmartAlert.getInstance().getPreferences().IsTTSEngineDataPassed()) {
			Intent checkTTSIntent = new Intent();
			checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);

			checkTTSIntent.putExtra(
					TextToSpeech.Engine.EXTRA_CHECK_VOICE_DATA_FOR,
					Locale.getDefault());
			startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

		}

		// 1.1
		bDisable.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (SmartAlert.getInstance().getPreferences()
						.IsAllowedNameSpeaker()) {
					SmartAlert.getInstance().getPreferences()
							.SetIsAllowedNameSpeaker(false);
					bDisable.setText("Click to enable Speaker");
					bDisable.setBackgroundColor(getResources().getColor(
							R.color.red));
				} else {
					SmartAlert.getInstance().getPreferences()
							.SetIsAllowedNameSpeaker(true);
					bDisable.setBackgroundColor(getResources().getColor(
							R.color.gray));
					bDisable.setText("Click to disable Speaker");
				}
			}
		});
		if (SmartAlert.getInstance().getPreferences().IsAllowedNameSpeaker()) {

			bDisable.setText("Click to disable Speaker");
			bDisable.setBackgroundColor(getResources().getColor(R.color.gray));

		} else {

			bDisable.setText("Click to enable Speaker");
			bDisable.setBackgroundColor(getResources().getColor(R.color.red));
		}

		return view;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL) {
				showDialog();
			}

			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				SmartAlert.getInstance().getPreferences()
						.setTTSEngineDataPassed(true);
				Toast toast = Toast
						.makeText(
								getActivity().getApplicationContext(),
								"Text To Speech is installed for your default language. Name Speaker will speak the caller's name in your default language ",
								Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			} else {
				if (data != null) {
					ArrayList<String> availableLanguages = data
							.getStringArrayListExtra(TextToSpeech.Engine.EXTRA_AVAILABLE_VOICES);

					if (availableLanguages.isEmpty()) {
						// no language data available, prompt for install
						showDialog();
					} else {
						// some language data is available, create TTS instance
						SmartAlert.getInstance().getPreferences()
								.setTTSEngineDataPassed(true);
						Toast.makeText(
								getActivity().getApplicationContext(),
								"Text To Speech is installed for your default language. Name Speaker will speak the caller's name in your default language ",
								Toast.LENGTH_LONG).show();

					}
				}

			}

		}
		if (requestCode == SET_PITCH) {
			if (resultCode == android.app.Activity.RESULT_OK) {
				int voicePitchId = data.getIntExtra("Pitch", 0);
				SmartAlert.getInstance().getPreferences()
						.setSpeakerPitch(voicePitchId);
				bPitch.setText(pitchValues[voicePitchId]);
			}
		}
		if (requestCode == SET_SPEED) {
			if (resultCode == android.app.Activity.RESULT_OK) {
				int voiceSpeeedId = data.getIntExtra("Speed", 0);
				SmartAlert.getInstance().getPreferences()
						.setSpeakerRate(voiceSpeeedId);
				bSpeed.setText(speedValues[voiceSpeeedId]);
			}
		}
	}

	private void showDialog() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(getActivity())
				.setTitle("Install Text To Speech")
				.setMessage(
						"Text to Speech Engine is not installed in your device .Do you want to install it?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent installTTSIntent = new Intent();
								installTTSIntent
										.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
								startActivity(installTTSIntent);
								dialog.cancel();
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

								dialog.cancel();
							}
						})

				.show();

	}

	private void SpeakOut(String wordToSpeak) {
		// TODO Auto-generated method stub

		if (tts != null) {
			/*
			 * Mapping - 0 -.25 1-.5 2-1 3-2 4-4
			 */
			tts.setLanguage(Locale.getDefault());
			tts.setPitch(normalized.get(SmartAlert.getInstance()
					.getPreferences().getSpeakerPitch()));
			tts.setSpeechRate(normalized.get(SmartAlert.getInstance()
					.getPreferences().getSpeakerRate()));
			tts.speak(wordToSpeak, TextToSpeech.QUEUE_FLUSH, null);
		} else {
			tts = new TextToSpeech(getActivity(), SpeakerFragment.this);
			tts.setPitch(normalized.get(SmartAlert.getInstance()
					.getPreferences().getSpeakerPitch()));
			tts.setSpeechRate(normalized.get(SmartAlert.getInstance()
					.getPreferences().getSpeakerRate()));
			tts.speak(wordToSpeak, TextToSpeech.QUEUE_FLUSH, null);
		}
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.getDefault());

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				if (GlobalConstants.DEBUGGABLE)
					Log.d("TTS", "This Language is not supported");
			} else {
				bVoiceTest.setEnabled(true);

			}

		} else {
			if (GlobalConstants.DEBUGGABLE)
				Log.e("TTS", "Initilization Failed!");
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_speaker, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.Settings:

			SmartAlertDialog.getDialogDisableSmartAlert(getActivity(),
					getFragmentManager());

			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}

	// int getNormalizedValueForPitchAndSpeed(int index){
	//
	// switch(index)
	// case:
	// }
}
