package commands.availableCommands;

import client.ClientManager;
import collection.PersonCollection;
import commands.Command;
import data.Person;

import java.util.Scanner;

/**
 * Command update id {element} : update the value of the collection item whose id is equal to the given one
 */
public class Update extends Command {
    private final PersonCollection personCollection;

    public Update (PersonCollection personCollection) {
        this.personCollection = personCollection;
    }
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Scanner sc = new Scanner(System.in);
            update(args[1], sc);
        }
    }

    public void update(String arg, Scanner sc) {
        try {
            int ID = Integer.parseInt(arg);
            if (personCollection.existID(ID)) {
                personCollection.updateElement(ClientManager.getNewPerson(sc), ID);
                System.out.println("Персонаж обновлен");
            } else {
                System.out.println("Человека с таким ID не существует");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID введен неверно");
        }
    }
    public boolean updateForScript(String id_s) {
        try {
            int ID = Integer.parseInt(id_s);
            if (personCollection.existID(ID)) {
                System.out.println("Персонаж обновлен");
                return true;
            } else {
                System.out.println("Человека с таким ID не существует");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("ID введен неверно");
            return false;
        }
    }
}

