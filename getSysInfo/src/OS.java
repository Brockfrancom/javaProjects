import java.lang.System;
public class OS {
    /**
     * This prints out information about the OS.
     */
    public static void os() {
        System.out.println("OS Name             : " + System.getProperty("os.name"));
        System.out.println("OS Version          : " + System.getProperty("os.version"));
        System.out.println();
    }
}
