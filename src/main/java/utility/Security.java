package utility;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Security {
	
	public static String encryptor(String encryptionPassword, String value) {
        System.out.println("Original Value : "+value);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptionPassword);
        return encryptor.encrypt(value);
	}
	
	public static String decryptor(String encryptionPassword, String value) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(encryptionPassword);
        return decryptor.decrypt(value);
	}
}
