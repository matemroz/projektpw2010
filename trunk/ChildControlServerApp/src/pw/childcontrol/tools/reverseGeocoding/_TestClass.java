package pw.childcontrol.tools.reverseGeocoding;

import java.io.IOException;

public class _TestClass {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		
		DeGeocoder dekoder = new DeGeocoder();
		System.out.println("Adres: "+dekoder.work(52.227839,21.012769));
	      

	}

}
