/**
 * Assignment 8 for CS 1410
 * This program demonstrates recursion
 *
 * @author Brock Francom
 */
public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double weights[][] = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < weights.length; row++) {
            for (int column = 0; column < weights[row].length; column++) {
                double weight = computePyramidWeights(weights, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char image[][] = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }

    protected static int howManyOrganisms(char[][] image) {
        char[][] map = image; //to keep track of where you've been
        int organisms = 0;
        for (int i=0; i < image.length; i++){
            for (int j=0; j < image[i].length; j++){
                if (image[i][j] == '*'){
                    if (map[i][j] != 'a') {
                        organisms += 1;
                        howManyOrganisms(map, image, i, j, organisms);
                    }
                }
            }
        }
        return organisms;
    }
    protected static void howManyOrganisms(char[][] map, char[][] image, int i, int j, int organisms){
        char character = 'a';
        int ascii = ((int) character) + organisms-1;
        char character2 = (char) ascii;
        map[i][j] = character2;
        //check if it is out of bounds and then see if it is the same organism.
        if (j+1 < image[i].length) {
            if (image[i][j + 1] == '*') {
                howManyOrganisms(map, image, i, j + 1, organisms);
            }
        }
        if (j-1 >= 0) {
            if (image[i][j - 1] == '*') {
                howManyOrganisms(map, image, i, j - 1,organisms);
            }
        }
        if (i+1 < image.length && j < image[i+1].length) {
            if (image[i + 1][j] == '*') {
                howManyOrganisms(map, image, i + 1, j,organisms);
            }
        }
        if (i-1 >= 0 && j < image[i-1].length) {
            if (image[i - 1][j] == '*') {
                howManyOrganisms(map, image, i - 1, j,organisms);
            }
        }
    }

    protected static double computePyramidWeights(double[][] weights, int row, int column) {
        if (row < 0)
            return 0;
        else if (column < 0)
            return (0);
        else if (row >= weights.length)
            return 0;
        else if (column >= weights[row].length)
            return (0);
        else {
            double v = computePyramidWeights(weights, row - 1, column - 1);
            double v1 = computePyramidWeights(weights, row - 1, column);
            double v2 = weights[row][column];
            return (0.5 * (v + v1) + v2);
        }
    }

    protected static Boolean isWordSymmetric(String[] words, int i, int i1) {
        if (i == i1)
            return true;
        if (words[i].toLowerCase().equals(words[i1].toLowerCase()))
            return isWordSymmetric(words, i+1, i1-1);
        else
            return false;
    }

    protected static int arrayMin(int[] minMe, int i) {
        if(i == minMe.length)
            return minMe[i-1];
        return Math.min(minMe[i], arrayMin(minMe, i+1));
    }
    protected static int arraySum(int[] sumMe, int i) {
        if (i >= sumMe.length)
            return 0;
        return (arraySum(sumMe, i + 1) + sumMe[i]);
    }
}
