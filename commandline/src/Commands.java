import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commands {
    static ArrayList historyList = new ArrayList();
    public static void list() {
        try {
            Assign3.process.command("ls");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Assign3.process.start().getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                list1(line);
            }
        }
        catch (Exception e) {
        }
    }
    private static void list1(String theFile) {
        File file = new File(theFile);
        String dirVar;
        String readVar;
        String writeVar;
        String executeVar;
        boolean dir = file.isDirectory();
        boolean read = file.canRead();
        boolean write = file.canWrite();
        boolean execute = file.canExecute();
        long space = file.length();
        long modified = file.lastModified();
        Date date = new Date(modified);
        String[] list = date.toString().split(" ");
        String[] time = list[3].split(":");
        String datef = list[1] + " " + list[2] + ", "  + list[5] +  " " + time[0] + ":" + time[1];
        if (dir) {
            dirVar = "d";
        }
        else {
            dirVar = "-";
        }
        if (read) {
            readVar = "r";
        }
        else {
            readVar = "-";
        }
        if (write) {
            writeVar = "w";
        }
        else {
            writeVar = "-";
        }
        if (execute) {
            executeVar = "x";
        }
        else {
            executeVar = "-";
        }
        System.out.printf("%s%s%s%s %10d %s %s", dirVar,readVar,writeVar,executeVar,space,datef,theFile);
        System.out.println();
        return;
    }
    public static void cd(String[] command) {
        try {
            List<String> commandsLeft = new ArrayList<String>();
            List<String> commandsRight = new ArrayList<String>();
            boolean rightCommand = false;
            for(int i=0;i<command.length;i++) {
                if(command[i].equals("|")){
                    rightCommand = true;
                }
                if (!rightCommand && !command[i].equals("|")){
                    commandsLeft.add(command[i]);
                }
                else if (!command[i].equals("|")){
                    commandsRight.add(command[i]);
                }
            }
            ProcessBuilder processLeft = new ProcessBuilder(commandsLeft);
            Process left = processLeft.start();

            ProcessBuilder processRight = new ProcessBuilder(commandsRight);
            Process right = processRight.start();


            java.io.InputStream in = left.getInputStream();
            java.io.OutputStream out = right.getOutputStream();
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            out.flush();
            out.close();
            processRight.inheritIO();
        }
        catch (Exception e) {
            System.out.println("Invalid command.");
            System.out.println(e);
        }
    }
    public static void ptime(boolean processRunning) {
        if (processRunning) {
            Long end = System.currentTimeMillis();
            float time = ((float) (end - Assign3.start)) / 1000;
            System.out.printf("Total time in child processes: " + time + " seconds\n", "%.4d");
        }
        else {
            System.out.println("No process running.");
        }
    }
    public static void pipe(String[] commandp) {
        try {
            List<String> commands = new ArrayList<String>();
            commands.add("/bin/sh");
            commands.add("-c");
            for (int i = 0; i < commandp.length; i++) {
                commands.add(commandp[i]);
            }
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        }
        catch (Exception e) {

        }
    }
    public static void repeat(String commands) {
        int command;
        String executeString = "";
        boolean functionRan = false;
        try {
            command = Integer.parseInt(commands);
        }
        catch (Exception e) {
            System.out.println("Invalid command.");
            historyList.add("^" + commands);
            return;
        }
        if (command <= historyList.size() && command > 0) {
            executeString += historyList.get(command-1);
        }
        else {
            System.out.println("Command history only contains the last " + historyList.size() + " commands.");
            return;
        }
        if (executeString.contains("|")){
            String[] commandString = Assign3.splitCommand(executeString);
            Commands.pipe(commandString);
            functionRan = true;
            historyList.add(executeString);
        }
        if (executeString.equals("ptime")) {
            ptime(false);
            functionRan = true;
            Assign3.processRunning = false;
            historyList.add(executeString);
        }
        if (executeString.equals("history")) {
            System.out.print(history());
            functionRan = true;
            historyList.add(executeString);
        }
        if (executeString.equals("list")) {
            list();
            functionRan = true;
            historyList.add(executeString);
        }
        if (executeString.startsWith("cd")) {
            String[] commandString = Assign3.splitCommand(executeString);
            cd(commandString);
            functionRan = true;
            historyList.add(executeString);
        }
        if (!functionRan) {
            String[] commandString = Assign3.splitCommand(executeString);
            ExecuteExternalCommand.execute(commandString);
            historyList.add(executeString);
        }
    }
    public static String history() {
        String returnString = "";
        for (int i = 0;i<historyList.size(); i++) {
            returnString += Integer.toString(i+1) + " : " + historyList.get(i) + "\n";
        }
        return returnString;
    }
}
