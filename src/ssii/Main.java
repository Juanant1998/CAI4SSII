package ssii;

public class Main {
	 
	 public static void main(String[] args) throws Exception {
	 String key = "92AE31A79FEEB2A3"; //llave
	 String iv = "0123456789ABCDEF"; // vector de inicialización
	 //String cleartext = "hola";
	 //System.out.println("Texto encriptado: "+ EncryptUtils.encrypt(key, iv, "C:\\Users\\juan1\\Desktop\\stuff revisar\\UML.pdf", "C:\\Users\\juan1\\Desktop\\stuff revisar\\UML2.txt"));
	 System.out.println("Texto desencriptado: "+EncryptUtils.decrypt(key, iv, "C:\\Users\\juan1\\Desktop\\stuff revisar\\UML2.txt", "C:\\Users\\juan1\\Desktop\\stuff revisar\\UMLnuevo.pdf"));
	 }

}
