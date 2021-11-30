package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class Show extends Command{

    private Receiver commandReciever;

    public Show (Receiver commandReciever) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
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
        commandReciever.show();
    }
}
