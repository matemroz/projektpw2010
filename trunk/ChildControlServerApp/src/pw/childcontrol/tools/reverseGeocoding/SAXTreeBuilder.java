package pw.childcontrol.tools.reverseGeocoding;

import javax.swing.tree.DefaultMutableTreeNode;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

class SAXTreeBuilder extends DefaultHandler{

    private DefaultMutableTreeNode currentNode = null;
    private DefaultMutableTreeNode previousNode = null;
    private DefaultMutableTreeNode rootNode = null;

    public SAXTreeBuilder(DefaultMutableTreeNode root){
           rootNode = root;
    }
    public void startDocument(){
           currentNode = rootNode;
    }
    public void endDocument(){
    }
    public void characters(char[] data,int start,int end){
    	
           String str = new String(data,start,end);     
           //System.out.println(str);
           if (!str.equals("") && (Character.isLetter(str.charAt(0)) || Character.isDigit(str.charAt(0)) || str.charAt(0)=='-' || Character.isJavaIdentifierPart(str.charAt(0))))
               currentNode.add(new DefaultMutableTreeNode(str));           
    }
    public void startElement(String uri,String qName,String lName,Attributes atts){
           previousNode = currentNode;
           
           currentNode = new DefaultMutableTreeNode(lName);
           // Add attributes as child nodes //
           attachAttributeList(currentNode,atts);
           previousNode.add(currentNode);              
    }
    public void endElement(String uri,String qName,String lName){
           if (currentNode.getUserObject().equals(lName))
               currentNode = (DefaultMutableTreeNode)currentNode.getParent();              
    }
    public DefaultMutableTreeNode getTree(){
           return rootNode;
    }

    private void attachAttributeList(DefaultMutableTreeNode node,Attributes atts){
            for (int i=0;i<atts.getLength();i++){
                 String name = atts.getLocalName(i);
                 String value = atts.getValue(name);
                 node.add(new DefaultMutableTreeNode(name + " = " + value));
            }
    }
}
