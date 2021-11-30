package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class PrintAscending extends Command {
    private Receiver commandReciever;

    public PrintAscending (Receiver commandReciever) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.commandReciever = commandReciever;
    }

    /**
     * исполнить команду
     * @param args
     * @throws IOException
     */
    @Override
    protected void execute(String[] args) throws IOException {
        if (args.length > 1) {
            throw new NoRelevantException("У данной команды нет аргумента, попробуйте ввести еще раз");
        }
        commandReciever.printAscending();
    }
}
