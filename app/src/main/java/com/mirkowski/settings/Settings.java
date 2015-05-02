package com.mirkowski.settings;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


/**
 * Created by Kamil on 2015-04-30.
 */
public class Settings {

    private String userName = null;
    private String serverAdress = null;

    private final File file ;//= new File("res/values/settings.xml");


    public Settings(){
//        try {
//            readXMLFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
        file = null;
    }
    private void readXMLFile() throws IOException, ParserConfigurationException, SAXException {


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc =  dBuilder.parse(file);
        NodeList nList = doc.getElementsByTagName("settings");
        for (int i = 0; i < nList.getLength(); i++) {
            Node n = nList.item(i);
            NodeList childNodes = n.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeName().equals("username")) {
                    userName = childNodes.item(j).getTextContent();
                } else if (childNodes.item(j).getNodeName().equals("serveradress")) {
                    serverAdress = childNodes.item(j).getTextContent();
                }
            }
        }
    }

    private void updateXMLFile() throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc =  dBuilder.parse(file);
        NodeList nList = doc.getElementsByTagName("settings");
        for (int i = 0; i < nList.getLength(); i++) {
            Node n = nList.item(i);
            NodeList childNodes = n.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeName().equals("username")) {
                    childNodes.item(j).setTextContent(userName);
                } else if (childNodes.item(j).getNodeName().equals("serveradress")) {
                    childNodes.item(j).setTextContent(serverAdress);
                }
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
        try {
            updateXMLFile();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public String getServerAdress() {

        return serverAdress+":8080/WebSocketGlassfish/chat";
    }

    public void setServerAdress(String serverAdress) {

        this.serverAdress = serverAdress;
        try {
            updateXMLFile();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void incrementWinCount(){

    }
    public void incrementDefeatCount(){

    }
}
