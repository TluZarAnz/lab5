package commands;

import exceptions.ArgumentNeeded;

import java.io.IOException;

public class UpdateId extends Command{
    private Receiver commandReciever;

    public UpdateId (Receiver commandReciever) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.commandReciever = commandReciever;
    }

    /**
     * исполнить команду
     * @param args
     * @throws IOException
     */
    @Override
    protected void execute(String[] args) throws IOException {
        if (args.length < 1) {
            throw new ArgumentNeeded("Должен быть аргумент");
        }
        commandReciever.updateId(args[1]);
    }
}
