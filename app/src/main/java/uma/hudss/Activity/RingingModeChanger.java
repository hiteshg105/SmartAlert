package uma.hudss.Activity;

import android.content.Context;
import android.media.AudioManager;

public class RingingModeChanger  {

	static Context context;
	static String phNo ;

	public RingingModeChanger() {
	}
	public static int lastRingerMode=0 ;
//	public static int changeMode(Context context,String phNo){
//
//		int hasModeChanged =-1;
//		if(ContactsDatabaseWorker.getContact(phNo)!=null){
//
//
//		TelephonyManager tm =(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//		AudioManager am =(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
//		lastRingerMode=am.getRingerMode();
//		am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//		hasModeChanged =0;
//		}
//		return hasModeChanged;
//
//
//
//	}
	public static void restoreLastRingerMode(){
		if(lastRingerMode != 0 ){
			AudioManager am =(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
			am.setRingerMode(lastRingerMode);	
		}
	}
	
	
	
}
