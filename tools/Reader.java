package tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Stack;

public class Reader {

    public Reader() {
    }

    /**
     * Загружает в коллекцию элементы из файла формата Xml
     * @param studyGroups
     * @param path
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void xmlRead (Stack<StudyGroup> studyGroups, String path) throws IOException, ParserConfigurationException, SAXException {

        FileInputStream fis = new FileInputStream("ScriptFile/"+path);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(bis);
        NodeList list  = document.getElementsByTagName("StudyGroup");
            for(int i = 0 ; i<list.getLength(); i++){
                Node node = list.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                        Color eye = null;
                        Color hair = null;
                        Country nationality = null;
                        LocalDate birthday = null;
                        FormOfEducation formOfEducation = null;
                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        Long count = Long.parseLong(element.getElementsByTagName("studentCount").item(0).getTextContent());
                        Semester semester =  Semester.valueOf(element.getElementsByTagName("semester").item(0).getTextContent());
                        Coordinates coordinates = new Coordinates(Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent()),Integer.valueOf(element.getElementsByTagName("y").item(0).getTextContent()));
                        String nameP = element.getElementsByTagName("nameP").item(0).getTextContent();

                        if(!(element.getElementsByTagName("birthday").item(0).getTextContent().isEmpty())){
                            birthday =  LocalDate.parse(element.getElementsByTagName("birthday").item(0).getTextContent());
                        }
                        if(!(element.getElementsByTagName("eye").item(0).getTextContent().isEmpty())){
                            eye =  Color.valueOf(element.getElementsByTagName("eye").item(0).getTextContent());
                        }

                        if(!(element.getElementsByTagName("hair").item(0).getTextContent().isEmpty())){
                             hair =  Color.valueOf(element.getElementsByTagName("hair").item(0).getTextContent());
                        }
                        if(!(element.getElementsByTagName("nationality").item(0).getTextContent().isEmpty())){
                             nationality =  Country.valueOf(element.getElementsByTagName("nationality").item(0).getTextContent());
                        }
                        if(!(element.getElementsByTagName("formOfEducation").item(0).getTextContent().isEmpty())){
                             formOfEducation =  FormOfEducation.valueOf(element.getElementsByTagName("formOfEducation").item(0).getTextContent());
                        }

                        Person person = new Person(nameP,birthday,eye,hair,nationality);
                        StudyGroup studyGroup = new StudyGroup(name,coordinates,count,formOfEducation,semester,person);
                        CollectionManager.initializeStack();
                        studyGroups.push(studyGroup);
                    }
                }
    }


}
