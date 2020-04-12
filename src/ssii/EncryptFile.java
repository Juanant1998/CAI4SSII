package ssii;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class EncryptFile {

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
        return "Cifrado, por favor, guarde el vector de inicialización generado. Puede verlo en la consola.";
}

	
	 public static void main(String[] args) throws Exception {

		 String key = generateRandomString(16); //llave
		 String iv = generateRandomString(16); // vector de inicialización
		 
		 String rutaClaro = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta del fichero que quiere cifrar (incluyendo el nombre del mismo):");
		 String rutaCifrado = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta donde quiere guardar el nuevo fichero (incluyendo el nombre del mismo):");
		 String ksruta = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta donde se encuentra el almacén de claves (incluyendo el nombre del mismo):");
		 String kspass = JOptionPane.showInputDialog(null, "Introduzca la contraseña del almacén de claves:");

		 String result = encrypt(key, iv, rutaClaro, rutaCifrado, ksruta, kspass);
		 
		 JOptionPane.showMessageDialog(null, result);
		 
		 System.out.println("IV: " + iv);
		 
}

	 
	 public static String generateRandomString(int length) {
		 String symbols = "ABCDEFGJKLMNPRSTUVWXYZ0123456789"; 

		 Random random = new SecureRandom();

		 char[] buf;

		  
		    if (length < 1)
		      throw new IllegalArgumentException("length < 1: " + length);
		    buf = new char[length];

		    for (int idx = 0; idx < buf.length; ++idx) 
		      buf[idx] = symbols.charAt(random.nextInt(symbols.length()));
		    return new String(buf);
		  }
}


