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
                if (strData[i].charAt(j) == '*') {
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
//        System.out.println("col " + col + " row " + row);
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
	   
    public boolean scan(BarcodeImage bc) {
    	cleanImage();
    	return true;
    	}

    public boolean readText(String text) {return true;}

    public boolean generateImageFromText() {return true;}

    public boolean translateImageToText() {return true;}

    public void displayTextToConsole() {}

    public void displayImageToConsole() {}
    
    private int computeSignalWidth(){
    	int width = 0;
    	//INCORRECT; change to look at bottom row
    	for (int i = 0; i < BarcodeImage.MAX_WIDTH; i++) {
    		if (image.getPixel(i, 0)) {
    			width++;
    		}
    	}
    	return width;
    }
    private int computeSignalHeight(){
    	int height = 0;
    	for (int j = 0; j < BarcodeImage.MAX_HEIGHT; j++) {
    		if (image.getPixel(0, j)) {
    			height++;
    		}
    	}
    	return height;
    }
    
    public void displayRawImage(){}
    private void clearImage(){}
    
    private void cleanImage() {}
    private void moveImageToLowerLeft(){
    	//find max offset (blank spaces to bottom and right)
    	//locate first instance of existing pixel within barcode
    	//find how many movements it took to reach that (offset)
    	for (int i = 0; i < BarcodeImage.MAX_HEIGHT; i++) {
    		if (image.getPixel(col, row)) {
    			
    		}
    	}
    	//use shiftImageDown and shiftImageLeft to move that number of offsets
    }
    
    private void shiftImageDown(int offset){
    	//move matrix down (offset number) of rows
    	//make another helper function to prevent moving too many rows?
    }
    private void shiftImageLeft(int offset){
    	//move matrix left (offset number) of columns
    }

}