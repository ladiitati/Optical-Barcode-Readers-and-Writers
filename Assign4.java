import java.util.Arrays;

import jdk.internal.jshell.tool.resources.l10n;

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
        b.displayToConsole();
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

    public boolean getPixel(int col, int row) {
        try {
            return imageData[col][row];
        } catch (Exception e) {
            return false;
        }
    }

    public void setPixel(int col, int row, boolean value) {
        try {
            this.imageData[col][row] = value;
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
}
class DataMatrix implements BarcodeIO {

    public static final char BLACK_CHAR = '*';
    public static final char WHITE_CHAR = ' ';
    private BarcodeImage image;
    private String text;
    private int actualWidth;
    private int actualHeight;
    
    DataMatrix() {
        this.text = "";
        this.image = new BarcodeImage();
        this.actualWidth = 0;
        this.actualHeight = 0;
    };

    DataMatrix(BarcodeImage image) {
        this();
        scan(image);
    }

    DataMatrix(String text) {
        this();
        this.readText(text);
    }

    public boolean readText(String text) {
        if(text.length() < BarcodeImage.MAX_WIDTH){
            this.text = text + "*";
            return true;
        }
        return false;
    }

    public boolean scan(BarcodeImage iBarcodeImage){
        try {
            this.image = image.clone();
            this.cleanImage();
            this.actualWidth = this.computeSignalWidth();
            this.actualHeight = this.computeSignalHeight();
            return true;
            }
            catch(Exception e) {
            return false;
        }
   }
}
