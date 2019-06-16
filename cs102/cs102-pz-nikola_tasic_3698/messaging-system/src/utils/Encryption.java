package utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	private final static byte[] HASH_SALT = "1106e5f6ead07f89f65a4060724ed88d124f17b8".getBytes();
	private static String key = "z%C*F-JaNdRgUkXp";
	private static Key aesKey = null;

	public static String encrypt(String text) {
		if (Encryption.aesKey == null) {
			Encryption.aesKey = new SecretKeySpec(key.getBytes(), "AES");
		}
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			return new String(Base64.getEncoder().encode(encrypted));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(String encrypted) {
		if (Encryption.aesKey == null) {
			Encryption.aesKey = new SecretKeySpec(key.getBytes(), "AES");
		}
		try {
			Cipher cipher;
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
			return new String(decrypted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getSHA1Hash(String passwordToHash) {
		return Encryption.getSHA1Hash(passwordToHash, Encryption.HASH_SALT);
	}

	public static String getSHA1Hash(String passwordToHash, byte[] salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}