package ssii;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Scanner;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {

	private final static String alg = "AES";
    private final static String cI = "AES/CBC/PKCS5Padding";

	public static String encrypt(String key, String iv, String rutaClaro, String nuevaRuta, String ksruta, String kspass) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");

				
				char[] keyStorePassword = kspass.toCharArray();

				keyStore.load(null, keyStorePassword);
				
				KeyStore.ProtectionParameter entryPassword =
				        new KeyStore.PasswordProtection(keyStorePassword);
				
				
				SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);

				
				KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(skeySpec);

				
				keyStore.setEntry(nuevaRuta, secretKeyEntry, entryPassword);
				
				try (FileOutputStream keyStoreOutputStream = new FileOutputStream(ksruta)) {

				    keyStore.store(keyStoreOutputStream, keyStorePassword);
				}
				
				
				
				
				
				
				Cipher cipher = Cipher.getInstance(cI);
		        //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
				IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

		        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParameterSpec);
		        FileInputStream fis = new FileInputStream(rutaClaro);
		        FileOutputStream fos = new FileOutputStream(nuevaRuta);
		        CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		        byte[] block = new byte[1024];
		        int i;
		        try {
		            while ((i = fis.read(block)) != -1) {
		                cos.write(block, 0, i);
		            }
		            fis.close();

		        } catch (Exception e) {
		            throw new IOException(e);
		        } finally {
		            cos.close();
		        }
		        
		        File file = new File(rutaClaro);
		        file.delete();
		        
		        System.out.println(iv);
		        return "Cifrado, por favor, guarde el vector de inicialización generado. Puede verlo en la consola.";
		}
	
	public static String decrypt(String iv, String rutaEncriptado, String nuevaRuta, String ksruta, String kspass) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");

				
				char[] keyStorePassword = kspass.toCharArray();

				try(InputStream keyStoreData = new FileInputStream(ksruta)){
				    keyStore.load(keyStoreData, keyStorePassword);
				}		
				KeyStore.ProtectionParameter entryPassword =
		        new KeyStore.PasswordProtection(keyStorePassword);
				
				KeyStore.SecretKeyEntry privateKeyEntry = (KeyStore.SecretKeyEntry)
				        keyStore.getEntry(rutaEncriptado, entryPassword);
				
				Thread.sleep(200);
				
				
				Cipher cipher = Cipher.getInstance(cI);

				IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		        cipher.init(Cipher.DECRYPT_MODE, privateKeyEntry.getSecretKey(), ivParameterSpec);

		        FileInputStream fis = new FileInputStream(rutaEncriptado);
		        FileOutputStream fos = new FileOutputStream(nuevaRuta);
		        CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		        byte[] block = new byte[1024];
		        int i;
		        try {
		            while ((i = fis.read(block)) != -1) {
		                cos.write(block, 0, i);
		            }
		            fis.close();

		        } catch (Exception e) {
		            throw new IOException(e);
		        } finally {
		            cos.close();
		        }
		        
		        
		        return "Descifrado";
		}
	
	public static void generateKeystoreFile(String ruta, String pass) {
		KeyStore keyStore;
		try {
			keyStore = KeyStore.getInstance("PKCS12");
		
		char[] keyStorePassword = pass.toCharArray();

		keyStore.load(null, keyStorePassword);
		
		FileOutputStream keyStoreOutputStream = new FileOutputStream(ruta);
		    keyStore.store(keyStoreOutputStream, keyStorePassword);
		}
		 catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
