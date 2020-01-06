import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class computeFibFacE {
    /*
    This is the main method. This may not catch all edge cases, if a identifier is passed in without an argument,
    for example "-fib" instead of "-fib 6", the rest of the arguments will be read incorrectly.
     */
    public static void main(String[] args) {
        for (int i=0;i<args.length;i++) {
            if (args[i].equals("-fib")) {
                int value = Integer.parseInt(args[i+1]);
                if (value >= 0 & value <= 40){  // Check that it is valid input
                    System.out.println("Fibonacci of " + args[i + 1] + " is " + computeFib(value));
                }
                else {
                    System.out.println("Fibonacci valid range is [0, 40]");
                }
                i++;
            }
            else if (args[i].equals("-fac")) {
                BigInteger value = new BigInteger(args[i+1]);
                if (value.compareTo(BigInteger.ZERO) != -1 & value.compareTo(new BigInteger("2147483647")) != 1){ // Check that it is valid input
                    System.out.println("Factorial of " + args[i + 1] + " is " + computeFac(value));
                }
                else {
                    System.out.println("Factorial valid range is [0, 2147483647]");
                }
                i++;
            }
            else if (args[i].equals("-e")) {
                int value = Integer.parseInt(args[i+1]);
                if (value >= 1 & value <= 2147483647){ // Check that it is valid input
                    // This rounds the value to a precision of 16 decimal places, I am not sure if this is how Eric wants it done.
                    System.out.println("Value of e using " + args[i + 1] + " is " + computeE(value).round(new MathContext(17)));
                }
                else {
                    System.out.println("Valid e iterations range is [1, 2147483647]");
                }
                i++;
            }
            else { // Print help if arguments are not recognized.
                System.out.println("Argument " + args[i] + " " + args[i+1] + " not recognized.");
                System.out.println("--- Assign 1 Help ---\n" +
                        "  -fib [N] : Compute the Fibonacci of N; valid range [0, 40]\n" +
                        "  -fac [N] : Compute the factorial of N; valid range, [0, 2147483647]\n" +
                        "  -e [N] : Compute the value of 'e' using N iterations; valid range [1, 2147483647]");
                i++;
            }
        }
    }

    // This actually computes the fib value
    private static int computeFib(int value) {
        if (value == 0) {
            return 1;
        }
        else if(value == 1) {
            return 1;
        }
        else if(value == 2) {
            return 2;
        }
        else {
            return computeFib(value-1) + computeFib(value-2);
        }
    }

    // This computes the factorial value
    private static BigInteger computeFac(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ONE;
        }
        return value.multiply(computeFac(value.subtract(BigInteger.ONE)));
    }

    // This computes the value of e to value iterations.
    private static BigDecimal computeE(int value) {
        BigDecimal finalValue = BigDecimal.ZERO;
        for (int i=0;i <= value; i++) {
            // The term value is calculated, but I am not sure what to set the MathContext to. A bigger MathContext should
            // result in more precision, but UNLIMITED will throw an ArithmeticException with values like 1/3.
            //BigDecimal termValue = BigDecimal.ONE.divide(new BigDecimal( computeFac( new BigInteger(Integer.toString(i)) ).toString() ), new MathContext(16));
            BigDecimal termValue = BigDecimal.ONE.divide(new BigDecimal( computeFac( new BigInteger(Integer.toString(i)) ).toString() ), MathContext.DECIMAL128);
            finalValue = finalValue.add(termValue);
        }
        return finalValue;
    }
}
