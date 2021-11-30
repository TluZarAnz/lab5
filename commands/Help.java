package commands;

import exceptions.NoRelevantException;

import java.io.IOException;

public class Help extends Command {

    private Receiver commandReciever;

    public Help (Receiver commandReciever) {
        super("help", " вывести справку по доступным командам");
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
        commandReciever.help();
    }

}
