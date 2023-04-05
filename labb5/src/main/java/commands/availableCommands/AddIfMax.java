package commands.availableCommands;

import collection.PersonCollection;
import commands.Command;

import java.util.Scanner;

/**
 * add_if_max {element}
 */
public class AddIfMax extends Command {
    private final PersonCollection personCollection;

    public AddIfMax(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    /**
     * add a new element to a collection if its value exceeds the value of the largest element of that collection
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 2) {
                System.out.println("Вы неправильно ввели команду");
            } else {
                personCollection.addIfMax(args[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недостаточно аргументов, обратитесь к команде help");
        }
    }
}