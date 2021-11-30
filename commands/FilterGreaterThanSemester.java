package commands;

import exceptions.ArgumentNeeded;

import java.io.IOException;

public class FilterGreaterThanSemester extends Command{
    private Receiver commandReciever;

    public FilterGreaterThanSemester (Receiver commandReciever) {
        super("filter_greater_than_semester_enum", "вывести элементы, значение поля semesterEnum которые больше заданного");
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
        commandReciever.filterG(args[1]);
    }
}
