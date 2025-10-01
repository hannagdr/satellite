package satellite;

import java.io.*;

public class Satellite {

    static int[][] oldImage;
    static int[][] newImage;
    static int noOfRows, noOfCols;

    public static void main(String[] args) {

        try {
            inputReader();
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            reader.readLine();
            reader.readLine();

            oldImage = readImages(reader, noOfRows, noOfCols);
            newImage = readImages(reader, noOfRows, noOfCols);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Determine corners
        int x1 = findFirstDifferent(noOfRows, true);
        int x2 = findLastDifferent(noOfRows - 1, true);

        int y1 = findFirstDifferent(noOfCols, false);
        int y2 = findLastDifferent(noOfCols - 1, false);

        // Write output
        if (x1 > x2 || y1 > y2) {
            System.out.println("The two images are the same");
        } else {
            System.out.println((x1 + 1) + " " + (y1 + 1) + " " + (x2 + 1) + " " + (y2 + 1));
        }
    }

    //Reading input from file
    public static void inputReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            noOfRows = Integer.parseInt(reader.readLine().trim());
            noOfCols = Integer.parseInt(reader.readLine().trim());

            oldImage = new int[noOfRows][noOfCols];
            newImage = new int[noOfRows][noOfCols];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[][] readImages(BufferedReader reader, int rows, int cols) throws IOException {
        int[][] image = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            for (int j = 0; j < cols; j++) {
                image[i][j] = Integer.parseInt(parts[j]);
            }
        }
        return image;
    }

    //Determining corners
    private static int findFirstDifferent(int end, boolean checkRows) {
        int i = 0;
        while (i < end && (checkRows ? equalRows(i) : equalCols(i))) {
            i++;
        }
        return i;
    }

    private static int findLastDifferent(int start, boolean checkRows) {
        int i = start;
        while (i >= -1 && (checkRows ? equalRows(i) : equalCols(i))) {
            i--;
        }
        return i;
    }

    // Checking if a row is equal in both images
    public static boolean equalRows(int row) {
        for (int col = 0; col < noOfCols; col++) {
            if (oldImage[row][col] != newImage[row][col]) {
                return false;
            }
        }
        return true;
    }

    // Checking if a column is equal in both images
    public static boolean equalCols(int col) {
        for (int row = 0; row < noOfRows; row++) {
            if (oldImage[row][col] != newImage[row][col]) {
                return false;
            }
        }
        return true;
    }
}