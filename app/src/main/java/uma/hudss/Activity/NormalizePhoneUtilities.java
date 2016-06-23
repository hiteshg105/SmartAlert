package uma.hudss.Activity;

import uma.hudss.SmartAlert.SmartAlert;

public class NormalizePhoneUtilities {

	public static String normalizePhoneNumber(String pNumber) {
		
		return normalizeNumberWithCode(pNumber,SmartAlert.getInstance().getPreferences().getCountryCode());


	}

	private static String normalizeNumberWithCode(String pNumber, Long countryCode) {
		// TODO Auto-generated method stub
		if (pNumber == null || pNumber.length() <= 0) {

		} else {
			pNumber = removeOccurrences(pNumber, ".");
			pNumber = removeOccurrences(pNumber, " ");
			pNumber = removeOccurrences(pNumber, "-");
			pNumber = retrieveAllDigitsAndplus(pNumber);
			pNumber = nomalizePrefix(pNumber, SmartAlert.getInstance()
					.getPreferences().getCountryCode());
		}
		return pNumber;
	}

	private static String nomalizePrefix(String pNumber, Long countryCode) {
		// TODO Auto-generated method stub
		if ((pNumber == null) || (pNumber.length() < 2)
				|| (pNumber.startsWith("00")))
			return pNumber;

		if ((pNumber.charAt(0) == '0') && (pNumber.charAt(1) != '0')) {
			return "00" + countryCode + pNumber.substring(1);
		}

		if (pNumber.startsWith("+")) {
			// Extract country code and replace with 00 + cc
			return "00" + pNumber.substring(1);

		}

		else {
			// Just prefix with 00 + cc and return
			return "00" + countryCode + pNumber;
		}
	}

	private static String retrieveAllDigitsAndplus(String pNumber) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		int i = 0;
		if (pNumber.charAt(i) == '+') {
			sb.append('+');
			i++;
		}
		for (; i < pNumber.length(); i++) {
			if (isNumeric(pNumber.charAt(i))) {
				sb.append(pNumber.charAt(i));
			} else if (isAlpha(pNumber.charAt(i)))
				break;
		}
		return sb.toString();

	}

	private static boolean isAlpha(char c) {
		// TODO Auto-generated method stub
		if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z'))
			return false;
		else
			return true;

	}

	private static boolean isNumeric(char c) {
		// TODO Auto-generated method stub
		if (c < '0' || c > '9')
			return false;
		else
			return true;

	}

	private static String removeOccurrences(String pNumber, String removeString) {
		// TODO Auto-generated method stub

		int index;
		while ((index = pNumber.indexOf(removeString, 0)) != -1) {
			pNumber = pNumber.substring(0, index)
					+ pNumber.substring(index + removeString.length(),
							pNumber.length());
		}
		return pNumber;
	}

}
