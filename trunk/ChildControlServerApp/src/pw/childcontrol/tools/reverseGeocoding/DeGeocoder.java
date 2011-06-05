/*
 * http://maps.googleapis.com/maps/api/geocode/xml?latlng=40.714224,-73.961452&sensor=false
 * http://code.google.com/intl/pl-PL/apis/maps/documentation/geocoding/#ReverseGeocoding
 * 
 */

package pw.childcontrol.tools.reverseGeocoding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DeGeocoder {
	SAXTreeBuilder saxTree;
	boolean work;
	String status; //OK, ZERO_RESULTS, OVER_QUERY_LIMIT, REQUEST_DENIED, INVALID_REQUEST
	Map<String, Result> wynik = new HashMap<String, Result>(); // Resulte

	double latitude; // zdekodowane
	double longitude; // po³o¿enie


	public String work(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
		String wynik="";

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultMutableTreeNode top = new DefaultMutableTreeNode("Google");
			SAXTreeBuilder handler= new SAXTreeBuilder(top);

			saxParser.parse("http://maps.googleapis.com/maps/api/geocode/xml?latlng="+this.latitude+","+this.longitude+"&sensor=false", handler);
			//System.out.println("G³êbokoœæ drzewa: "+handler.getTree().getDepth());

			TreeTool tool = new TreeTool(handler.getTree());
			//tool.printTree();
			tool.getAdress();
			HashMap<String,Result> map = tool.getAdress();

			for(Iterator i=map.keySet().iterator();i.hasNext();){
				String klucz = (String) i.next();
				wynik=map.get(klucz).formatedAdress.length()>wynik.length()?map.get(klucz).formatedAdress:wynik;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return wynik;

	}

}
