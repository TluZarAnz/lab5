package commands;

import exceptions.ArgumentNeeded;

import java.io.IOException;

public class ExecuteScript extends Command{
    private Receiver commandReciever;

    public ExecuteScript (Receiver commandReciever) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.commandReciever = commandReciever;
    }

    /**
     * исполнить команду
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length < 1) {
            throw new ArgumentNeeded("Должен быть аргумент");
        }
        commandReciever.executeScript(args[1],4);
    }
}
