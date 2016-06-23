package uma.hudss.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferences {
    public static String PrefsFile = "SmartAlertPreferences";

    public static int RING_TO_SILENT = 2;
    public static int SILENT_TO_RING = 1;

    SharedPreferences settings;
    Editor editor;

    public AppPreferences(Context app) {
        settings = app.getSharedPreferences(PrefsFile, 0);
        editor = settings.edit();
    }

    public void setCountry(String country) {
        // TODO Auto-generated method stub
        editor.putString("country", country);
        editor.commit();

    }

    public void setCountryCode(Long cc) {
        // TODO Auto-generated method stub
        editor.putLong("countrycode ", cc);
        editor.commit();

    }

    public Long getCountryCode() {
        return settings.getLong("countrycode", Long.parseLong("91"));
    }

    public void setIsLastCallIncoming(boolean islastCallIncoming) {
        editor.putBoolean("islastcallincoming", islastCallIncoming);
        editor.commit();

    }

    public boolean getIsLastCallIncoming() {
        return settings.getBoolean("islastcallincoming", false);
    }

    public void setPreviousVolume(int streamVolume) {
        // TODO Auto-generated method stub
        editor.putInt("previousvolume", streamVolume);
        editor.commit();

    }

    public int getPreviousVolume() {
        return settings.getInt("previousvolume", 0);
    }

    public void SetIsAllowedNameSpeaker(boolean isChecked) {
        // TODO Auto-generated method stub
        editor.putBoolean("allownamespeaker", isChecked);
        editor.commit();

    }

    public boolean IsAllowedNameSpeaker() {
        // TODO Auto-generated method stub
        return settings.getBoolean("allownamespeaker", false);

    }

    public void setTTSEngineDataPassed(boolean dataPassed) {
        // TODO Auto-generated method stub
        editor.putBoolean("isttsenginedatapassed", dataPassed);
        editor.commit();
    }

    public boolean IsTTSEngineDataPassed() {
        // TODO Auto-generated method stub
        return settings.getBoolean("isttsenginedatapassed", false);

    }

    public boolean IsSmartAlertOn() {
        // TODO Auto-generated method stub
        return settings.getBoolean("issmartalerton", true);

    }

    public void SetSmartAlertOn(boolean value) {
        // TODO Auto-generated method stub
        editor.putBoolean("issmartalerton", value);
        editor.commit();

    }

    public boolean isLastCallOffHook() {
        // TODO Auto-generated method stub
        return settings.getBoolean("islastcalloffhook", false);
    }

    public void setisLastCallOffHook(boolean b) {
        // TODO Auto-generated method stub
        editor.putBoolean("islastcalloffhook", b);
        editor.commit();
    }

    public void setSpeakerPitch(int voicePitchId) {
        // TODO Auto-generated method stub
        editor.putInt("speakerpitch", voicePitchId);
        editor.commit();
    }

    public int getSpeakerPitch() {
        // TODO Auto-generated method stub
        return settings.getInt("speakerpitch", 2);
    }

    public void setSpeakerRate(int voiceRateId) {
        // TODO Auto-generated method stub
        editor.putInt("speakerrate", voiceRateId);
        editor.commit();
    }

    public int getSpeakerRate() {
        // TODO Auto-generated method stub
        return settings.getInt("speakerrate", 2);
    }

    public void setSpeakerTextBeforeSpeak(String text) {
        // TODO Auto-generated method stub
        editor.putString("speakertextbeforespeak", text);
        editor.commit();
    }

    public String getSpeakerTextBeforeSpeak() {
        // TODO Auto-generated method stub
        return settings.getString("speakertextbeforespeak", "hello");
    }

    public void setSpeakerTextAfterSpeak(String text) {
        // TODO Auto-generated method stub
        editor.putString("speakertextafterspeak", text);
        editor.commit();
    }

    public String getSpeakerTextAfterSpeak() {
        // TODO Auto-generated method stub
        return settings.getString("speakertextafterspeak", "calling");
    }
}
