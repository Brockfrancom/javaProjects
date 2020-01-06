public class Java {
    /**
     * This prints out java information.
     */
    public static void java() {
        System.out.println("Java Vendor         : " + System.getProperty("java.vendor"));
        System.out.println("Java Runtime        : " + System.getProperty("java.runtime.version"));
        System.out.println("Java Version        : " + System.getProperty("java.version"));
        System.out.println("Java VM Version     : " + System.getProperty("java.vm.version"));
        System.out.println("Java VM Name        : " + System.getProperty("java.vm.name"));
        System.out.println();
    }
}
