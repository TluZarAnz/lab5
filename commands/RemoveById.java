package commands;

import exceptions.ArgumentNeeded;

import java.io.IOException;

public class RemoveById extends Command{
    private Receiver commandReciever;

    public RemoveById (Receiver commandReciever) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
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
        commandReciever.removeById(args[1]);
    }
}
