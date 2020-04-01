import java.util.Arrays;

public class Assign4
{

    public static void main(String[] args)
    {
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
        String[] sImageIn_2 =
                {
                        "                                          ",
                        "                                          ",
                        "* * * * * * * * * * * * * * * * * * *     ",
                        "*                                    *    ",
                        "**** *** **   ***** ****   *********      ",
                        "* ************ ************ **********    ",
                        "** *      *    *  * * *         * *       ",
                        "***   *  *           * **    *      **    ",
                        "* ** * *  *   * * * **  *   ***   ***     ",
                        "* *           **    *****  *   **   **    ",
                        "****  *  * *  * **  ** *   ** *  * *      ",
                        "**************************************    ",
                        "                                          ",
                        "                                          ",
                        "                                          ",
                        "                                          "

                };

        //First Secret Message
        BarcodeImage bc1 = new BarcodeImage(sImageIn);
        DataMatrix dm1 = new DataMatrix(bc1);
        // First secret message
        dm1.translateImageToText();
        dm1.displayTextToConsole();
        dm1.displayImageToConsole();

        //Second Secret Message
        BarcodeImage bc2 = new BarcodeImage(sImageIn_2);
        DataMatrix dm2 = new DataMatrix(bc2);
        // First secret message
        dm2.translateImageToText();
        dm2.displayTextToConsole();
        dm2.displayImageToConsole();

        //Test set image from text
        DataMatrix dm4 = new DataMatrix("Test Encoded Message.");
        // First secret message
        dm4.generateImageFromText();
        dm4.displayTextToConsole();
        dm4.displayImageToConsole();

    }
}

interface BarcodeIO
{
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
            return (BarcodeImage) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

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


    DataMatrix() {
        this.text = "";
        this.image = new BarcodeImage();
        this.actualWidth = 0;
        this.actualHeight = 0;
    }

    DataMatrix(BarcodeImage image) {
        this();
        scan(image);
    }

    DataMatrix(String text) {
        this();
        this.readText(text);
    }

    public boolean readText(String text) {
        if (text.length() < BarcodeImage.MAX_WIDTH) {
            this.text = text;
            return true;
        }
        return false;
    }

    public boolean scan(BarcodeImage image) {
        try {
            this.image = (BarcodeImage) image.clone();
            this.cleanImage();
            this.actualWidth = this.computeSignalWidth();
            this.actualHeight = this.computeSignalHeight();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean generateImageFromText()
    {
        if (text.length() > BarcodeImage.MAX_WIDTH)
        {
            return false;
        }
        else
        {
            for (int i = 1; i < text.length(); i++)
            {
                WriteCharToCol(i, text.charAt(i));
            }

            generateEdges();
            cleanImage();
            actualHeight = computeSignalHeight();
            return true;
        }
    }

    private void generateEdges()
    {
        actualWidth = text.length();

        //Left Edge
        for (int i = 0; i < 10; i++ )
        {
            image.setPixel(i, 0, true);
        }

        //Bottom Edge
        for (int i = 0; i < actualWidth + 1; i++ )
        {
            image.setPixel(9, i, true);
        }

        //Top Edge
        for (int i = 0; i < actualWidth + 1; i++ )
        {
            if(i % 2 == 0)
            {
                image.setPixel(0, i, true);
            }
            else
            {
                image.setPixel(0, i, false);
            }
        }
    }

    public boolean translateImageToText()
    {
        if (actualWidth == 0)
        {
            return false;
        }
        else
        {
            for (int i = 1; i < actualWidth - 1; i++)
            {
                text += readCharFromCol(i);
            }
            return true;
        }
    }

    public void displayTextToConsole()
    {
        System.out.println(text);
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

    private char readCharFromCol(int col)
    {
        String string = new String();

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

        //write binary to column
        int index = string.length() - 1;

        if (string.length() == 0)
        {
            return false;
        }
        else
        {
            for (int i = 8; i > 8 - string.length(); i--)
            {
                if (string.charAt(index) == '0')
                {
                    //0 - false
                    image.setPixel(i, col, false);
                }
                else
                {
                    //1 - true
                    image.setPixel(i, col, true);
                }
                index--;
            }

            //write false to remaining cells
            for (int i = 0; i > string.length() - 1; i++)
            {
                image.setPixel(col, i, false);
            }

            return true;
        }
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

    private void clearImage(){
        for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++) {
            for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
                image.setPixel(row, col, false);
            }
        }
    }

    private void cleanImage() {
        shiftImageLeft(findLeftColumn());
        shiftImageDown(findBottomRow());
    }

    private void shiftImageDown(int bottomRow){
        int rowCounter = bottomRow;
        //If image is not already located at bottom,
        //copy image one row at a time to the bottom, working upwards
        if (bottomRow < BarcodeImage.MAX_HEIGHT - 1) {
            for (int row = BarcodeImage.MAX_HEIGHT - 1; row >= 0; row --) {
                for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
                    image.setPixel(row, col, image.getPixel(rowCounter, col));
                }
                rowCounter--;
            }
        }

    }

    private void shiftImageLeft(int leftColumn){
        //If image is not already located at left,
        //copy image one column at a time to the left, working to the right
        int colCounter = leftColumn;
        if (leftColumn > 0) {
            for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
                for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++) {
                    image.setPixel(row, col,image.getPixel(row, colCounter));
                }
                colCounter++;
            }
        }
    }

    private int findLeftColumn() {
        //Begin search at upper left corner and move down
        for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++) {
            for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
                //Once pixel is located, return column location
                if (image.getPixel(row, col)) {
                    return col;
                }
            }
        }
        return 0;
    }

    private int findBottomRow() {
        //Begin search at bottom left of corner and move up
        for (int row = BarcodeImage.MAX_HEIGHT - 1; row >= 0; row--) {
            for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
                //Once pixel is located, return row location
                if (image.getPixel(row, col)) {
                    return row;
                }
            }
        }
        return 0;
    }
}
