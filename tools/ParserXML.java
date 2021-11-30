package tools;

import utils.Person;
import utils.StudyGroup;

import java.io.*;
import java.util.Stack;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ParserXML {
    /**
     * Парсер для сохранения коллекции в формате Xml
     * @param studyGroups
     * @param file
     * @throws IOException
     */
    public static void writeInXml(Stack<StudyGroup> studyGroups, String file) throws IOException {
        try{
            org.jdom2.Document doc = new org.jdom2.Document();
            org.jdom2.Element root = new org.jdom2.Element("studyGroups");
            doc.setRootElement(root);
            for(StudyGroup studyGroup : studyGroups){
                org.jdom2.Element currentStudyGroup = new org.jdom2.Element("StudyGroup");
                currentStudyGroup.setAttribute("id", String.valueOf(studyGroup.getId()));
                currentStudyGroup.addContent(new org.jdom2.Element("name").addContent(studyGroup.getName()));
                org.jdom2.Element coords = new org.jdom2.Element("coordinates")
                        .addContent(new org.jdom2.Element("x").addContent(String.valueOf(studyGroup.getCoordinates().getX())))
                        .addContent(new org.jdom2.Element("y").addContent(String.valueOf(studyGroup.getCoordinates().getY())));
                currentStudyGroup.addContent(coords);
                currentStudyGroup.addContent(new org.jdom2.Element("creationDate").addContent(studyGroup.getCreationDate().toString()));
                currentStudyGroup.addContent(new org.jdom2.Element("studentCount").addContent(String.valueOf(studyGroup.getStudentsCount())));
                currentStudyGroup.addContent(new org.jdom2.Element("formOfEducation").addContent(String.valueOf(studyGroup.getFormOfEducation())));
                currentStudyGroup.addContent(new org.jdom2.Element("semester").addContent(String.valueOf(studyGroup.getSemesterEnum())));
                Person groupAdmin = studyGroup.getGroupAdmin();
                org.jdom2.Element groupAdminAdd = new org.jdom2.Element("person")
                        .addContent(new org.jdom2.Element("nameP").addContent(groupAdmin.getName()))
                        .addContent(new org.jdom2.Element("birthday").addContent(String.valueOf(groupAdmin.getBirthday())))
                        .addContent(new org.jdom2.Element("eye").addContent(String.valueOf(groupAdmin.getEyeColor())))
                        .addContent(new org.jdom2.Element("hair").addContent(String.valueOf(groupAdmin.getHairColor())))
                        .addContent(new org.jdom2.Element("nationality").addContent(String.valueOf(groupAdmin.getNationality())));
                currentStudyGroup.addContent(groupAdminAdd);
                root.addContent(currentStudyGroup);
            }
            Format fmt = Format.getPrettyFormat();
            XMLOutputter outputter = new XMLOutputter(fmt);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            outputter.output(doc, bos);
            bos.flush();
            bos.close();
        }catch (IOException e) {
            System.out.println("Ошибка в парсере");
        }


    }

}
