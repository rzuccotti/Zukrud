package com.interlem.rzuccotti.zukrud.utility;

import com.interlem.rzuccotti.zukrud.database.model.ObjectStudent;
import com.interlem.rzuccotti.zukrud.enumeration.ServerOperation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class BuildXMLFile {

    public static String buildStudentXML(ObjectStudent student, ServerOperation operation) {

        String xml = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element cereal = doc.createElement("cereal");
            doc.appendChild(cereal);

            Element message = doc.createElement("message");
            cereal.appendChild(message);

            Element response = doc.createElement("response");
            response.appendChild(doc.createTextNode(""));
            message.appendChild(response);

            Element op = doc.createElement("op");
            op.appendChild(doc.createTextNode(operation.name()));
            message.appendChild(op);

            Element stud = doc.createElement("student");
            message.appendChild(stud);

            Element idElem = doc.createElement("id");
            idElem.appendChild(doc.createTextNode(String.valueOf(student.getId())));
            stud.appendChild(idElem);

            Element nomeElem = doc.createElement("nome");
            nomeElem.appendChild(doc.createTextNode(student.getFirstName()));
            stud.appendChild(nomeElem);

            Element emailElem = doc.createElement("email");
            emailElem.appendChild(doc.createTextNode(String.valueOf(student.getEmail())));
            stud.appendChild(emailElem);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            transformer.transform(source, result);
            StringBuffer sb = sw.getBuffer();
            xml = sb.toString();


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return xml;
    }
}