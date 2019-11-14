import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


/*
 * ref: http://zetcode.com/java/dom/
 * https://howtodoinjava.com/xml/read-xml-dom-parser-example/
 */
public class readfile {

	
	String read () throws IOException{

		 // How to read file into String before Java 7
        InputStream is = new FileInputStream("C:\\corpus\\corpus\\corpus\\FB396001\\FB396001");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append("<FILE>\n");
        
        while(line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        sb.append("</FILE>\n");
        
        String fileAsString = sb.toString();
        //System.out.println("Contents (before Java 7) : " + fileAsString);
        
        return fileAsString;
        // Reading file into Stirng in one line in JDK 7
        //String contents = new String(Files.readAllBytes(Paths.get("manifest.mf")));
        //System.out.println("Contents (Java 7) : " + contents);
        
        
        
        // Reading file into String using proper character encoding
        //String fileString = new String(Files.readAllBytes(Paths.get("manifest.mf")), StandardCharsets.UTF_8);
        //System.out.println("Contents (Java 7 with character encoding ) : " + fileString);
        

        // It's even easier in Java 8
        //Files.lines(Paths.get("manifest.mf"), StandardCharsets.UTF_8).forEach(System.out::println);
        
	
}
	
	
	
	void parseXML(String singleFileXML) throws Exception {
		
	      
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(new InputSource(new StringReader(singleFileXML)));
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		 
		//Get all employees
		NodeList nList = document.getElementsByTagName("DOC");
		System.out.println("============================");
		//System.out.println(((Document) nList).getDocumentElement().getNodeName());
		
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 System.out.println("");    //Just a separator
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    //Print each employee's detail
		    Element eElement = (Element) node;
		    System.out.println("Document id : "	+ eElement.getElementsByTagName("DOCNO").item(0).getTextContent());
		    System.out.println("HT : "			+ eElement.getElementsByTagName("HT").item(0).getTextContent());
			NodeList headerList = eElement.getElementsByTagName("HEADER");
			//System.out.println("header : "   + eElement.getElementsByTagName("HEADER").item(0).getTextContent());
			getHeaders(headerList);
		    //System.out.println("Last Name : "   + eElement.getElementsByTagName("lastName").item(0).getTextContent());
		    //System.out.println("Text : "    + eElement.getElementsByTagName("TEXT").item(0).getTextContent());
		 }
		}
	      
	      
	      
	}
	
	void getHeaders(NodeList headerList) {

		for (int temp = 0; temp < headerList.getLength(); temp++)
		{
		 Node node = headerList.item(temp);
//		 System.out.println("");    //Just a separator
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    //Print each employee's detail
		    Element eElement = (Element) node;
		    //System.out.println(eElement.getElementsByTagName("H2").item(0));
		    if (eElement.getElementsByTagName("H2").item(0) != null) {
		    	System.out.println("H2 : "	+ eElement.getElementsByTagName("H2").item(0).getTextContent());
		    }
		    else {
		    	System.out.println("H2 : "	+ null);
		    }
		    System.out.println("DATE : "			+ eElement.getElementsByTagName("DATE1").item(0).getTextContent());
		    System.out.println("Article Type : "   + eElement.getAttribute("Article Type"));
		    System.out.println("Document Type : "   + eElement.getAttribute("Document Type"));
		    //System.out.println("Text : "    + eElement.getElementsByTagName("TEXT").item(0).getTextContent());
		 }
		}
	}
	
}
