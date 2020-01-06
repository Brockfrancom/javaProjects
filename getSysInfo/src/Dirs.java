import java.lang.System;
public class Dirs {
    /**
     * This prints out the different directories.
     */
    public static void dirs() {
        System.out.println("Working Directory   : " + System.getenv().get("PWD"));
        System.out.println("User Home Directory : " + System.getenv().get("HOME"));
        System.out.println();
    }
}
