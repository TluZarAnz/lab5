package commands;

import exceptions.ArgumentNeeded;
import exceptions.NoRelevantException;
import exceptions.NoValueException;
import exceptions.ValueTooLowException;
import tools.StringTool;
import utils.StudyGroupFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


public class ConsoleManager {
    public void InteractiveMode() throws IOException {

        StudyGroupFactory studyGroup = new StudyGroupFactory();
        Invoker commandInvoker = new Invoker();
        Receiver commandReciever = new Receiver(commandInvoker, studyGroup);
        commandInvoker.register( new Help(commandReciever));
        commandInvoker.register( new ExecuteScript(commandReciever));
        commandInvoker.register( new Add(commandReciever));
        commandInvoker.register(new Info(commandReciever));
        commandInvoker.register(new Show(commandReciever));
        commandInvoker.register(new History(commandReciever));
        commandInvoker.register(new RemoveById(commandReciever));
        commandInvoker.register( new Clear(commandReciever));
        commandInvoker.register(new Exit(commandReciever));
        commandInvoker.register(new Save(commandReciever));
        commandInvoker.register(new Reorder(commandReciever));
        commandInvoker.register(new PrintAscending(commandReciever));
        commandInvoker.register(new PrintDescending(commandReciever));
        commandInvoker.register(new FilterGreaterThanSemester(commandReciever));
        commandInvoker.register(new RemoveLower(commandReciever));
        commandInvoker.register(new UpdateId(commandReciever));

        System.out.println("Введите команду \"help\" для простой навигации для работы с коллекции");
        while (true){
            boolean isCommandExist = false;
            Invoker commandHandle = new Invoker();
            Command command;
            Iterator<Command> iterator = commandHandle.getCommands().stream().iterator();
            System.out.print(">>");
            String[] commands = StringTool.Input().split(" ");
            try {
                while (iterator.hasNext()) {
                    if ((command = iterator.next()).getName().equalsIgnoreCase(commands[0])) {
                        command.execute(commands);
                        isCommandExist = true;
                    }
                }
                if (!isCommandExist) {
                    System.out.println("Такой команды нет, убедитесь что название было введено правильно");
                }
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }catch (NoRelevantException | ArgumentNeeded  | IllegalStateException | ValueTooLowException
                    | NoValueException ex ){
                System.out.println(ex.getMessage());
            }catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("Такой команды нет или неправильно введены аргументы");
            }catch (FileNotFoundException ex){
                System.out.println("Такого файла нет");
            }
        }
    }
}
