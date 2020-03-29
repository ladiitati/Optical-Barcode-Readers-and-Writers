
public class assign4 {

    public static void main(String[] args) {
        String[] sImageIn =
        {
           " a                                             ",
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
           "                                            b  ",
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
            for(int strCol = 0; strCol < strData.length - 1; strCol++){
                System.out.println("strData[strCol] = [" + strCol + "] " + strData[strCol]);
                for(int strRow = 0; strRow < strData[strCol].length() - 1; strRow++){
                    System.out.println("strData[strRow].charAt(strRow) = [][" + strRow + "] " + strData[strCol].charAt(strRow));

                // for(int strRow = strData[strCol].length(); strRow >= 0; strRow --){
                //     System.out.println("Col length = " + strData.length);
                //     System.out.println("Row length " + strData[strCol].length());
                    // System.out.println("col "  + strCol);
                    // System.out.println("row = "  + strRow);

                  //  System.out.println("array[" + strRow + "][" + strCol + "] = "  + strData[strCol].charAt(strCol));
                    //  if(strData[strCol].charAt(strCol) == '*'){
                    //     System.out.println("strData[strCol].charAt(strCol) "  + strData[strCol].charAt(strCol));
                    //  }
                    //  if(strData[strCol].charAt(strCol) == 'c'){
                    //     System.out.println("!! C strData[strCol].charAt(strCol) "  + strData[strCol].charAt(strCol));
                    //  }
                    //  if(strData[strCol].charAt(strCol) == 't'){
                    //     System.out.println("!! t strData[strCol].charAt(strCol) "  + strData[strCol].charAt(strCol));
                    //  }
                }
            }
        }
    
    }
}

