package commands.availableCommands;

import collection.PersonCollection;
import commands.Command;

import java.util.Scanner;

/**
 * add_if_min {element} :
 */
public class AddIfMin extends Command {
    private final PersonCollection personCollection;

    public AddIfMin(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    /**
     * dd a new element to the collection if its value is less than the smallest element of that collection
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 2) {
                System.out.println("Вы неправильно ввели команду");
            } else {
                personCollection.addIfMin(args[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недостаточно аргументов, обратитесь к команде help");
        }
    }
}

