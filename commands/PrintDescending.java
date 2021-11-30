package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class PrintDescending extends Command{
    private Receiver commandReciever;

    public PrintDescending (Receiver commandReciever) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
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
        commandReciever.printDescending();
    }
}
