public class Assign1 {
    /**
     This is the main class, this takes the command line arguments, and calls the corresponding
     class.
     **/
    public static void main(String[] args) {
        for (int i=0;i<args.length;i++) {
            if (args[i].equals("-cpu")) {
                CPU.cpu();
            }
            else if (args[i].equals("-mem")) {
                Mem.mem();
            }
            else if (args[i].equals("-dirs")) {
                Dirs.dirs();
            }
            else if (args[i].equals("-os")) {
                OS.os();
            }
            else if (args[i].equals("-java")) {
                Java.java();
            }
            else { // Print help if arguments are not recognized.
                System.out.println("Argument '" + args[i] + "' not recognized.");
                System.out.println();
            }
        }
    }
}
