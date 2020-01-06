import java.lang.Runtime;
public class Mem {
    /**
     * This prints out memory information.
     */
    public static void mem() {
        System.out.format("Free Memory  :%,15d%n", Runtime.getRuntime().freeMemory());
        System.out.format("Total Memory :%,15d%n", Runtime.getRuntime().totalMemory());
        System.out.format("Max Memory   :%,15d%n", Runtime.getRuntime().maxMemory());
        System.out.println();
    }
}
