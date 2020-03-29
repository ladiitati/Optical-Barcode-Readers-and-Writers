
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
                        
                        if(strData[strRow1].charAt(strCol1) == '*' ? true:false ){
                        }
                }
            }
        }
    
    }
}

