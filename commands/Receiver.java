package commands;

import exceptions.NoValueException;
import exceptions.ValueTooLowException;
import utils.*;
import java.io.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

/**
 * шаблон команда
 * класс Receiver (получатель) – располагает информацией о способах выполнения операций.
 */
public class Receiver {

    private final Invoker commandInvoker;
    private final StudyGroupFactory creator;
    private  Stack<String> history = new Stack<>();

    public Receiver(Invoker commandInvoker,  StudyGroupFactory creator) {
        this.commandInvoker = commandInvoker;
        this.creator = creator;
    }

    public void help() {
        Iterator<Command> iterator = commandInvoker.getCommands().iterator();
        history.push("help");
        while (iterator.hasNext()) {
            Command A = iterator.next();
            System.out.print(A.getName() + ": "+A.getDescription() + "\n");
        }
    }

    public  void add() {
        history.push("add");

        CollectionManager.add(StudyGroupFactory.studyGroupFactory());
    }

    public void removeById(String id){
        history.push("remove_by_id");
        CollectionManager.removeById( Integer.parseInt(id));
    }

    public void filterG(String op){
        history.push("filter_greater_than_semester_enum");
        CollectionManager.filterGreater(op);
    }

    public  void show() {
        history.push("show");

        CollectionManager.show();

    }

    public  void exit() {
        CollectionManager.exit();
    }
    public  void save() throws IOException {
        history.push("save");

        CollectionManager.save();

    }
    public  void clear() throws IOException {
        history.push("clear");

        CollectionManager.clear();

    }
    public  void info() {
        history.push("info");
        CollectionManager.info();


    }

    public void  history(){
        history.push("history");
        System.out.println(history.subList(Math.max(history.size()-12,0),history.size()));
    }

    public void printAscending(){
        history.push("print_ascending");
        CollectionManager.printAscending();

    }
    public void printDescending(){
        history.push("print_descending");
        CollectionManager.printDescending();

    }
    public void reorder(){
        history.push("reorder");
        CollectionManager.reorder();

    }

    public void removeLower(){
        history.push("remove_lower");
        CollectionManager.removeLower();
    }

    public void updateId(String in){
        history.push("update");
        CollectionManager.updateId( in);
    }

    public  void executeScript(String name,int n) throws IOException, ClassNotFoundException, InterruptedException {
        history.push("execute_script");
        String folder = System.getenv("IN_PATH");
        File fv = null;
        if (n==0){System.out.println("Скажем нет рекурсии!");
        }else {
            if(!(folder==null)){
                System.out.println("Переменная окружения была успешна использована");
                System.out.println(folder);
                fv = new File(folder + name);
            }else {
                System.out.println("Переменная окружения не была использована");
                System.out.println("Тогда используем значение из командной строки");
                fv = new File("ScriptFile/" + name);
            }

            String fd = fv.getAbsolutePath();
            FileInputStream f = new FileInputStream(fd);
            BufferedInputStream isr = new BufferedInputStream(f);
            Scanner s = new Scanner(isr).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            Scanner scanner = new Scanner(result);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");
                Command command = findCommand(split[0]);
                if(command==null){
                    if (split[0].equalsIgnoreCase("clear")){clear();}
                    if (split[0].equalsIgnoreCase("add")){addToScript(scanner,command);}
                    if (split[0].equalsIgnoreCase("help")){help();}
                    if (split[0].equalsIgnoreCase("save")){save();}
                    if (split[0].equalsIgnoreCase("info")){info();}
                    if (split[0].equalsIgnoreCase("show")){show();}
                    if (split[0].equalsIgnoreCase("exit")){exit();}
                    if (split[0].equalsIgnoreCase("reorder")){reorder();}
                    if (split[0].equalsIgnoreCase("execute_script")){
                        executeScript(split[1],n);
                    }
                    if (split[0].equalsIgnoreCase("print_descending")){printDescending();}
                    if (split[0].equalsIgnoreCase("print_ascending")){printAscending();}
                    if (split[0].equalsIgnoreCase("history")){history();}
                    if (split[0].equalsIgnoreCase("remove_lower")){removeLower();}
                    if (split[0].equalsIgnoreCase("filter_greater_than_semester_enum")){filterG(split[1].toUpperCase());}
                }
                if (command != null && !(command.getName().equals("add"))&& !(command.getName().equals("execute_script"))) {
                    command.execute(split);
                }
                if (command != null && command.getName().equals("execute_script")) {
                    n--;
                    System.out.println(split[1]);
                    executeScript(split[1],n);
                }
                if (command != null && command.getName().equalsIgnoreCase("add")){
                    addToScript(scanner, command);
                }
            }
            scanner.close();
            isr.close();
            s.close();
            f.close();
        }
    }

    /**
     * Исполняет проверку на наличие команд
     * @param name
     * @return
     */
    public  Command findCommand(String name){
        Command A=null;
        Command B;

        Iterator<Command> iterator=commandInvoker.getCommands().stream().iterator();

        while(iterator.hasNext()){
            if((B=iterator.next()).getName().equalsIgnoreCase(name)) {
                A = B;
            }
        }
        return A;
    }

    /**
     * Добавляет Объект коллекции из скрипта
     * @param s
     * @param command
     */
    public void addToScript(Scanner s, Command command){
        String name, sFormOfStudy, sSemester, sColorH, sColorE,sCountry, studentName, sBirthday;
        String sX, sY,  sCount;
        Integer x;
        Integer y ;
        Long countOfStuds ;
        FormOfEducation formOfStudy = null;
        LocalDate birthday = null;
        Color colorE = null;
        Color colorH = null;
        Country country = null;
        while (s.hasNext()) {
            String line1 = s.nextLine();
            Scanner scR = new Scanner(line1);
            scR.useDelimiter("#");
            name = scR.next();
            sX = scR.next();
            sY = scR.next();
            sCount = scR.next();
            if(name.isEmpty()){throw new NoValueException("При исполнении скрипта была обнаружена ошибка. У имени группы должно быть значение");}
            if(sX.isEmpty()){throw new NoValueException("При исполнении скрипта была обнаружена ошибка. У Х должно быть значение");}
            if(sY.isEmpty()){throw new NoValueException("При исполнении скрипта была обнаружена ошибка. У Y должно быть значение");}
            if(sCount.isEmpty()){throw new NoValueException("При исполнении скрипта была обнаружена ошибка. У количества студентов должно быть значение");}
                x = Integer.valueOf(sX);
                y=Integer.valueOf(sY);
                countOfStuds=Long.valueOf(sCount);
                if(x<-553){ throw new ValueTooLowException("При исполнении скрипта значение X было сишком мало");}
                if(y<-444){ throw new ValueTooLowException("При исполнении скрипта значение Y было сишком мало");}
                if(countOfStuds<0){ throw new ValueTooLowException("При исполнении скрипта значение количества студентов было сишком мало");}

            sFormOfStudy = scR.next();
            sSemester = scR.next();
            if(sSemester.isEmpty()){throw new NoValueException("При исполнении скрипта была обнаружена ошибка. У семестра должно быть значение");}
            studentName=scR.next();
            if(studentName.isEmpty()){throw new NoValueException("При исполнении скрипта была обнаружена ошибка. У студента должно быть имя");}
            sBirthday=scR.next();
            sColorE = scR.next();
            sColorH = scR.next();
            sCountry = scR.next();

            if(!(sFormOfStudy.isEmpty())){
                formOfStudy = FormOfEducation.valueOf(sFormOfStudy.toUpperCase());
            }
            if(!(sBirthday.isEmpty())){
                birthday = LocalDate.parse(sBirthday);
            }
            if(!(sColorE.isEmpty())){
                colorE = Color.valueOf(sColorE.toUpperCase());
            }
            if(!(sColorH.isEmpty())){
                colorH = Color.valueOf(sColorH.toUpperCase());
            }
            if(!(sCountry.isEmpty())){
                country = Country.valueOf(sCountry.toUpperCase());
            }

            Semester semester = Semester.valueOf(sSemester.toUpperCase());
            Coordinates coordinates = new Coordinates(x, y);
            Person person = new Person(studentName,birthday, colorE,colorH,country);

            CollectionManager.initializeStack();
            CollectionManager.getCollection().push(new StudyGroup(name,coordinates,countOfStuds, formOfStudy,semester,person));
            if(command!=null){break;}
        }
    }
}
