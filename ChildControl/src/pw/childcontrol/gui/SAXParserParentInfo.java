package pw.childcontrol.gui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Pawel
 */
public class SAXParserParentInfo {

    String nodeName = "";
    HashMap<String, String> map;
    int childNumber = 1;

    public static String CONST_IS_PARENT = "isParent";
    public static String CONST_PARENT_EMAIL = "parentEmail";
    public static String CONST_PARENT_NAME = "parentName";
    public static String CONST_PARENT_PASSWORD = "parentPassword";
    public static String CONST_NO_CHILD = "nochild";
    public static String CONST_CHILD = "child";
    public static String CONST_NUMBER = "number";

    SAXParserParentInfo(){

    }

    public HashMap<String, String> parseXML(String xml) throws SAXException, ParserConfigurationException, IOException{

         map = new HashMap<String, String>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        //InputStream is = new InputStreamReader( xml.getBytes( "UTF-8" ) );
        saxParser.parse(is, handler);

        System.out.println(map.toString());
        
        return map;
    }

    DefaultHandler handler = new DefaultHandler(){

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            String s = new String(ch, start, length);
            //if(s.equals(""))
            //System.out.println(s);
            if(nodeName.equals(CONST_IS_PARENT)){
                System.out.println(s);
                map.put(CONST_IS_PARENT, s);
                nodeName = "";
            } else if(nodeName.equals(CONST_PARENT_EMAIL)){
                map.put(CONST_PARENT_EMAIL, s);
                nodeName = "";
            } else if(nodeName.equals(CONST_PARENT_NAME)){
                map.put(CONST_PARENT_NAME, s);
                nodeName = "";
            } else if(nodeName.equals(CONST_PARENT_PASSWORD)){
                map.put(CONST_PARENT_PASSWORD, s);
                nodeName = "";
            } else if(nodeName.equals(CONST_NO_CHILD)){
                map.put(CONST_NO_CHILD, s);
                nodeName = "";
            } else if(nodeName.equals(CONST_CHILD)){
                map.put(CONST_CHILD + String.valueOf(childNumber), s);
                childNumber++;
                nodeName = "";
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            //System.out.println("localName: " + qName);
            if(qName.equals(CONST_IS_PARENT)){
                System.out.println(qName + ": ");
                nodeName = CONST_IS_PARENT;
            } else if(qName.equals(CONST_PARENT_EMAIL)){
                nodeName = CONST_PARENT_EMAIL;
            } else if(qName.equals(CONST_PARENT_NAME)){
                nodeName = CONST_PARENT_NAME;
            } else if(qName.equals(CONST_PARENT_PASSWORD)){
                nodeName = CONST_PARENT_PASSWORD;
            } else if(qName.equals(CONST_NO_CHILD)){
                nodeName = CONST_NO_CHILD;
            } else if(qName.equals(CONST_CHILD)){
                nodeName = CONST_CHILD;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }
    };


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            String xml = "<parentInfo><isParent>true</isParent><parentEmail>asd@qw.pl</parentEmail><parentName>Zdzis</parentName><parentPassword>wsx</parentPassword><nochild>false</nochild><child number=\"1\">45</child><child number=\"2\">46</child></parentInfo>";
            SAXParserParentInfo pi = new SAXParserParentInfo();
            pi.parseXML(xml);
        } catch (SAXException ex) {
            Logger.getLogger(SAXParserParentInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SAXParserParentInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SAXParserParentInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
