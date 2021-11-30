package commands;

import java.util.Stack;

/**
 * шаблон команда
 * Класс Invoker скрывает деталь вызова в реализацию конечной точки программы.
 */
public  class Invoker {
    private static final Stack<Command> commands = new Stack<>();

    /**
     *Добавляет команды в отдельную коллекцию для команд
     * @param command
     */
    public void register(Command command) {
        commands.push(command);
    }

    Stack<Command> getCommands() {
        return commands;
    }
}
