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

    public void displayImageToConsole()
    {

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
        int width = 0;
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

}
