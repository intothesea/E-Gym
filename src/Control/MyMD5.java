package control;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Title: MyMD5
 * Description: Containing Encryption and Decryption methods with MD5.
 *
 * @author MingdaJia
 * @version 1.0.1
**/
public class MyMD5 {

	private static final String hexNumsStr = "0123456789ABCDEF";
	private static final Integer saltLength = 12;

	/**Title: hexStringToByte
	 * Description: convert string to a hex byte list.
	 *
	 * @param hex The String to be converted
	 * @return byte[] The converted hexadecimal byte list.
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] hexChars = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (hexNumsStr.indexOf(hexChars[pos]) << 4 | hexNumsStr.indexOf(hexChars[pos + 1]));
		}
		return result;
	}

	/**Title: byteToHexString
	 * Description: Convert byte list to a String
	 * 
	 * @param b The byte string to be converted
	 * @return String the value needed
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**Title: validPassword
	 * Description: Checking that if a password is correct or not
	 * 
	 * @param password The password user input
	 * @param passwordInDb The real encrypted password in database
	 * @return boolean If true, password correct, if not it's wrong.
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean validPassword(String password, String passwordInDb)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//
		byte[] pwdInDb = hexStringToByte(passwordInDb);
		//
		byte[] salt = new byte[saltLength];
		//
		System.arraycopy(pwdInDb, 0, salt, 0, saltLength);
		//
		MessageDigest md = MessageDigest.getInstance("MD5");
		//
		md.update(salt);
		//
		md.update(password.getBytes("UTF-8"));
		//
		byte[] digest = md.digest();
		//
		byte[] digestInDb = new byte[pwdInDb.length - saltLength];
		//
		System.arraycopy(pwdInDb, saltLength, digestInDb, 0, digestInDb.length);
		//
		if (Arrays.equals(digest, digestInDb)) {
			//
			return true;
		} else {

			return false;
		}
	}

	/**Title: getEncryptedPwd
	 *Description: Enrype a password by MD5
	 * 
	 * @param password The original password
	 * @return String The encrypted password
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncryptedPwd(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//
		byte[] pwd = null;
		//
		SecureRandom random = new SecureRandom();
		//
		byte[] salt = new byte[saltLength];
		//
		random.nextBytes(salt);

		//
		MessageDigest md = null;
		//
		md = MessageDigest.getInstance("MD5");
		//
		md.update(salt);
		//
		md.update(password.getBytes("UTF-8"));
		//
		byte[] digest = md.digest();

		//
		pwd = new byte[digest.length + saltLength];
		//
		System.arraycopy(salt, 0, pwd, 0, saltLength);
		//
		System.arraycopy(digest, 0, pwd, saltLength, digest.length);
		//
		return byteToHexString(pwd);
	}
}
