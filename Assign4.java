public class assign4 {

    public static void main(String[] args) {
        String[] sImageIn =
        {
           "                                               ",
           "                                               ",
           "                                               ",
           "     * * * * * * * * * * * * * * * * * * * * * ",
           "     *                                       * ",
           "     ****** **** ****** ******* ** *** *****   ",
           "     *     *    ****************************** ",
           "     * **    * *        **  *    * * *   *     ",
           "     *   *    *  *****    *   * *   *  **  *** ",
           "     *  **     * *** **   **  *    **  ***  *  ",
           "     ***  * **   **  *   ****    *  *  ** * ** ",
           "     *****  ***  *  * *   ** ** **  *   * *    ",
           "     ***************************************** ",  
           "                                               ",
           "                                               ",
           "                                               "
        };
        System.out.println("Start Program");
        BarcodeImage obj1 = new BarcodeImage(sImageIn);

    }

 }

class BarcodeImage {
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 65;

    private boolean[][] imageData;

    BarcodeImage(){
        imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];

        for(int row = 0; row < MAX_HEIGHT; row ++){
            for(int col = 0; col < MAX_WIDTH; col++){
                this.imageData[row][col] = false;
            }
        } 
    }
    BarcodeImage(String[] strData){

        this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
        int beginOfArrayRow = 0; 
        int beginOfArrayCol  = 0;
        int endOfArrayRow = 0;
        int endOfArrayCol = 0;

        boolean checkSize = true;

        if(checkSize == true){
        //Left Corner points   
       // if(checkSize(strData) == true){

            for(int strRow = 0; strRow < strData.length; strRow++){
                for(int strCol = 0; strCol < strData[strRow].length(); strCol++){

                    if(strData[strRow].charAt(strCol) == '*'){
                        endOfArrayRow = strRow;
                        endOfArrayCol = strCol;
                    }

                }
            }

            for(int strRow = strData.length - 1; strRow >= 0; strRow--){
                for(int strCol = strData[strRow].length() - 1; strCol >= 0; strCol--){

                    if(strData[strRow].charAt(strCol) == '*'){
                        beginOfArrayRow = strRow;
                        beginOfArrayCol = strCol;
                    }
                }

                }
                for(int strRow1 = beginOfArrayRow; strRow1 <= endOfArrayRow; strRow1++){

                    for(int strCol1 = beginOfArrayCol; strCol1 <= endOfArrayCol; strCol1++){

                       if(strData[strRow1].charAt(strCol1) == '*') {
                          imageData[MAX_HEIGHT - strData.length + strRow1][strCol1] = true;

                       }
                       else {
                          imageData[MAX_HEIGHT - strData.length + strRow1][strCol1] = false;
                       }
                }
            }
                for(int strRow = 0; strRow < strData.length - 1; strRow++){
                   for(int strCol = 0; strCol < strData[strRow].length() - 1; strCol++){
                       System.out.println("strData[strRow].charAt(strCol) = [" + strRow + "][" + strCol + "] " + strData[strRow].charAt(strCol) + " = " + imageData[MAX_HEIGHT - strData.length + strRow][strCol] );

                   }
               }
        }

    }
}

=======
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