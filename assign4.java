
public class assign4 {

    public static void main(String[] args) {
        String[] sImageIn =
        {
           "                                               ",
           "   a                                           ",
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
           "                                              b",
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
                    System.out.println("strData[strRow].charAt(strCol) = [" + strRow + "][" + strCol + "] " + strData[strRow].charAt(strCol));
                    if(strData[strRow].charAt(strCol) == '*'){
                        beginOfArrayRow = strRow;
                        beginOfArrayCol = strCol;
                    }

                }
            }
            for(int strRow = 0; strRow < strData.length; strRow++){
                for(int strgit Col = 0; strCol < strData[strRow].length(); strCol++){
                    System.out.println("strData[strRow].charAt(strCol) = [" + strRow + "][" + strCol + "] " + strData[strRow].charAt(strCol));
                    if(strData[strRow].charAt(strCol) == '*'){
                        beginOfArrayRow = strRow;
                        beginOfArrayCol = strCol;
                    }

                }
            }
        }
    
    }
}

