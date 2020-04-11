package ssii;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class DecryptFile {

	private final static String alg = "AES";
    private final static String cI = "AES/CBC/PKCS5Padding";
    
    
    public static String decrypt(String key, String iv, String rutaEncriptado, String nuevaRuta) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);

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
        
        
        return "decrypted";
}
    
	 public static void main(String[] args) throws Exception {

		 String key = "92AE31A79FEEB2A3"; //llave
		 String iv = "0123456789ABCDEF"; // vector de inicialización
		 
		 String rutaClaro = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta del fichero que quiere descifrar (incluyendo el nombre del mismo):");
		 String rutaCifrado = JOptionPane.showInputDialog(null, "Introduzca la ruta absoluta donde quiere guardar el nuevo fichero (incluyendo el nombre del mismo):");

		 String result = decrypt(key, iv, rutaClaro, rutaCifrado);
		 
		 JOptionPane.showMessageDialog(null, result);
		 
}
}
