package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class History extends Command{
    private Receiver commandReciever;

    public History (Receiver commandReciever) {
        super("history", "вывести последние 12 команд (без их аргументов)");
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
        commandReciever.history();
    }
}
