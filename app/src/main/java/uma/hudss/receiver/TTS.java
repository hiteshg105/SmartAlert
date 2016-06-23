package uma.hudss.receiver;

import java.util.HashMap;
import java.util.Locale;

import uma.hudss.Activity.SpeakerFragment;
import uma.hudss.SmartAlert.SmartAlert;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;

@SuppressWarnings("deprecation")
public class TTS extends Service implements TextToSpeech.OnInitListener,
		OnUtteranceCompletedListener {
	private TextToSpeech mTts;
	private String spokenText;

	@Override
	public void onCreate() {
		mTts = new TextToSpeech(this, this);
		// This is a good place to set spokenText
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		spokenText = SmartAlert.getInstance().getPreferences()
				.getSpeakerTextBeforeSpeak()
				+ " "
				+ intent.getExtras().getString("texttospeak")
				+ " "
				+ SmartAlert.getInstance().getPreferences()
						.getSpeakerTextAfterSpeak();
		if (SmartAlert.getInstance().getPreferences().IsAllowedNameSpeaker()) {
			Speak();
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = mTts.setLanguage(Locale.getDefault());
			if (result != TextToSpeech.LANG_MISSING_DATA
					&& result != TextToSpeech.LANG_NOT_SUPPORTED) {
				if (SmartAlert.getInstance().getPreferences()
						.IsAllowedNameSpeaker()) {
					Speak();
				}
			}
		}
	}

	private void Speak() {
		// TODO Auto-generated method stub
		if (mTts != null) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<String, String> myHashAlarm = new HashMap<String, String>();
			myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
					String.valueOf(AudioManager.STREAM_RING));
			myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
					"SOME MESSAGE");
			mTts.setLanguage(Locale.getDefault());
			mTts.setPitch(SpeakerFragment.normalized.get(SmartAlert
					.getInstance().getPreferences().getSpeakerPitch()));
			mTts.setSpeechRate(SpeakerFragment.normalized.get(SmartAlert
					.getInstance().getPreferences().getSpeakerRate()));
			mTts.speak(spokenText, TextToSpeech.QUEUE_ADD, myHashAlarm);
			mTts.setOnUtteranceCompletedListener(this);

		}
	}

	@Override
	public void onUtteranceCompleted(String uttId) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Speak();
	}

	@Override
	public void onDestroy() {
		if (mTts != null) {
			mTts.stop();
			mTts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}