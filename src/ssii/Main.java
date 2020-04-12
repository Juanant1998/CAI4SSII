package ssii;

import java.security.SecureRandom;
import java.util.Random;

public class Main {
	 
	 public static void main(String[] args) throws Exception {
		 String key = generateRandomString(16); //llave
		 //String iv = generateRandomString(16); // vector de inicialización
		 String iv = "E7939V9L8MK1PA8P"; //Vector de inicialización (descifrar)
	 
	 //ENCRIPTAR ARCHIVO
	 //System.out.println(EncryptUtils.encrypt(key, iv, "C:\\Users\\juan1\\Desktop\\aaaa.txt", "C:\\Users\\juan1\\Desktop\\1.txt", "C:\\Users\\juan1\\Desktop\\newkeystore.ks", "1234"));
	 
	 
	 
	 //DESENCRIPTAR ARCHIVO
	//System.out.println(EncryptUtils.decrypt(iv, "C:\\Users\\juan1\\Desktop\\1.txt", "C:\\Users\\juan1\\Desktop\\u.txt", "C:\\Users\\juan1\\Desktop\\newkeystore.ks", "1234"));
	 
	 
	 
	 //CREAR UN NUEVO ALMACEN DE CLAVES
	 //EncryptUtils.generateKeystoreFile("C:\\Users\\juan1\\Desktop\\newkeystore.ks", "1234");
	 
	 
	 
	 //RandomString r = new RandomString (16);
	 //System.out.println(generateRandomString(16));
	 }

	 
	 
	 //Clase auxiliar para generar números aleatorios
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

