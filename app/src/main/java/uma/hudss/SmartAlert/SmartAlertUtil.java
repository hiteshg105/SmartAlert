package uma.hudss.SmartAlert;

import android.app.Activity;
import android.widget.Toast;

public class SmartAlertUtil {
	public static String SILENT_TO_RING ="silentToRing";
	
	public static void showToast( Activity context,String message,int timePeriod){
		
		Toast.makeText(context, message, timePeriod).show();
		
	}

}
