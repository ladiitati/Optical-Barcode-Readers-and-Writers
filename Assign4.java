import java.util.Arrays;

public class Assign4
{
    public static void main(String[] args)
    {
        String[] sImageIn_3 =
                {
                        "* * * * * * * * * * * * * * * * * * * ",
                        "*                                    *",
                        "**** *** **   ***** ****   *********  ",
                        "* ************ ************ **********",
                        "** *      *    *  * * *         * *   ",
                        "***   *  *           * **    *      **",
                        "* ** * *  *   * * * **  *   ***   *** ",
                        "* *           **    *****  *   **   **",
                        "****  *  * *  * **  ** *   ** *  * *  ",
                        "**************************************"
                };

        BarcodeImage bc = new BarcodeImage(sImageIn_3);
        DataMatrix dm = new DataMatrix(bc);
        //Testing for rawImage
        dm.displayRawImage();
        // First secret message
        dm.translateImageToText();
        dm.displayTextToConsole();
        dm.displayImageToConsole();
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
    public BarcodeImage clone() {
        BarcodeImage cloneImage;
        try {
            cloneImage = (BarcodeImage) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error();
        }
        return cloneImage;
    }
}
class DataMatrix implements BarcodeIO
{
    public static final char BLACK_CHAR = '*';
    public static final char WHITE_CHAR = ' ';
    private String text;
    private BarcodeImage image;
    private int actualWidth = 0;
    private int actualHeight = 0;

    public DataMatrix()
    {
        image = new BarcodeImage();
        text = new String("");
    }

    public DataMatrix(String string)
    {
        text = new String(string);
        image = new BarcodeImage();

        actualWidth = string.length();

        //other stuff maybe
        generateImageFromText();


    }

    public DataMatrix(BarcodeImage bc)
    {
        image = bc.clone();
        text = new String("");
    }

    public boolean scan(BarcodeImage bc)
    {

        return true;
    }

    public boolean readText(String text)
    {
        return true;
    }

    public boolean generateImageFromText()
    {

        for (int i = 0; i < text.length(); i++)
        {
            WriteCharToCol(i, text.charAt(i));
        }
        return true;
    }

    public boolean translateImageToText()
    {

        actualWidth = 38;
        for (int i = 1; i < actualWidth -1; i++)
        {
            text += readCharFromCol(i);
        }

        return true;
    }

    public void displayTextToConsole()
    {
        System.out.println(text);
    }

    private char readCharFromCol(int col)
    {
        String string = new String();

        //for (int i = 0; i < 10; i++)
        for (int i = 21; i < 29; i++)
        {
            if (image.getPixel(i, col))
            {
                //1 - true
                string += '1';
            }
            else
            {
                //0 - false
                string += '0';
            }
        }

        int decimal = Integer.parseInt(string,2);
        return ((char)decimal);
    }

    private boolean WriteCharToCol(int col, char ch)
    {
        String string = new String(Integer.toBinaryString(getASCII(ch)));
        System.out.println(string);


        //write binary to column
        int index = string.length() - 1;

        //get height from accessor - replace 9
        for (int i = 9; i >  9 - string.length(); i--)
        {
            if (string.charAt(index) == '0')
            {
                //0 - false
                image.setPixel(col, i, false);
            }
            else
            {
                //1 - true
                image.setPixel(col, i, true);
            }
            index--;
        }

        //write false to remaining cells
        for (int i = 0; i > string.length(); i++)
        {
            image.setPixel(col, i, false);
        }

        return true;
    }

    private int getASCII(char code)
    {
        return (int)code;
    }

    public int getActualHeight()
    {
        return actualHeight;
    }

    public int getActualWidth()
    {
        return actualWidth;
    }
    
    private int computeSignalWidth(){
    	//Use after image is shifted; looks at bottommost row (max height)
    	int width = 0;
    	for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
    		if (image.getPixel(BarcodeImage.MAX_HEIGHT - 1, col)) {
    			width++;
    		}
    	}
    	return width;
    }
    private int computeSignalHeight(){
    	//Use after image is shifted; looks at leftmost column (0)
    	int height = 0;
    	for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++) {
    		if (image.getPixel(row, 0)) {
    			height++;
    		}
    	}
    	return height;
    }
    
    public void displayImageToConsole() {
    	String outputImage = "";
    	int height = computeSignalHeight();
    	int width = computeSignalWidth();
    	//Top border output
    	for (int col = 0; col < width + 2; col++) {
    		outputImage += "-";
    	}
    	outputImage += "\n";
    	//Output for each row
    	for (int row = BarcodeImage.MAX_HEIGHT - height; row < BarcodeImage.MAX_HEIGHT; row++) {
    		outputImage += "|";
    		for (int col = 0; col < width; col++) {
    			if (image.getPixel(row, col)) {
    				outputImage += BLACK_CHAR;
    			}
    			else {
    				outputImage += WHITE_CHAR;
    			}
    		}
    		outputImage += "|\n";
    	}
    	//Bottom border output
    	for (int col = 0; col < width + 2; col++) {
    		outputImage += "-";
    	}
    	System.out.println(outputImage);

    }
    
    public void displayRawImage(){
    	String outputImage = "";
    	//Top border output
    	for (int col = 0; col < BarcodeImage.MAX_WIDTH + 2; col++) {
    		outputImage += "-";
    	}
    	outputImage += "\n";
    	//Output for each row
    	for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++) {
    		outputImage += "|";
    		for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
    			if (image.getPixel(row, col)) {
    				outputImage += BLACK_CHAR;
    			}
    			else {
    				outputImage += WHITE_CHAR;
    			}
    		}
    		outputImage += "|\n";
    	}
    	//Bottom border output
    	for (int col = 0; col < BarcodeImage.MAX_WIDTH + 2; col++) {
    		outputImage += "-";
    	}
    	System.out.println(outputImage);
    }
    private void clearImage(){}
    
    private void cleanImage() {}
    private void moveImageToLowerLeft(){
    	//find max offset (blank spaces to bottom and right)
    	//locate first instance of existing pixel within barcode (upper left)
    	//find how many movements it took to reach that (offset)
    	/*shiftImageDown(findBottomMargin(findLeftMargin()));
    	shiftImageLeft(findLeftMargin());*/
    	

    	}
    	//use shiftImageDown and shiftImageLeft to move that number of offsets
    
/*    private void shiftImageDown(int offset){
    	//move matrix down (offset number) of rows
    	BarcodeImage shiftedDown = new BarcodeImage();
    	
    	
    }
    private void shiftImageLeft(int offset){
    	//move matrix left (offset number) of columns
    	BarcodeImage shiftedLeft = new BarcodeImage();
    	
    }*/
    
    private void shiftImage (int bottomRow, int leftColumn) {
    	boolean copiedPixel = false;
    	int bottom = bottomRow;
    	int left = leftColumn;
    	//start at bottom left corner of barcode
    	//move to bottom left corner of canvas
    	//traverse with for loops while using setPixel to set it in the shiftedImage
    	for (int row = BarcodeImage.MAX_HEIGHT - 1; row >= 0; row --) {
    		for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
    			copiedPixel = image.getPixel(bottom - row, left - col);
    			image.setPixel(row, col, copiedPixel);
    		}
    	}
    }
    
    private int findLeftColumn() {
    	for (int row = 0; row < BarcodeImage.MAX_HEIGHT - 1; row++) {
    		for (int col = 0; col < BarcodeImage.MAX_WIDTH - 1; col++) {
    			if (image.getPixel(row, col)) {
    				return col;
    				}
    			}
    		}
    	return 0;
    }
    
    private int findBottomRow(int leftColumn) {
    	int bottomMargin = 0;
    	//Index location of bottom of barcode "canvas"
    	int canvasBottom = BarcodeImage.MAX_HEIGHT - 1;
    	//Begin search at bottom of dimensions (max height)
    	for (int row = canvasBottom; row >= 0; row--) {
    		if (image.getPixel(row, leftColumn) == false) {
    			bottomMargin++;
    			}
    		}
    	//Canvas - margin = location of bottom row
    	return canvasBottom - bottomMargin;
    }

}