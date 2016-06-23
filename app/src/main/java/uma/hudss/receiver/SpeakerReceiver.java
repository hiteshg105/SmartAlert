package uma.hudss.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import uma.hudss.SmartAlert.SmartAlert;

public class SpeakerReceiver extends BroadcastReceiver {

	AudioManager audio;
	String incomingnumber;
	Context context;
	boolean isSpeakAllowed;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		this.context = context;
		PhoneStateChangeListener pscl = new PhoneStateChangeListener();
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// Case when call is made and no intent from tts is there, need to
		// initialize variable

		audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

		if (SmartAlert.getInstance().getPreferences().IsAllowedNameSpeaker()) {
			// Speak Allowed is false if user unchecked the preference
			isSpeakAllowed = true;
			tm.listen(pscl, PhoneStateListener.LISTEN_CALL_STATE);
		} else {
			isSpeakAllowed = false;
		}
	}

	class PhoneStateChangeListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub

			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				if (isSpeakAllowed) {
					Intent intent = new Intent(context, TTS.class);
					String name = SmartAlert.getInstance().getContactName(
							incomingNumber);
					if (name != null) {
						intent.putExtra("texttospeak", name);
					} else {
						intent.putExtra("texttospeak", incomingNumber);
					}
					context.startService(intent);

					incomingnumber = incomingNumber;
				}
				break;

			case TelephonyManager.CALL_STATE_OFFHOOK:
				if (isSpeakAllowed) {
					context.stopService(new Intent(context, TTS.class));

				}
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				if (isSpeakAllowed) {
					context.stopService(new Intent(context, TTS.class));
				}

				break;
			}

		}

	}
}
