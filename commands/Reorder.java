package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class Reorder extends Command{
    private Receiver commandReciever;

    public Reorder (Receiver commandReciever) {
        super("reorder", "отсортировать коллекцию обратно заданной");
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
        commandReciever.reorder();
    }
}
