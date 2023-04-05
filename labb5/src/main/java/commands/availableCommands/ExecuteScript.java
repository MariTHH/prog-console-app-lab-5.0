package commands.availableCommands;

import client.ClientManager;
import collection.PersonCollection;
import commands.Command;
import commands.CommandManager;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;

/**
 * execute_script file_name : read and execute the script from the specified file.
 * The script contains commands in the same form as they are entered by the user in interactive mode.
 */
public class ExecuteScript extends Command {
    private final PersonCollection personCollection;
    private HashMap<String, Command> commandMap;
    private ArrayList<String> filePaths;
    ArrayList<String> personList = new ArrayList<>();

    public ExecuteScript(PersonCollection personCollection) {
        this.personCollection = personCollection;
        this.commandMap = CommandManager.getCommandMap();
        this.filePaths = new ArrayList<>();
    }

    /**
     * performs a script reading with command processing
     * reads the file lines by adding the commands to the array
     * if the "add" command, creates an array with the persons characteristics
     *
     * @param args
     * @throws JAXBException
     * @throws IOException
     */
    @Override
    public void execute(String[] args) throws JAXBException, IOException {
        if (args.length > 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            filePaths.add((String) getArgument());
            ArrayList<String> commandList = new ArrayList<>();
            try (Scanner reader = new Scanner(new FileInputStream((String) getArgument()))) {

                while (reader.hasNextLine()) {
                    String line = reader.nextLine().trim();
                    commandList.add(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            }

            for (String command : commandList) {
                while (command.contains("  "))
                    command = command.replaceAll("  ", " ");

                String[] commandAndArgument = command.split(" ");
                String argument;

                if (commandAndArgument.length == 1)
                    argument = null;
                else if (commandAndArgument.length == 2)
                    argument = commandAndArgument[1];
                else {
                    System.out.println("Введите комманду и аргумент, если нужно");
                    return;
                }
                Scanner reader = new Scanner(new FileInputStream((String) getArgument()));
                boolean a = !commandAndArgument[0].equals("add") && !commandAndArgument[0].equals("add_if_min") && !commandAndArgument[0].equals("add_if_max") && !commandAndArgument[0].equals("update") && !commandAndArgument[0].equals("remove_greater");
                if (commandMap.containsKey(commandAndArgument[0]) && a) {
                    if (commandAndArgument[0].equals("execute_script")) {
                        if (filePaths.contains(commandAndArgument[1])) {
                            System.out.println("Файл содержит рекурсию!!");
                            continue;
                        }
                    }
                    commandMap.get(commandAndArgument[0]).setArgument(argument);
                    commandMap.get(commandAndArgument[0]).execute(commandAndArgument);

                } else if (!a) {
                    String line1 = reader.nextLine();
                    while (line1.equals("add") && reader.hasNextLine()) {
                        reader.nextLine();
                    }
                    reader.nextLine();
                    for (int i = 0; i < 11; i++) {
                        while (reader.hasNextLine()) {
                            String line = reader.nextLine().trim();
                            personList.add(line);
                        }
                    }
                    personCollection.addPerson(ClientManager.createPersonFromScript(personList));
                    for (int i = 0; i < 10; i++) {
                        if (reader.hasNextLine()) {
                            reader.nextLine();
                        }
                    }
                }

            }
            filePaths.remove(getArgument());
        }
    }

}


