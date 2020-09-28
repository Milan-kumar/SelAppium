package utils;

import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class JavaUtil {

	public static String encryptString(String sourceString) {
		byte[] encodedString = Base64.encodeBase64(sourceString.getBytes());
//		System.out.println(new String(encodedPass));
		return new String(encodedString);	
	}
	
	public static String decryptString(String sourceString) {
		byte[] decodedString = Base64.decodeBase64(sourceString.getBytes());
//		System.out.println(new String(decodedString));
		return new String(decodedString);	
	}
	
	public static void main(String[] args) {
//		System.out.println(encryptString("Password"));
//		System.out.println(decryptString(encryptString("Password")));
//		Scanner reader = new Scanner(System.in);
//		System.out.println("Enter the number: ");
//		int i = reader.nextInt();
//		System.out.println("Entered int number: "+i);
		char c = 'a';
		int ascii = c;
		System.out.println("Ascii value is "+ ascii);
		System.out.println("Ascii value of a is "+ (int)'a');
		Xls_Reader xls = new Xls_Reader("C:\\MILAN\\Ved\\barheta input 2020 (1).xlsx");
		System.out.print("*******regNum*****"+xls.getCellData("Table 1",1,3).replace(".", "").substring(0,13));
	}

}
