package anu.comp2100.teamgg.god_of_hand;
/**
 * @author COMP2100 TeamGG
 *  In LoadStoreFacade, we used facade design pattern to simplify the API and reduece dependency between classes.
 *  Meanwhile, factory design pattern is used as well.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class LoadStoreFacade {
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    OutputStream outputStream;
    Document doc;
    static LoadStoreFacade save;
    static LoadStoreFacade load;

    public LoadStoreFacade() {
        dbf = DocumentBuilderFactory.newInstance();
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    static LoadStoreFacade createSave(OutputStream outputStream) {
        if (save != null) {
            return save;
        } else {
            LoadStoreFacade save = new LoadStoreFacade();
            save.outputStream = outputStream;
            save.doc = save.db.newDocument();
            LoadStoreFacade.save = save;
            return LoadStoreFacade.save;
        }
    }

    static LoadStoreFacade createLoad(InputStream inputStream) {
        if (load != null) {
            return load;
        } else {
            LoadStoreFacade load = new LoadStoreFacade();
            try {
                load.doc = load.db.parse(inputStream);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LoadStoreFacade.load = load;
            return LoadStoreFacade.load;
        }
    }

    void addElement(String name, int value) {
        Element e = doc.createElement(name);
        e.setTextContent(String.valueOf(value));
        doc.appendChild(e);
    }

    void save(){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(outputStream);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Node load() {
        return doc.getFirstChild();
    }


}
