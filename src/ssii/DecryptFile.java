package ssii;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class DecryptFile {

	private final static String alg = "AES";
    private final static String cI = "AES/CBC/PKCS5Padding";
    
    
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
        
        
        return "Decrypted!";
}
    
	 public static void main(String[] args) throws Exception {

		 String iv = "0123456789ABCDEF"; // vector de inicialización
		 
		 String rutaClaro = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta del fichero que quiere descifrar (incluyendo el nombre del mismo):");
		 String rutaCifrado = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta donde quiere guardar el nuevo fichero (incluyendo el nombre del mismo):");
		 String ksruta = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta donde se encuentra el almacén de claves (incluyendo el nombre del mismo):");
		 String kspass = JOptionPane.showInputDialog(null, "Introduzca la contraseña del almacén de claves:");
		 
		 String result = decrypt(iv, rutaClaro, rutaCifrado, ksruta, kspass);
		 
		 JOptionPane.showMessageDialog(null, result);
		 
}
}
