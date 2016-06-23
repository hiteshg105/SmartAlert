package uma.hudss.SmartAlert;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
//import caller.id.ind.util.GlobalConstants;
//import caller.id.ind.util.Logger;

public class CountryUtilities {

	static	HashMap<String, String[]> mccmncMap = new HashMap<String, String[]>();

	 
	public static String[][] country_mcc_cc_list = new String[][]{
		{"Afghanistan","93","412"},
		{"Albania","355","276"},
		{"Algeria","213","603"},
		{"American Samoa","1684","544"},
		{"Andorra","376","213"},
		{"Angola","244","631"},
		{"Anguilla","1264","365"},
		{"Antarctica","672","90113"},
		{"Antigua and Barbuda","1268","344"},
		{"Argentina","54","722"},
		{"Armenia","374","283"},
		{"Aruba","297","363"},
		{"Australia","61","505"},
		{"Austria","43","232"},
		{"Azerbaijan","994","400"},
		{"Bahamas","1242","364"},
		{"Bahrain","973","426"},
		{"Bangladesh","880","470"},
		{"Barbados","1246","342"},
		{"Belarus","375","257"},
		{"Belgium","32","206"},
		{"Belize","501","702"},
		{"Benin","229","616"},
		{"Bermuda","1441","31059"},
		{"Bermuda","1441","35001"},
		{"Bermuda","1441","35002"},
		{"Bermuda","1441","338050"},
		{"Bhutan","975","402"},
		{"Bolivia","591","736"},
		{"Bosnia & Herzegovina","387","218"},
		{"Botswana","267","652"},
		{"Brazil","55","724"},
		{"British Virgin Islands","1284","348"},
		{"Brunei Darussalam","673","528"},
		{"Bulgaria","359","284"},
		{"Burkina Faso","226","613"},
		{"Burundi","257","642"},
		{"Cambodia","855","456"},
		{"Cameroon","237","624"},
		{"Canada","1","302"},
		{"Cape Verde Islands","238","625"},
		{"Cayman Islands","1345","346"},
		{"Central African Republic","236","623"},
		{"Chad","235","622"},
		{"Chile","56","730"},
		{"China","86","460"},
		{"Colombia","57","732"},
		{"Comoros","269","654"},
		{"Congo","242","629"},
		{"Cook Islands","682","548"},
		{"Costa Rica","506","712"},
		{"Croatia","385","219"},
		{"Cuba","53","368"},
		{"Curacao","599","36269"},
		{"Cyprus","357","280"},
		{"Czech Republic","420","230"},
		{"Democratic Rep. of Congo","243","630"},
		{"Denmark","45","238"},
		{"Djibouti","253","638"},
		{"Dominica","1767","366"},
		{"Dominican Republic","1809","370"},
		{"East Timor","670","514"},
		{"Ecuador","593","740"},
		{"Egypt","20","602"},
		{"El Salvador","503","706"},
		{"Equatorial Guinea","240","627"},
		{"Eritrea","291","657"},
		{"Estonia","372","248"},
		{"Ethiopia","251","636"},
		{"Faroe Islands","298","288"},
		{"Fiji","679","542"},
		{"Finland","358","244"},
		{"France","33","208"},
		{"French Polynesia","689","547"},
		{"Gabonese Republic","241","628"},
		{"Gambia","220","607"},
		{"Georgia","995","282"},
		{"Germany","49","262"},
		{"Ghana","233","620"},
		{"Gibraltar","350","266"},
		{"Greece","30","202"},
		{"Greenland","299","290"},
		{"Grenada","1473","352"},
		{"Guadeloupe","590","34003"},
		{"Guadeloupe","590","34008"},
		{"Guam","1671","310032"},
		{"Guam","1671","310033"},
		{"Guam","1671","310140"},
		{"Guam","1671","310370"},
		{"Guam","1671","311250"},
		{"Guatemala","502","704"},
		{"Guinea","224","611"},
		{"Guinea (Bissau)","245","632"},
		{"Guyana","592","738"},
		{"Haiti","509","372"},
		{"Honduras","504","708"},
		{"Hong Kong","852","454"},
		{"Hungary","36","216"},
		{"Iceland","354","274"},
		{"India","91","404"},
		{"India","91","405"},
		{"Indonesia","62","510"},
		{"Iran","98","432"},
		{"Iraq","964","418"},
		{"Ireland","353","272"},
		{"Israel","972","425"},
		{"Italy","39","222"},
		{"Ivory Coast","225","612"},
		{"Jamaica","1876","338020"},
		{"Jamaica","1876","338050"},
		{"Jamaica","1876","338180"},
		{"Japan","81","440"},
		{"Jordan","962","416"},
		{"Kazakhstan","7","401"},
		{"Kenya","254","639"},
		{"Kiribati","686","545"},
		{"Korea (North)","Korea (North)","850","467"},
		{"Korea (South)","Korea (South)","82","450"},
		{"Kuwait","965","419"},
		{"Kyrgyz Republic","996","437"},
		{"Laos","856","457"},
		{"Latvia","371","247"},
		{"Lebanon","961","415"},
		{"Lesotho","266","651"},
		{"Liberia","231","618"},
		{"Libya","218","606"},
		{"Liechtenstein","423","295"},
		{"Lithuania","370","246"},
		{"Luxembourg","352","270"},
		{"Macao","853","455"},
		{"Macedonia","389","294"},
		{"Madagascar","261","646"},
		{"Malawi","265","650"},
		{"Malaysia","60","502"},
		{"Maldives","960","472"},
		{"Mali Republic","223","610"},
		{"Malta","356","278"},
		{"Martinique","596","34001"},
		{"Martinique","596","34002"},
		{"Martinique","596","340020"},
		{"Mauritania","222","609"},
		{"Mauritius","230","617"},
		{"Mexico","52","334"},//Fixed county code issue
		{"Micronesia","691","550"},
		{"Moldova","373","259"},
		{"Monaco","377","212"},
		{"Mongolia","976","428"},
		{"Montenegro","382","297"},
		{"Montserrat","1664","354"},
		{"Morocco","212","604"},
		{"Mozambique","258","643"},
		{"Myanmar","95","414"},
		{"Namibia","264","649"},
		{"Nauru","674","536"},
		{"Nepal","977","429"},
		{"Netherlands","31","204"},
		{"Netherlands Antilles","599","362"},
		{"New Caledonia","687","546"},
		{"New Zealand","64","530"},
		{"Nicaragua","505","710"},
		{"Niger","227","614"},
		{"Nigeria","234","621"},
		{"Niue","683","555"},
		{"Norfolk Island","6723","50510"},
		{"Norway","47","242"},
		{"Oman","968","422"},
		{"Pakistan","92","410"},
		{"Palau","680","552"},
		{"Palestinian Settlements","970","42506"},
		{"Panama","507","714"},
		{"Papua New Guinea","675","537"},
		{"Paraguay","595","744"},
		{"Peru","51","716"},
		{"Philippines","63","515"},
		{"Poland","48","260"},
		{"Portugal","351","268"},
		{"Puerto Rico (US)","1787","330"},
		{"Qatar","974","427"},
		{"REunion Island","262","647"},
		{"Romania","40","226"},
		{"Russia","7","250"},
		{"Rwanda","250","635"},
		{"St. Kitts/Nevis","1869","356"},
		{"St. Lucia","1758","358"},
		{"St. Pierre & Miquelon","508","308"},
		{"St. Vincent & Grenadines","1784","360"},
		{"Samoa","685","549"},
		{"San Marino","378","292"},
		{"Sao Tome And Principe","239","626"},
		{"Saudi Arabia","966","420"},
		{"Senegal","221","608"},
		{"Serbia","381","220"},
		{"Seychelles Republic","248","633"},
		{"Sierra Leone","232","619"},
		{"Singapore","65","525"},
		{"Slovak Republic","421","231"},
		{"Slovenia","386","293"},
		{"Solomon Islands","677","540"},
		{"Somali Democratic Republic","252","637"},
		{"South Africa","27","655"},
		{"South Sudan","211","659"},
		{"Spain","34","214"},
		{"Sri Lanka","94","413"},
		{"Sudan","249","634"},
		{"Suriname","597","746"},
		{"Swaziland","268","653"},
		{"Sweden","46","240"},
		{"Switzerland","41","228"},
		{"Syria","963","417"},
		{"Taiwan","886","466"},
		{"Tajikistan","992","436"},
		{"Tanzania","255","640"},
		{"Thailand","66","520"},
		{"Togolese Republic","228","615"},
		{"Tonga Islands","676","539"},
		{"Trinidad & Tobago","1868","374"},
		{"Tunisia","216","605"},
		{"Turkey","90","286"},
		{"Turkmenistan","993","438"},
		{"Turks and Caicos Islands","1649","338"},
		{"Turks and Caicos Islands","1649","376"},
		{"Uganda","256","641"},
		{"United Arab Emirates","971","424"},
		{"United Kingdom","44","234"},
		{"Ukraine","380","255"},
		{"United States of America","1","310"},
		{"United States of America","1","311"},
		{"United States of America","1","313"},
		{"United States of America","1","316"},
		{"Uruguay","598","748"},
		{"Uzbekistan","998","434"},
		{"Vanuatu","678","541"},
		{"Vatican City","39","225"},
		{"Vatican City State","379","225"},
		{"Venezuela","58","734"},
		{"Vietnam","84","452"},
		{"Yemen","967","421"},
		{"Zambia","260","645"},
		{"Zanzibar","255","64003"},
		{"Zimbabwe","263","648"}};

	static {
		for(int i=0;i< country_mcc_cc_list.length; i++)
		{
			String[] cc_country_arr= new String[2];
			cc_country_arr[0]= country_mcc_cc_list[i][0];
			cc_country_arr[1]= country_mcc_cc_list[i][1];
			mccmncMap.put(country_mcc_cc_list[i][2], cc_country_arr);
		}
	}
	
	public static String[] getCC_CountryByMCCMNC(String mccmnc)
	{
		if  ( mccmncMap.get(mccmnc)!=null)
		{
			return (String[])mccmncMap.get(mccmnc);
		}
		else if (mccmnc.length() >= 3)
		{
			mccmnc = mccmnc.substring(0,3);
			if  ( mccmncMap.get(mccmnc)!=null)
			{
				return mccmncMap.get(mccmnc);
			}
			
		}
		return null;
	}
	
	public static String[] detectCC_Country(Activity context)
	{
		try{
			TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			String mcc_mnc = tm.getSimOperator();
			String[] location1 =  getCC_CountryByMCCMNC(mcc_mnc);
			if (location1!=null){
				return location1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		
		return null;
	}
	
}