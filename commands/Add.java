package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class Add extends Command {

    private Receiver commandReciever;

    public Add (Receiver commandReciever) {
        super("add", "добавить новый элемент в коллекцию");
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
        }else {
        commandReciever.add();
        }

    }
}
