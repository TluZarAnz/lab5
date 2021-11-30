package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class Clear extends Command {

    private Receiver commandReciever;

    public Clear (Receiver commandReciever) {
        super("clear", "очистить коллекцию");
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
        commandReciever.clear();
    }
}
