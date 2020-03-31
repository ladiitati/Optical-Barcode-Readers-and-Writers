import java.util.Arrays;

public class Assign4 {
    public static void main(String[] args) {
        testPhaseTwo();
    }

    /**
     * testPhaseTwo is only for the purpose of testing the
     * BarcodeImage class. Feel free to delete later.
     */
    public static void testPhaseTwo() {
        String[] phaseTwoTest = new String[]{
                "* * * * * * * * * * * * * * * * * *",
                "*                                 *",
                "***** ** * **** ****** ** **** **  ",
                "* **************      *************",
                "**  *  *        *  *   *        *  ",
                "* **  *     **    * *   * ****   **",
                "**         ****   * ** ** ***   ** ",
                "*   *  *   ***  *       *  ***   **",
                "*  ** ** * ***  ***  *  *  *** *   ",
                "***********************************"
        };
        BarcodeImage b = new BarcodeImage(phaseTwoTest);
        BarcodeImage c = (BarcodeImage) b.clone();
        c.displayToConsole();
    }
}

interface BarcodeIO {
    public boolean scan(BarcodeImage bc);

    public boolean readText(String text);

    public boolean generateImageFromText();

    public boolean translateImageToText();

    public void displayTextToConsole();

    public void displayImageToConsole();
}

class BarcodeImage implements Cloneable {
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 65;
    private boolean[][] imageData;

    BarcodeImage(String[] strData) {
        boolean[][] image = new boolean[MAX_HEIGHT][MAX_WIDTH];

        // check if input is valid
        if (!checkSize(strData)) {
            return;
        }
        ;
        this.setImageData(image);

        for (int i = 0; i < strData.length; i++) {
            /**
             * for each character in the strings set booleans
             * in the matrix starting at the bottom left.
             * */
            for (int j = 0; j < strData[i].length(); j++) {
                if (strData[(strData.length - 1) - i].charAt(j) == '*') {
                    setPixel((MAX_HEIGHT - 1) - i, j, true);
                } else {
                    setPixel((MAX_HEIGHT - 1) - i, j, false);
                }
            }
        }
    }

    // sets every value to false if no data passed in
    BarcodeImage() {
        boolean[][] image = new boolean[MAX_HEIGHT][MAX_WIDTH];
        this.setImageData(image);

        // iterate through every column and row
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                this.setPixel(i, j, false);
            }
        }
    }

    public void setImageData(boolean[][] imageData) {
        this.imageData = imageData;
    }

    public boolean[][] getImageData() {
        return imageData;
    }

    public boolean getPixel(int row, int col) {
        try {
            return imageData[row][col];
        } catch (Exception e) {
            return false;
        }
    }

    public void setPixel(int row, int col, boolean value) {
        try {
            this.imageData[row][col] = value;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private boolean checkSize(String[] data) {
        if (data.length <= MAX_HEIGHT) {
            for (String datum : data) {
                if (datum != null && datum.length() <= MAX_WIDTH) {
                    return true;
                }
            }
        }
        return false;
    }

    public void displayToConsole() {
        boolean[][] image = this.getImageData();

        for (int i = 0; i < MAX_HEIGHT; i++) {
            System.out.println(Arrays.toString(image[i]));
        }
    }

    @Override
    public Object clone() {
        BarcodeImage cloneImage;
        try {
            cloneImage = (BarcodeImage) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        return (BarcodeImage) cloneImage;
    }
}
