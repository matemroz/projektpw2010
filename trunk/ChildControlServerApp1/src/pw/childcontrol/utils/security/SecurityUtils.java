package pw.childcontrol.utils.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class SecurityUtils {
	
	/**
	 * Static method responsible for generating md5 hash.
	 * 
	 * @param message text which will be hashed
	 * @return crypt method returns crypted text
	 */
	public static String generateMD5Hash(String message)
			throws NoSuchAlgorithmException {

		final String inputString = message;

		final MessageDigest messageDigest = MessageDigest.getInstance("MD5");

		final InputStream inputStream = new ByteArrayInputStream(
				inputString.getBytes());
		final byte[] buffer = new byte[4];

		int bufferLength;

		try {
			while ((bufferLength = inputStream.read(buffer)) != -1) {
				messageDigest.update(buffer, 0, bufferLength);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		final byte[] digest = messageDigest.digest();

		final Formatter formatter = new Formatter();
		for (final byte digestByte : digest) {
			formatter.format("%02x", digestByte);
		}
		String crypted = formatter.toString();

		return crypted;
	}
}
