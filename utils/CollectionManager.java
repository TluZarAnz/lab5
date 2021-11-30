package utils;

import tools.StringTool;
import java.io.*;
import java.util.*;
import tools.*;
import commands.Invoker;

/**
 * Класс для работы с командами относящимися к коллекции
 */
public class CollectionManager {
    private static Stack<StudyGroup> collection;
    private static Date creationDate;
    private static boolean findId=false;

    /**
     * Инициализация коллекции
     */
    public static void initializeStack() {
        if (collection == null) {
            collection = new Stack<StudyGroup>();
            creationDate = new Date();
        }
    }

    public static void removeById(Integer id){
        StudyGroup sM=findById(id);
        if(!findId){
            System.out.println("no such");
        }
        findId=false;
        collection.remove(sM);
    }
    public static void filterGreater(String option){
        Stack<StudyGroup> spaceMarinesFl = new Stack<>();
        Integer intop = null;

        for (StudyGroup spaceMarine : collection){
            try {
                switch (option.toUpperCase()) {
                    case ("FOURTH") :
                        intop = 4;
                        break;
                    case ("FIFTH") :
                        intop = 5;
                        break;
                    case ("SEVENTH") :
                        intop = 7;
                        break;
                }

                if(intop<spaceMarine.getSemesterEnum().getValue() ){
                    spaceMarinesFl.add(spaceMarine);
                    System.out.println(spaceMarine);}
            }catch (IllegalStateException ex) {
                System.out.println("Unexpected value: " + option);
            }catch (NullPointerException ex){System.out.println("no such");}

        }

        if (spaceMarinesFl.isEmpty()){
            System.out.println("Не найдены продукты с таким владельцем");
        }
    }
    public static void add(StudyGroup studyGroup) {
        initializeStack();
        collection.push(studyGroup);
    }
    public static void show() {

        initializeStack();

        if (collection.size()==0){
            System.out.println("collection still empty");
        }
        new StringTool().PrintLabWorkSet(collection);
    }
    public static void exit() {
        System.out.print("exit program");
        System.exit(0);
    }
    public static void info() {
        System.out.print("the amount of elements "+ collection.size()+ "\n");
        System.out.print("the type of collection is "+ collection.getClass() +"\n");
        System.out.println("Дата - "+ creationDate);
    }
    public static void save() throws IOException {
        File fff = new File("ScriptFile/file.xml");
        String fd = fff.getAbsolutePath();
        ParserXML.writeInXml(collection,fd);
    }
    public static void clear() throws IOException {
        File f = new File("ScriptFile/"+"file.xml");
        FileWriter fwOb = new FileWriter(f, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        collection.clear();
    }
    public static void reorder(){
        Collections.reverse(collection);

    }
    public static void printAscending(){
        StudyGroupComparator compareSpaceMarine = new StudyGroupComparator();
        collection.sort(compareSpaceMarine);

    }
    public static void printDescending(){
        StudyGroupComparator compareSpaceMarine = new StudyGroupComparator();
        Collections.sort(collection, Collections.reverseOrder(compareSpaceMarine));

    }
    public static void removeLower(){
        StudyGroup insert = StudyGroupFactory.studyGroupFactory();
        collection.removeIf(entry -> entry.getStudentsCount()<insert.getStudentsCount());
    }
    public static void updateId(String in){
        Integer id=Integer.valueOf(in);
        StudyGroup spaceMarine;
        Iterator<StudyGroup> iterator=collection.stream().iterator();
        while (iterator.hasNext()) {
            if ((spaceMarine = iterator.next()).getId().equals(id)) {
                collection.remove(spaceMarine);
                StudyGroup insert = StudyGroupFactory.studyGroupFactory();
                insert.setId(id);
                collection.push(insert);
                break;
            }
        }
    }

    /**
     * Вспомогательный метод для нахождения элемента коллекции по ID
     * @param id
     * @return
     */
    private static StudyGroup findById(Integer id){
        StudyGroup p=null;
        StudyGroup m;
        Iterator<StudyGroup> iterator=collection.stream().iterator();
        out:while(iterator.hasNext()){
            if((m = iterator.next()).getId().equals(id)){
                p=m;
                findId=true;
                break out;
            }
        }
        return p;
    }

    public static Stack<StudyGroup> getCollection() {
        return collection;
    }
}
