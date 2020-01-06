import java.lang.Runtime;
public class CPU {
    /**
     * This prints out the number of processors.
     */
    public static void cpu() {
        System.out.println("Processors   : " + Runtime.getRuntime().availableProcessors());
        System.out.println();
    }
}
