import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExecuteExternalCommand {
    public static void execute(String[] command) {
        try {
            List<String> commands = new ArrayList<String>();
            for(int i=0;i<command.length;i++) {
                commands.add(command[i]);
            }
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.inheritIO();
            Process process = pb.start();
            if (commands.get(command.length-1)!="&") {
                process.waitFor();
            }
        }
        catch (Exception e) {
            System.out.println("Invalid command.");
        }
    }
}
