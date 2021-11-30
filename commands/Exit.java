package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class Exit extends Command{

    private Receiver commandReciever;

    public Exit (Receiver commandReciever) {
        super("exit", "завершить программу (без сохранения в файл)");
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
        commandReciever.exit();
    }
}
