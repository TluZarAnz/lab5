package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class Save extends Command{

    private Receiver commandReciever;

    public Save (Receiver commandReciever) {
        super("save", "сохранить коллекцию в файл");
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
        commandReciever.save();
    }
}
