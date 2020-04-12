package ssii;

import java.security.SecureRandom;
import java.util.Random;

public class Main {
	 
	 public static void main(String[] args) throws Exception {
	 String key = "92AE31A79FEEB2A3"; //llave
	 String iv = "0123456789ABCDEF"; // vector de inicialización

	 
	 //ENCRIPTAR ARCHIVO
	 //System.out.println("Texto encriptado: "+ EncryptUtils.encrypt(key, iv, "C:\\Users\\juan1\\Desktop\\UMLNuevo.pdf", "C:\\Users\\juan1\\Desktop\\uml.txt"));
	 
	 
	 
	 //DESENCRIPTAR ARCHIVO
	 //System.out.println("Texto desencriptado: "+EncryptUtils.decrypt(key, iv, "C:\\Users\\juan1\\Desktop\\uml.txt", "C:\\Users\\juan1\\Desktop\\UMLNuevo.pdf", "C:\\Users\\juan1\\Desktop\\almacen.txt"));
	 
	 
	 
	 //CREAR UN NUEVO ALMACEN DE CLAVES
	 //EncryptUtils.generateKeystoreFile("C:\\Users\\juan1\\Desktop\\newkeystore.ks", "1234");
	 
	 
	 
	 //RandomString r = new RandomString (16);
	 System.out.println(generateRandomString(16));
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

