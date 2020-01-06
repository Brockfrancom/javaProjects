import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 Brock Francom
 A02052161
 **/
public class Assign3 {
    public static ProcessBuilder process = new ProcessBuilder();
    public static Long start = System.currentTimeMillis();
    public static Long end = System.currentTimeMillis();
    public static boolean processRunning = false;
    public static void main(String[] args) {
    System.out.println("\nWelcome to Brock's command prompt. :)");
    System.out.println("Please enter your command.");
    System.out.print("[" + System.getenv().get("PWD") + "]: ");
    while(true){
        try {
            boolean functionRan = false;
            Scanner input = new Scanner(System.in);
            if (input.hasNextLine()) {
                String commandp = input.nextLine();
                String[] command = splitCommand(commandp);
                for (int i=0;i<command.length;i++){
                    if (command[i].equals("|")){
                        Commands.pipe(command);
                        functionRan = true;
                        Commands.historyList.add(commandp);
                    }
                }
                if (command[0].equals("exit")) {
                    System.exit(0);
                }
                if (command[0].startsWith("ptime")) {
                    Commands.ptime(processRunning);
                    functionRan = true;
                    processRunning = false;
                    Commands.historyList.add(commandp);
                }
                if (command[0].startsWith("history")) {
                    System.out.print(Commands.history());
                    functionRan = true;
                    Commands.historyList.add(commandp);
                }
                if (command[0].startsWith("list")) {
                    Commands.list();
                    functionRan = true;
                    Commands.historyList.add(commandp);
                }
                if (command[0].startsWith("cd")) {
                    Commands.cd(command);
                    functionRan = true;
                    Commands.historyList.add(commandp);
                }
                if (command[0].startsWith("^")) {
                    Commands.repeat(command[1]);
                    functionRan = true;
                }
                if (!functionRan) {
                    processRunning = true;
                    start = System.currentTimeMillis();
                    ExecuteExternalCommand.execute(command);
                    end = System.currentTimeMillis();
                    processRunning = false;
                    Commands.historyList.add(commandp);
                }
                System.out.print("[" + System.getenv().get("PWD") + "]: ");
            }
        }
        catch (Exception e){
            System.out.println("Invalid command.");
            System.out.print("[" + System.getenv().get("PWD") + "]: ");
        }
    }
}
/**
 * Split the user command by spaces, but preserving them when inside double-quotes.
 * Code Adapted from: https://stackoverflow.com/questions/366202/
 regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
 **/
public static String[] splitCommand(String command) {
        java.util.List<String> matchList = new java.util.ArrayList<>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(command);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }
        return matchList.toArray(new String[matchList.size()]);
    }
}