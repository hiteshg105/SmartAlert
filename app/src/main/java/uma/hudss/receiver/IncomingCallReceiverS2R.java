package uma.hudss.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import uma.hudss.Activity.NormalizePhoneUtilities;
import uma.hudss.Model.ContactModel;
import uma.hudss.Activity.HomeActivity;
import uma.hudss.R;
import uma.hudss.SmartAlert.GlobalConstants;
import uma.hudss.SmartAlert.SmartAlert;
import uma.hudss.database.AppPreferences;
import uma.hudss.database.ContactsDatabaseWorker;

public class IncomingCallReceiverS2R extends BroadcastReceiver {

	AudioManager audio;
	static int streamVolume, previousVolume;
	public static boolean contactExists;
	static CustomPhoneStateListener phoneStateListener;
	boolean isLastCallIncoming = false;
	ContactsDatabaseWorker worker;
	Context context;
	MediaPlayer mMediaPlayer;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		this.context = context;

		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(context.TELEPHONY_SERVICE);
		audio = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);

		if (audio.getStreamVolume(AudioManager.STREAM_RING) != 0) {
			return;
		}
		if (phoneStateListener == null) {
			phoneStateListener = new CustomPhoneStateListener();
			tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		}
		// Change the Profile if Contact Exists in Application Database


		// Store Previous Volume when Called
		
	}

	class CustomPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			boolean contactExists = false;
			String phonenumber = NormalizePhoneUtilities
					.normalizePhoneNumber(incomingNumber);

			ContactModel contact =ContactsDatabaseWorker.getInstance()
					.getContact(context,NormalizePhoneUtilities
							.normalizePhoneNumber(incomingNumber), AppPreferences.SILENT_TO_RING);
			// For Accepting the Call Issue //Phone Number is null in that case
			//call off hook -reason -issue when call picked at end it resets the volume to 7 again
			if (SmartAlert.getInstance().getPreferences()
					.getIsLastCallIncomingS2R()||SmartAlert.getInstance().getPreferences().isLastCallOffHook())
				contactExists = true;
			if (contact != null
					&& SmartAlert.getInstance().getPreferences()
							.IsSmartAlertOn())
				contactExists = true;

			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:

				if (contactExists) {
					if (audio.getStreamVolume(AudioManager.STREAM_RING) == 0) {

						int maxVolume = audio
								.getStreamMaxVolume(AudioManager.STREAM_RING);

						audio.setStreamVolume(AudioManager.STREAM_RING, 7, 0);
						int a =audio.getStreamVolume(AudioManager.STREAM_RING);
						if(GlobalConstants.DEBUGGABLE)
						Log.d("TAG",
								"CALL_STATE_RINGING  Stream ring volume "
										+ audio.getStreamVolume(AudioManager.STREAM_RING));
						SmartAlert.getInstance().getPreferences()
								.setIsLastCallIncomingS2R(true);
						sendNotification(incomingNumber);
					}

				}
				break;

			case TelephonyManager.CALL_STATE_OFFHOOK:
				if (contactExists) {
					if (SmartAlert.getInstance().getPreferences()
							.getIsLastCallIncomingS2R()) {

						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						audio.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
						SmartAlert.getInstance().getPreferences()
								.setIsLastCallIncomingS2R(false);
						SmartAlert.getInstance().getPreferences().setisLastCallOffHook(true);
						if(GlobalConstants.DEBUGGABLE)
						Log.d("TAG", "CALL_STATE_OFFHOOK  previous Volume "
								+ SmartAlert.getInstance().getPreferences()
										.getPreviousVolume());
						if(GlobalConstants.DEBUGGABLE)
						Log.d("TAG",
								"CALL_STATE_OFFHOOK  Stream ring volume "
										+ audio.getStreamVolume(AudioManager.STREAM_RING));
						
					}
				}
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				if (contactExists) {
					boolean text = SmartAlert.getInstance().getPreferences()
							.getIsLastCallIncomingS2R();
					if (SmartAlert.getInstance().getPreferences()
							.getIsLastCallIncomingS2R()||SmartAlert.getInstance().getPreferences().isLastCallOffHook()) {

						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						audio.setStreamVolume(AudioManager.STREAM_RING, 0, 0);

						if(GlobalConstants.DEBUGGABLE)
						Log.d("TAG", "CALL_STATE_IDLE  previous Volume "
								+ SmartAlert.getInstance().getPreferences()
										.getPreviousVolume());
						SmartAlert.getInstance().getPreferences()
								.setIsLastCallIncomingS2R(false);
						SmartAlert.getInstance().getPreferences().setisLastCallOffHook(false);
						if(GlobalConstants.DEBUGGABLE)
						Log.d("TAG",
								"CALL_STATE_IDLE  Stream ring volume "
										+ audio.getStreamVolume(AudioManager.STREAM_RING));
						
					}
				}
				break;
			}

		}

	}

	private void sendNotification(String incomingNumber) {
		// TODO Auto-generated method stub
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);
		int mNotificationId = 001;
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Wake Call")
				.setContentText("Wakes you up for " + incomingNumber);
		mBuilder.setAutoCancel(true);
		Intent resultIntent = new Intent(context, HomeActivity.class);
		PendingIntent resultPendingIntent = PendingIntent.getActivity(context,
				0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		notificationManager.notify(mNotificationId, mBuilder.build());

	}
}
