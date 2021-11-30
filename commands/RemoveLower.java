package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class RemoveLower extends Command {
    private Receiver commandReciever;

    public RemoveLower (Receiver commandReciever) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
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
        commandReciever.removeLower();
    }
}
