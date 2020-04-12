package ssii;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.swing.JOptionPane;

public class GenerateKeystore {

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
	
	public static void main (String[] args) {
		String ksruta = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta donde quiere crear el almac�n de claves (incluyendo el nombre del mismo):");
		 String kspass = JOptionPane.showInputDialog(null, "Introduzca la contrase�a del almac�n de claves:");
		 
		 generateKeystoreFile(ksruta, kspass);
		 
		 JOptionPane.showMessageDialog(null, "Almac�n de claves creado, puede comprobarlo desde el gestor de archivos!");
	}
}
